package domain.company.project.module.dto.request;

import lombok.Data;

import java.util.List;

@Data
public class TeacherRequest {
    private Long id;
    private String name;
    private String email;
    private List<AvailabilityRequest> availabilities;
}
