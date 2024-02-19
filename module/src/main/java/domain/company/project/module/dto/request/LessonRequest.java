package domain.company.project.module.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
public class LessonRequest {
    private Long id;
    private String subject;
    private Long teacherId;
    private String studentGroup;
    private Long timeslotId;
    private Long roomId;
}
