package domain.company.project.module.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ai.timefold.solver.core.api.domain.entity.PlanningEntity;
import ai.timefold.solver.core.api.domain.lookup.PlanningId;
import ai.timefold.solver.core.api.domain.variable.PlanningVariable;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "lessons")
public class Lesson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "subject", length = 120, nullable = false)
    private String subject;

    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

    @Column(name = "studentGroup", length = 120, nullable = false)
    private String studentGroup;

    @ManyToOne
    @JoinColumn(name = "timeslot_id")
    private Timeslot timeslot;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;

    public Lesson(String subject, Teacher teacher, String studentGroup) {
        this.subject = subject;
        this.teacher = teacher;
        this.studentGroup = studentGroup;
    }

    @Override
    public String toString() {
        return subject + "(" + id + ")";
    }
}
