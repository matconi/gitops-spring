package domain.company.project.module.dto.response;

import lombok.Data;

import java.time.DayOfWeek;
import java.time.LocalTime;

@Data
public class TimeslotResponse {
    private Long id;
    private DayOfWeek dayOfWeek;
    private LocalTime startTime;
    private LocalTime endTime;
}
