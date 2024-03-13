package domain.company.project.module.solver.justifications;

import ai.timefold.solver.core.api.score.stream.ConstraintJustification;

import domain.company.project.module.domain.entities.Lesson;
import domain.company.project.module.domain.entities.Teacher;

public record TeacherTimeEfficiencyJustification(Teacher teacher, Lesson lesson1, Lesson lesson2, String description)
        implements
            ConstraintJustification {

    public TeacherTimeEfficiencyJustification(Teacher teacher, Lesson lesson1, Lesson lesson2) {
        this(teacher, lesson1, lesson2,
                "Teacher '%s' has 2 consecutive lessons: lesson '%s' for student group '%s' at '%s %s' and lesson '%s' for student group '%s' at '%s %s' (gap)"
                        .formatted(teacher.getName(), lesson1.getSubject(), lesson1.getStudentGroup(),
                                lesson1.getTimeslot().getDayOfWeek(), lesson1.getTimeslot().getStartTime(),
                                lesson2.getSubject(), lesson2.getStudentGroup(), lesson2.getTimeslot().getDayOfWeek(),
                                lesson2.getTimeslot().getStartTime()));
    }
}
