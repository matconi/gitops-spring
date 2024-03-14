package domain.company.project.module.solver.justifications;

import ai.timefold.solver.core.api.score.stream.ConstraintJustification;
import domain.company.project.module.dto.request.solver.LessonSolverRequest;
import domain.company.project.module.dto.request.solver.TeacherSolverRequest;

public record TeacherAvailabilityConflictJustification(TeacherSolverRequest teacher, LessonSolverRequest lesson, String description)
        implements
            ConstraintJustification {

    public TeacherAvailabilityConflictJustification(TeacherSolverRequest teacher, LessonSolverRequest lesson) {
        this(teacher, lesson,
                "Teacher '%s' needs to teach lesson '%s' for student group '%s' with no availability at '%s %s'"
                        .formatted(teacher.getName(), lesson.getSubject(), lesson.getStudentGroup(),
                                lesson.getTimeslot().getDayOfWeek(),
                                lesson.getTimeslot().getStartTime()));
    }
}
