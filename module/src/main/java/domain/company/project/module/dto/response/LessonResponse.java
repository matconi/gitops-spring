package domain.company.project.module.dto.response;

import lombok.Data;

@Data
public class LessonResponse {
    private Long id;
    private String subject;
    private TeacherResponse teacherResponse;
    private String studentGroup;
    private TimeslotResponse timeslotResponse;
    private RoomResponse roomResponse;
}
