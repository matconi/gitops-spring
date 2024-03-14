package domain.company.project.module.dto.request.solver;

import ai.timefold.solver.core.api.domain.lookup.PlanningId;
import lombok.Data;

@Data
public class TeacherSolverRequest {
    @PlanningId
    private Long id;
    private String name;
    private String email;
}
