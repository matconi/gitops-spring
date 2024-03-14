package domain.company.project.module.dto.request.solver;

import ai.timefold.solver.core.api.domain.entity.PlanningEntity;
import ai.timefold.solver.core.api.domain.lookup.PlanningId;
import ai.timefold.solver.core.api.domain.variable.PlanningVariable;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@PlanningEntity
public class LessonSolverRequest {
    @PlanningId
    private Long id;
    private String subject;
    private TeacherSolverRequest teacher;
    private String studentGroup;
    @JsonIdentityReference
    @PlanningVariable
    private TimeslotSolverRequest timeslot;
    @JsonIdentityReference
    @PlanningVariable
    private RoomSolverRequest room;
}
