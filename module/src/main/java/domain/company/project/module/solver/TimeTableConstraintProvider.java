package domain.company.project.module.solver;

import java.time.Duration;

import ai.timefold.solver.core.api.score.stream.Constraint;
import ai.timefold.solver.core.api.score.stream.ConstraintFactory;
import ai.timefold.solver.core.api.score.stream.ConstraintProvider;
import ai.timefold.solver.core.api.score.stream.Joiners;

import domain.company.project.module.config.TimeTableConstraintConfiguration;
import domain.company.project.module.dto.request.solver.LessonSolverRequest;
import domain.company.project.module.dto.request.solver.AvailabilitySolverRequest;
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
                .forEachUniquePair(LessonSolverRequest.class,
                        // ... in the same timeslot ...
                        Joiners.equal(LessonSolverRequest::getTimeslot),
                        // ... in the same room ...
                        Joiners.equal(LessonSolverRequest::getRoom))
                // ... and penalize each pair with a hard weight.
                .penalizeConfigurable()
                .justifyWith((lesson1, lesson2, score) -> new RoomConflictJustification(lesson1.getRoom(), lesson1, lesson2))
                .asConstraint(TimeTableConstraintConfiguration.ROOM_CONFLICT);
    }

    Constraint teacherConflict(ConstraintFactory constraintFactory) {
        // A teacher can teach at most one lesson at the same time.
        return constraintFactory
                .forEachUniquePair(LessonSolverRequest.class,
                        Joiners.equal(LessonSolverRequest::getTimeslot),
                        Joiners.equal(LessonSolverRequest::getTeacher))
                .penalizeConfigurable()
                .justifyWith(
                        (lesson1, lesson2, score) -> new TeacherConflictJustification(lesson1.getTeacher(), lesson1, lesson2))
                .asConstraint(TimeTableConstraintConfiguration.TEACHER_CONFLICT);
    }

    Constraint studentGroupConflict(ConstraintFactory constraintFactory) {
        // A student can attend at most one lesson at the same time.
        return constraintFactory
                .forEachUniquePair(LessonSolverRequest.class,
                        Joiners.equal(LessonSolverRequest::getTimeslot),
                        Joiners.equal(LessonSolverRequest::getStudentGroup))
                .penalizeConfigurable()
                .justifyWith((lesson1, lesson2, score) -> new StudentGroupConflictJustification(lesson1.getStudentGroup(), lesson1, lesson2))
                .asConstraint(TimeTableConstraintConfiguration.STUDENT_GROUP_CONFLICT);
    }

    Constraint teacherAvailableConflict(ConstraintFactory constraintFactory) {
        // A teacher can teach at most one lesson at the same time.
        return constraintFactory
                .forEach(LessonSolverRequest.class)
                .join(AvailabilitySolverRequest.class,
                        Joiners.equal((LessonSolverRequest lesson) -> lesson.getTeacher().getId(),
                                AvailabilitySolverRequest::getTeacherId),
                        Joiners.equal((LessonSolverRequest lesson) ->lesson.getTimeslot().getId(),
                                (AvailabilitySolverRequest::getTimesoltId)))
                .penalizeConfigurable()
                .justifyWith(
                        (lesson, availability,score) -> new TeacherAvailabilityConflictJustification(lesson.getTeacher(), lesson))
                .asConstraint(TimeTableConstraintConfiguration.TEACHER_AVAILABILITY_CONFLICT);
    }

    Constraint teacherRoomStability(ConstraintFactory constraintFactory) {
        // A teacher prefers to teach in a single room.
        return constraintFactory
                .forEachUniquePair(LessonSolverRequest.class,
                        Joiners.equal(LessonSolverRequest::getTeacher))
                .filter((lesson1, lesson2) -> lesson1.getRoom() != lesson2.getRoom())
                .penalizeConfigurable()
                .justifyWith((lesson1, lesson2, score) -> new TeacherRoomStabilityJustification(lesson1.getTeacher(), lesson1, lesson2))
                .asConstraint(TimeTableConstraintConfiguration.TEACHER_ROOM_STABILITY);
    }

    Constraint teacherTimeEfficiency(ConstraintFactory constraintFactory) {
        // A teacher prefers to teach sequential lessons and dislikes gaps between lessons.
        return constraintFactory
                .forEachUniquePair(LessonSolverRequest.class,
                        Joiners.equal(LessonSolverRequest::getTeacher),
                        Joiners.equal((lesson) -> lesson.getTimeslot().getDayOfWeek()))
                .filter((lesson1, lesson2) -> {
                    Duration between = Duration.between(lesson1.getTimeslot().getEndTime(),
                            lesson2.getTimeslot().getStartTime());
                    return !between.isNegative() && between.compareTo(Duration.ofMinutes(50)) <= 0;
                })
                .rewardConfigurable()
                .justifyWith((lesson1, lesson2, score) -> new TeacherTimeEfficiencyJustification(lesson1.getTeacher(), lesson1, lesson2))
                .asConstraint(TimeTableConstraintConfiguration.TEACHER_TIME_EFFICIENCY);
    }

    Constraint studentGroupSubjectVariety(ConstraintFactory constraintFactory) {
        // A student group dislikes sequential lessons on the same subject.
        // Em resumo, o código filtra pares de aulas onde a segunda começa dentro
        // de X minutos após o término da primeira.
        return constraintFactory
                .forEach(LessonSolverRequest.class)
                .join(LessonSolverRequest.class,
                        Joiners.equal(LessonSolverRequest::getSubject),
                        Joiners.equal(LessonSolverRequest::getStudentGroup),
                        Joiners.equal((lesson) -> lesson.getTimeslot().getDayOfWeek()))
                .filter((lesson1, lesson2) -> {
                    Duration between = Duration.between(lesson1.getTimeslot().getEndTime(),
                            lesson2.getTimeslot().getStartTime());
                    return !between.isNegative() && between.compareTo(Duration.ofMinutes(50)) <= 0;
                })
                .rewardConfigurable()
                .justifyWith((lesson1, lesson2, score) -> new StudentGroupSubjectVarietyJustification(lesson1.getStudentGroup(), lesson1, lesson2))
                .asConstraint(TimeTableConstraintConfiguration.STUDENT_GROUP_VARIETY);
    }
}
