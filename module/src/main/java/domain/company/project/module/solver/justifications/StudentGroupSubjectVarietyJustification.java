package domain.company.project.module.solver.justifications;

import ai.timefold.solver.core.api.score.stream.ConstraintJustification;

import domain.company.project.module.dto.request.solver.LessonSolverRequest;

public record StudentGroupSubjectVarietyJustification(String studentGroup, LessonSolverRequest lesson1, LessonSolverRequest lesson2, String description)
        implements
            ConstraintJustification {

    public StudentGroupSubjectVarietyJustification(String studentGroup, LessonSolverRequest lesson1, LessonSolverRequest lesson2) {
        this(studentGroup, lesson1, lesson2,
                "Student Group '%s' has two consecutive lessons on '%s' at '%s %s' and at '%s %s'"
                        .formatted(studentGroup, lesson1.getSubject(), lesson1.getTimeslot().getDayOfWeek(),
                                lesson1.getTimeslot().getStartTime(), lesson2.getTimeslot().getDayOfWeek(),
                                lesson2.getTimeslot().getStartTime()));
    }
}
