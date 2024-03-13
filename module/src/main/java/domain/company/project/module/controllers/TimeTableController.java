package domain.company.project.module.controllers;

import ai.timefold.solver.core.api.solver.SolverJob;
import ai.timefold.solver.core.api.solver.SolverManager;
import domain.company.project.module.config.TimeTableConstraintConfiguration;
import domain.company.project.module.domain.TimeTable;
import domain.company.project.module.domain.entities.Availability;
import domain.company.project.module.domain.entities.Lesson;
import domain.company.project.module.domain.entities.Teacher;
import domain.company.project.module.domain.entities.Timeslot;
import domain.company.project.module.repositories.LessonRepository;
import domain.company.project.module.repositories.TeacherRepository;
import domain.company.project.module.repositories.TimeslotRepository;
import domain.company.project.module.services.TimeTableService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/timeTable")
@RequiredArgsConstructor
public class TimeTableController {
    private final SolverManager<TimeTable, UUID> solverManager;
    private final TimeTableService timeTableService;
    private final TeacherRepository teacherRepository;
    private final LessonRepository lessonRepository;
    private final TimeslotRepository timeslotRepository;

    @GetMapping("/demoData")
    public void demoData() {
        timeTableService.generateDemoData();
    }

    @GetMapping("/ava")
    public boolean testAvailableTeacher() {
        // test availability custom constraint
        Teacher teacher = teacherRepository.findById(1L).orElse(null);
        Lesson lesson = lessonRepository.findById(1L).orElse(null);
        Timeslot timeslot = timeslotRepository.findById(31L).orElse(null);
        lesson.setTimeslot(timeslot);
        lesson.setTeacher(teacher);
        List<Availability> availablies = teacher.getAvailabilities().stream()
                .filter(
                    availability -> {
                        LocalTime lessonStart = lesson.getTimeslot().getStartTime();
                        LocalTime lessonEnd = lesson.getTimeslot().getEndTime();

                        boolean day = availability.getDayOfWeek().equals(lesson.getTimeslot().getDayOfWeek());
                        boolean startBefore = lessonStart.isBefore(availability.getEndTime()) || lessonStart.equals(availability.getEndTime());
                        boolean endAfter = lessonEnd.isAfter(availability.getStartTime()) || lessonStart.equals(availability.getStartTime());
                        boolean endsMiddleLesson = !availability.getEndTime().isBefore(lessonEnd);
                        boolean startsMiddleLesson = !availability.getStartTime().isAfter(lessonStart);
                        System.out.println("day: " + day);
                        System.out.println("startBefore: " + startBefore);
                        System.out.println("endAfter: " + endAfter);
                        System.out.println("endsMiddleLesson: " + endsMiddleLesson);
                        System.out.println("startsMiddleLesson: " + startsMiddleLesson);

                        return day && startBefore && endAfter && endsMiddleLesson && startsMiddleLesson;
                    })
                .toList();
        System.out.println("ava array: ");
        for (Availability ava: availablies) {
            System.out.println("ava item: " + ava);
        }
        return availablies.isEmpty();
    }
    @PostMapping("/solve")
    public TimeTable solve(@RequestBody TimeTable problem) {
        TimeTableConstraintConfiguration timeTableConstraintConfiguration =
                new TimeTableConstraintConfiguration(problem.getConstraints(), timeTableService);
        problem.setTimeTableConstraintConfiguration(timeTableConstraintConfiguration);
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
