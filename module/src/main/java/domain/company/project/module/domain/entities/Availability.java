package domain.company.project.module.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.optaplanner.core.api.domain.lookup.PlanningId;

import javax.persistence.*;
import java.time.DayOfWeek;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "availabilities")
public class Availability {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

    @Column(name = "dayOfWeek", length = 120, nullable = false)
    private DayOfWeek dayOfWeek;

    @Column(name = "time", length = 120, nullable = false)
    private LocalTime time;
}
