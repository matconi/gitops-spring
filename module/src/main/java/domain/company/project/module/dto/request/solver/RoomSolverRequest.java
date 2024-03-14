package domain.company.project.module.dto.request.solver;

import ai.timefold.solver.core.api.domain.lookup.PlanningId;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;

@Data
@JsonIdentityInfo(scope = RoomSolverRequest.class, generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class RoomSolverRequest {
    @PlanningId
    private Long id;
    private String name;
}
