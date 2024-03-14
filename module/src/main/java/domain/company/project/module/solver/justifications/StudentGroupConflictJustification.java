package domain.company.project.module.solver.justifications;

import ai.timefold.solver.core.api.score.stream.ConstraintJustification;

import domain.company.project.module.dto.request.solver.LessonSolverRequest;

public record StudentGroupConflictJustification(String studentGroup, LessonSolverRequest lesson1, LessonSolverRequest lesson2, String description)
        implements
            ConstraintJustification {
    public StudentGroupConflictJustification(String studentGroup, LessonSolverRequest lesson1, LessonSolverRequest lesson2) {
        this(studentGroup, lesson1, lesson2,
                "Student group '%s' has lesson '%s' and lesson '%s' at '%s %s'"
                        .formatted(studentGroup, lesson1.getSubject(), lesson2.getSubject(),
                                lesson1.getTimeslot().getDayOfWeek(), lesson1.getTimeslot().getStartTime()));
    }
}
