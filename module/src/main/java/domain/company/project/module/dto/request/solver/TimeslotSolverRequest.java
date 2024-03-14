package domain.company.project.module.dto.request.solver;

import ai.timefold.solver.core.api.domain.lookup.PlanningId;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;

import java.time.DayOfWeek;
import java.time.LocalTime;

@Data
@JsonIdentityInfo(scope = TimeslotSolverRequest.class, generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class TimeslotSolverRequest {
    @PlanningId
    private Long id;
    private DayOfWeek dayOfWeek;
    private LocalTime startTime;
    private LocalTime endTime;
}
