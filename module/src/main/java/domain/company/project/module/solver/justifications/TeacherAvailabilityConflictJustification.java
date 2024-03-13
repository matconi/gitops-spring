package domain.company.project.module.solver.justifications;

import ai.timefold.solver.core.api.score.stream.ConstraintJustification;
import domain.company.project.module.domain.entities.Lesson;
import domain.company.project.module.domain.entities.Teacher;

public record TeacherAvailabilityConflictJustification(Teacher teacher, Lesson lesson, String description)
        implements
            ConstraintJustification {

    public TeacherAvailabilityConflictJustification(Teacher teacher, Lesson lesson) {
        this(teacher, lesson,
                "Teacher '%s' needs to teach lesson '%s' for student group '%s' with no availability at '%s %s'"
                        .formatted(teacher.getName(), lesson.getSubject(), lesson.getStudentGroup(),
                                lesson.getTimeslot().getDayOfWeek(),
                                lesson.getTimeslot().getStartTime()));
    }
}
