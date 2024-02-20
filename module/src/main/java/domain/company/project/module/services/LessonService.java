package domain.company.project.module.services;

import domain.company.project.module.domain.entities.Lesson;
import domain.company.project.module.domain.entities.Room;
import domain.company.project.module.domain.entities.Teacher;
import domain.company.project.module.domain.entities.Timeslot;
import domain.company.project.module.dto.request.LessonRequest;
import domain.company.project.module.dto.response.LessonResponse;
import domain.company.project.module.dto.response.RoomResponse;
import domain.company.project.module.dto.response.TeacherResponse;
import domain.company.project.module.dto.response.TimeslotResponse;
import domain.company.project.module.repositories.LessonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LessonService {
    @Autowired
    private LessonRepository lessonRepository;
    @Autowired
    private TeacherService teacherService;

    @Autowired
    private RoomService roomService;
    private TimeslotService timeslotService;


    public LessonResponse createLesson(LessonRequest request) {
        Lesson lesson = new Lesson();
        Teacher teacher = teacherService.findById(request.getTeacherId());
        Room room = roomService.findById(request.getRoomId());
        Timeslot timeslot = timeslotService.findById(request.getRoomId());

        lesson.setSubject(request.getSubject());
        lesson.setTeacher(teacher);
        lesson.setStudentGroup(request.getStudentGroup());
        lesson.setTimeslot(timeslot);
        lesson.setRoom(room);;

        Lesson savedLesson = lessonRepository.save(lesson);

        LessonResponse response = new LessonResponse();
        response.setId(savedLesson.getId());
        response.setSubject(savedLesson.getSubject());
        response.setStudentGroup(savedLesson.getStudentGroup());
        this.setRelatedResponse(savedLesson, response);

        return response;
    }

    public List<LessonResponse> listLessons() {
        List<Lesson> lessones = lessonRepository.findAll();
        return lessones.stream()
                .map(this::convertLessonTo)
                .toList();
    }

    private LessonResponse convertLessonTo(Lesson lesson) {
        LessonResponse response = new LessonResponse();
        response.setId(lesson.getId());
        response.setSubject(lesson.getSubject());
        response.setStudentGroup(lesson.getStudentGroup());
        this.setRelatedResponse(lesson, response);

        return response;
    }

    public Lesson findById(Long id){
        return lessonRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Lesson não encontrado com id" + id +" !"));
    }

    public LessonResponse findLessonById(Long id){
        Lesson lesson = this.findById(id);
        LessonResponse response = new LessonResponse();
        response.setId(lesson.getId());
        response.setSubject(lesson.getSubject());
        response.setStudentGroup(lesson.getStudentGroup());
        this.setRelatedResponse(lesson, response);

        return response;
    }

    public LessonResponse updateLesson(LessonRequest request, Long id) {
        Lesson lesson = this.findById(id);
        Teacher teacher = teacherService.findById(request.getTeacherId());
        Room room = roomService.findById(request.getRoomId());
        Timeslot timeslot = timeslotService.findById(request.getRoomId());

        lesson.setSubject(request.getSubject());
        lesson.setTeacher(teacher);
        lesson.setStudentGroup(request.getStudentGroup());
        lesson.setTimeslot(timeslot);
        lesson.setRoom(room);

        Lesson savedLesson = lessonRepository.save(lesson);

        LessonResponse response = new LessonResponse();
        response.setId(savedLesson.getId());
        response.setSubject(savedLesson.getSubject());
        response.setStudentGroup(savedLesson.getStudentGroup());
        this.setRelatedResponse(savedLesson, response);

        return response;
    }

    public void deleteLesson(Long id){
        Lesson lesson = this.findById(id);
        lessonRepository.delete(lesson);
    }

    private void setRelatedResponse(Lesson lesson, LessonResponse response) {
        TeacherResponse teacherResponse = new TeacherResponse();
        teacherResponse.setId(lesson.getTeacher().getId());
        teacherResponse.setName(lesson.getTeacher().getName());
        teacherResponse.setEmail(lesson.getTeacher().getEmail());
        response.setTeacherResponse(teacherResponse);

        TimeslotResponse timeslotResponse = new TimeslotResponse();
        timeslotResponse.setId(lesson.getTimeslot().getId());
        timeslotResponse.setId(lesson.getTimeslot().getId());
        timeslotResponse.setDayOfWeek(lesson.getTimeslot().getDayOfWeek());
        timeslotResponse.setStartTime(lesson.getTimeslot().getStartTime());
        timeslotResponse.setEndTime(lesson.getTimeslot().getEndTime());
        response.setTimeslotResponse(timeslotResponse);

        RoomResponse roomResponse = new RoomResponse();
        roomResponse.setId(lesson.getRoom().getId());
        roomResponse.setName(lesson.getRoom().getName());
        response.setRoomResponse(roomResponse);
    }
}
