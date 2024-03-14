package domain.company.project.module.dto.request;

import lombok.Data;

@Data
public class DefaultLessonRequest {
    private Long id;
    private String subject;
    private Long teacherId;
    private String studentGroup;
    private Long timeslotId;
    private Long roomId;
}
