package domain.company.project.module.solver;

import java.time.Duration;
import java.time.LocalTime;
import java.util.List;

import ai.timefold.solver.core.api.score.buildin.hardsoft.HardSoftScore;
import ai.timefold.solver.core.api.score.stream.Constraint;
import ai.timefold.solver.core.api.score.stream.ConstraintFactory;
import ai.timefold.solver.core.api.score.stream.ConstraintProvider;
import ai.timefold.solver.core.api.score.stream.Joiners;

import domain.company.project.module.config.TimeTableConstraintConfiguration;
import domain.company.project.module.domain.entities.Availability;
import domain.company.project.module.domain.entities.Lesson;
import domain.company.project.module.domain.entities.Teacher;
import domain.company.project.module.solver.justifications.*;

public class TimeTableConstraintProvider implements ConstraintProvider {

    @Override
    public Constraint[] defineConstraints(ConstraintFactory constraintFactory) {
        return new Constraint[] {
                // Hard constraints
                roomConflict(constraintFactory),
                teacherConflict(constraintFactory),
                studentGroupConflict(constraintFactory),
                teacherAvailableConflict(constraintFactory),
                // Soft constraints
                teacherRoomStability(constraintFactory),
                teacherTimeEfficiency(constraintFactory),
                studentGroupSubjectVariety(constraintFactory)
        };
    }

    Constraint roomConflict(ConstraintFactory constraintFactory) {
        // A room can accommodate at most one lesson at the same time.
        return constraintFactory
                // Select each pair of 2 different lessons ...
                .forEachUniquePair(Lesson.class,
                        // ... in the same timeslot ...
                        Joiners.equal(Lesson::getTimeslot),
                        // ... in the same room ...
                        Joiners.equal(Lesson::getRoom))
                // ... and penalize each pair with a hard weight.
                .penalizeConfigurable()
                .justifyWith((lesson1, lesson2, score) -> new RoomConflictJustification(lesson1.getRoom(), lesson1, lesson2))
                .asConstraint(TimeTableConstraintConfiguration.ROOM_CONFLICT);
    }

    Constraint teacherConflict(ConstraintFactory constraintFactory) {
        // A teacher can teach at most one lesson at the same time.
        return constraintFactory
                .forEachUniquePair(Lesson.class,
                        Joiners.equal(Lesson::getTimeslot),
                        Joiners.equal(Lesson::getTeacher))
                .penalizeConfigurable()
                .justifyWith(
                        (lesson1, lesson2, score) -> new TeacherConflictJustification(lesson1.getTeacher(), lesson1, lesson2))
                .asConstraint(TimeTableConstraintConfiguration.TEACHER_CONFLICT);
    }

    Constraint studentGroupConflict(ConstraintFactory constraintFactory) {
        // A student can attend at most one lesson at the same time.
        return constraintFactory
                .forEachUniquePair(Lesson.class,
                        Joiners.equal(Lesson::getTimeslot),
                        Joiners.equal(Lesson::getStudentGroup))
                .penalizeConfigurable()
                .justifyWith((lesson1, lesson2, score) -> new StudentGroupConflictJustification(lesson1.getStudentGroup(), lesson1, lesson2))
                .asConstraint(TimeTableConstraintConfiguration.STUDENT_GROUP_CONFLICT);
    }

    Constraint teacherAvailableConflict(ConstraintFactory constraintFactory) {
        // A teacher can teach at most one lesson at the same time.
        return constraintFactory
                .forEach(Lesson.class)
                .filter((lesson) -> {
                    LocalTime lessonStart = lesson.getTimeslot().getStartTime();
                    LocalTime lessonEnd = lesson.getTimeslot().getEndTime();
                    List<Availability> availabilities = lesson.getTeacher().getAvailabilities().stream()
                        .filter(
                            availability -> availability.getDayOfWeek().equals(lesson.getTimeslot().getDayOfWeek())
                            && (lessonStart.isBefore(availability.getEndTime()) || lessonStart.equals(availability.getEndTime()))
                            && (lessonEnd.isAfter(availability.getStartTime()) || lessonStart.equals(availability.getStartTime()))
                            && !availability.getEndTime().isBefore(lessonEnd)
                            && !availability.getStartTime().isAfter(lessonStart)
                        )
                        .toList();
                    return availabilities.isEmpty();
                })
                .penalizeConfigurable()
                .justifyWith(
                        (lesson, score) -> new TeacherAvailabilityConflictJustification(lesson.getTeacher(), lesson))
                .asConstraint(TimeTableConstraintConfiguration.TEACHER_AVAILABILITY_CONFLICT);
    }

    Constraint teacherRoomStability(ConstraintFactory constraintFactory) {
        // A teacher prefers to teach in a single room.
        return constraintFactory
                .forEachUniquePair(Lesson.class,
                        Joiners.equal(Lesson::getTeacher))
                .filter((lesson1, lesson2) -> lesson1.getRoom() != lesson2.getRoom())
                .penalize(HardSoftScore.ONE_SOFT)
                .justifyWith((lesson1, lesson2, score) -> new TeacherRoomStabilityJustification(lesson1.getTeacher(), lesson1, lesson2))
                .asConstraint(TimeTableConstraintConfiguration.TEACHER_ROOM_STABILITY);
    }

    Constraint teacherTimeEfficiency(ConstraintFactory constraintFactory) {
        // A teacher prefers to teach sequential lessons and dislikes gaps between lessons.
        return constraintFactory
                .forEachUniquePair(Lesson.class,
                        Joiners.equal(Lesson::getTeacher),
                        Joiners.equal((lesson) -> lesson.getTimeslot().getDayOfWeek()))
                .filter((lesson1, lesson2) -> {
                    Duration between = Duration.between(lesson1.getTimeslot().getEndTime(),
                            lesson2.getTimeslot().getStartTime());
                    return !between.isNegative() && between.compareTo(Duration.ofMinutes(30)) <= 0;
                })
                .rewardConfigurable()
                .justifyWith((lesson1, lesson2, score) -> new TeacherTimeEfficiencyJustification(lesson1.getTeacher(), lesson1, lesson2))
                .asConstraint(TimeTableConstraintConfiguration.TEACHER_TIME_EFFICIENCY);
    }

    Constraint studentGroupSubjectVariety(ConstraintFactory constraintFactory) {
        // A student group dislikes sequential lessons on the same subject.
        return constraintFactory
                .forEach(Lesson.class)
                .join(Lesson.class,
                        Joiners.equal(Lesson::getSubject),
                        Joiners.equal(Lesson::getStudentGroup),
                        Joiners.equal((lesson) -> lesson.getTimeslot().getDayOfWeek()))
                .filter((lesson1, lesson2) -> {
                    Duration between = Duration.between(lesson1.getTimeslot().getEndTime(),
                            lesson2.getTimeslot().getStartTime());
                    return !between.isNegative() && between.compareTo(Duration.ofMinutes(30)) <= 0;
                })
                .penalizeConfigurable()
                .justifyWith((lesson1, lesson2, score) -> new StudentGroupSubjectVarietyJustification(lesson1.getStudentGroup(), lesson1, lesson2))
                .asConstraint(TimeTableConstraintConfiguration.STUDENT_GROUP_VARIETY);
    }

}
