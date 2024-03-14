package domain.company.project.module.solver.justifications;

import ai.timefold.solver.core.api.score.stream.ConstraintJustification;

import domain.company.project.module.dto.request.solver.LessonSolverRequest;
import domain.company.project.module.dto.request.solver.RoomSolverRequest;

public record RoomConflictJustification(RoomSolverRequest room, LessonSolverRequest lesson1, LessonSolverRequest lesson2, String description)
        implements
            ConstraintJustification {

    public RoomConflictJustification(RoomSolverRequest room, LessonSolverRequest lesson1, LessonSolverRequest lesson2) {
        this(room, lesson1, lesson2,
                "Room '%s' is used for lesson '%s' for student group '%s' and lesson '%s' for student group '%s' at '%s %s'"
                        .formatted(room, lesson1.getSubject(), lesson1.getStudentGroup(), lesson2.getSubject(),
                                lesson2.getStudentGroup(), lesson1.getTimeslot().getDayOfWeek(),
                                lesson1.getTimeslot().getStartTime()));
    }
}
