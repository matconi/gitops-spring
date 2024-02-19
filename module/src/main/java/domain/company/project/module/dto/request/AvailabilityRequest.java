package domain.company.project.module.dto.request;

import lombok.Data;

import java.time.DayOfWeek;
import java.time.LocalTime;

@Data
public class AvailabilityRequest {
    private Long teacherId;
    private DayOfWeek dayOfWeek;
    private LocalTime time;
}
