package domain.company.project.module.solver.justifications;

import ai.timefold.solver.core.api.score.stream.ConstraintJustification;

import domain.company.project.module.domain.entities.Lesson;
import domain.company.project.module.domain.entities.Teacher;

public record TeacherRoomStabilityJustification(Teacher teacher, Lesson lesson1, Lesson lesson2, String description)
        implements
            ConstraintJustification {

    public TeacherRoomStabilityJustification(Teacher teacher, Lesson lesson1, Lesson lesson2) {
        this(teacher, lesson1, lesson2,
                "Teacher '%s' has two lessons in different rooms: room '%s' at '%s %s' and room '%s' at '%s %s'"
                        .formatted(teacher.getName(), lesson1.getRoom(), lesson1.getTimeslot().getDayOfWeek(),
                                lesson1.getTimeslot().getStartTime(), lesson2.getRoom(), lesson2.getTimeslot().getDayOfWeek(),
                                lesson2.getTimeslot().getStartTime()));
    }
}
