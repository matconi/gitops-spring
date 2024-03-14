package domain.company.project.module.dto.request;

import ai.timefold.solver.core.api.domain.lookup.PlanningId;
import lombok.Data;

@Data
public class RoomRequest {
    private Long id;
    private String name;
}
