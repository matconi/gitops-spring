package domain.company.project.module.dto.request;

import lombok.Data;

import java.time.DayOfWeek;
import java.time.LocalTime;

@Data
public class TimeslotRequest {
    private DayOfWeek dayOfWeek;
    private LocalTime startTime;
    private LocalTime endTime;
}
