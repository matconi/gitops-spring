package domain.company.project.module.domain.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ai.timefold.solver.core.api.domain.lookup.PlanningId;

import javax.persistence.*;
import java.time.DayOfWeek;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
//@JsonIdentityInfo(scope = Timeslot.class, generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Entity
@Table(name = "timeslots")
public class Timeslot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "dayOfWeek", length = 120, nullable = false)
    private DayOfWeek dayOfWeek;

    @Column(name = "startTime", length = 120, nullable = false)
    private LocalTime startTime;

    @Column(name = "endTime", length = 120, nullable = false)
    private LocalTime endTime;

    public Timeslot(DayOfWeek dayOfWeek, LocalTime startTime, LocalTime endTime) {
        this.dayOfWeek = dayOfWeek;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return dayOfWeek + " " + startTime;
    }
}
