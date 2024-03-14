package domain.company.project.module.domain.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ai.timefold.solver.core.api.domain.lookup.PlanningId;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
//@JsonIdentityInfo(scope = Timeslot.class, generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Entity
@Table(name = "rooms")
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", length = 120, nullable = false)
    private String name;

    public Room(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
