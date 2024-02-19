package domain.company.project.module.controllers;

import domain.company.project.module.domain.TimeTable;
import domain.company.project.module.services.TimeTableService;
import org.optaplanner.core.api.solver.SolutionManager;
import org.optaplanner.core.api.solver.SolverJob;
import org.optaplanner.core.api.solver.SolverManager;
import org.optaplanner.core.api.solver.SolverStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;

import java.util.UUID;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/timeTable")
public class TimeTableController {
    private SolverManager<TimeTable, UUID> solverManager;
    private SolutionManager<TimeTable, HardSoftScore> solutionManager;
    @Autowired
    private TimeTableService timeTableService;

    @GetMapping("/demoData")
    public void demoData() {
        timeTableService.generateDemoData();
    }
    @PostMapping("/solve")
    public TimeTable solve(@RequestBody TimeTable problem) {
        UUID problemId = UUID.randomUUID();
        // Submit the problem to start solving
        SolverJob<TimeTable, UUID> solverJob = solverManager.solve(problemId, problem);
        TimeTable solution;
        try {
            // Wait until the solving ends
            solution = solverJob.getFinalBestSolution();
        } catch (InterruptedException | ExecutionException e) {
            throw new IllegalStateException("Solving failed.", e);
        }
        return solution;
    }
}
