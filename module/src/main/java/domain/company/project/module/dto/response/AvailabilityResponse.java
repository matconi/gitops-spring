package domain.company.project.module.dto.response;

import lombok.Data;

import java.time.DayOfWeek;
import java.time.LocalTime;

@Data
public class AvailabilityResponse {
    private Long id;
    private TeacherResponse teacherResponse;
    private DayOfWeek dayOfWeek;
    private LocalTime endTime;
    private LocalTime startTime;
}
