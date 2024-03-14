package domain.company.project.module.domain;

import ai.timefold.solver.core.api.domain.constraintweight.ConstraintConfigurationProvider;
import ai.timefold.solver.core.api.domain.solution.PlanningEntityCollectionProperty;
import ai.timefold.solver.core.api.domain.solution.PlanningScore;
import ai.timefold.solver.core.api.domain.solution.PlanningSolution;
import ai.timefold.solver.core.api.domain.solution.ProblemFactCollectionProperty;
import ai.timefold.solver.core.api.domain.valuerange.ValueRangeProvider;
import ai.timefold.solver.core.api.score.buildin.hardsoft.HardSoftScore;
import ai.timefold.solver.core.api.solver.SolverStatus;
import domain.company.project.module.config.TimeTableConstraintConfiguration;
import domain.company.project.module.dto.request.*;
import domain.company.project.module.dto.request.solver.LessonSolverRequest;
import domain.company.project.module.dto.request.solver.AvailabilitySolverRequest;
import domain.company.project.module.dto.request.solver.RoomSolverRequest;
import domain.company.project.module.dto.request.solver.TimeslotSolverRequest;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@PlanningSolution
public class TimeTable {

    @ConstraintConfigurationProvider
    private TimeTableConstraintConfiguration timeTableConstraintConfiguration;

    private List<UserConstraint> constraints;

    @ProblemFactCollectionProperty
    @ValueRangeProvider
    private List<TimeslotSolverRequest> timeslots;

    @ProblemFactCollectionProperty
    @ValueRangeProvider
    private List<RoomSolverRequest> rooms;

    @PlanningEntityCollectionProperty
    private List<LessonSolverRequest> lessons;

    @ProblemFactCollectionProperty
    @ValueRangeProvider
    private List<AvailabilitySolverRequest> availabilities;

    @PlanningScore
    private HardSoftScore score;

    // Ignored by Timefold, used by the UI to display solve or stop solving button
    private SolverStatus solverStatus;
}
