package domain.company.project.module.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "teachers")
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", length = 120, nullable = false)
    private String name;

    @Column(name = "email", nullable = false)
    private String email;

    @OneToMany(mappedBy = "teacher", cascade = CascadeType.ALL)
    private List<Availability> availabilities;
}
