package domain.company.project.module.services;

import domain.company.project.module.domain.entities.Availability;
import domain.company.project.module.domain.entities.Room;
import domain.company.project.module.domain.entities.Teacher;
import domain.company.project.module.domain.entities.Timeslot;
import domain.company.project.module.dto.request.AvailabilityRequest;
import domain.company.project.module.dto.response.AvailabilityResponse;
import domain.company.project.module.dto.response.RoomResponse;
import domain.company.project.module.dto.response.TeacherResponse;
import domain.company.project.module.dto.response.TimeslotResponse;
import domain.company.project.module.repositories.AvailabilityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AvailabilityService {
    @Autowired
    private AvailabilityRepository availabilityRepository;
    @Autowired
    private TeacherService teacherService;

    @Autowired
    private RoomService roomService;
    private TimeslotService timeslotService;


    public AvailabilityResponse createAvailability(AvailabilityRequest request) {
        Availability availability = new Availability();
        Teacher teacher = teacherService.findById(request.getTeacherId());

        availability.setDayOfWeek(request.getDayOfWeek());
        availability.setTeacher(teacher);
        availability.setEndTime(request.getEndTime());
        availability.setStartTime(request.getStartTime());

        Availability savedAvailability = availabilityRepository.save(availability);

        AvailabilityResponse response = new AvailabilityResponse();
        response.setId(savedAvailability.getId());
        response.setDayOfWeek(response.getDayOfWeek());
        response.setEndTime(response.getEndTime());
        response.setStartTime(response.getStartTime());
        this.setRelatedResponse(savedAvailability, response);

        return response;
    }

    public List<AvailabilityResponse> listAvailabilities() {
        List<Availability> availabilities = availabilityRepository.findAll();
        return availabilities.stream()
                .map(this::convertAvailabilityTo)
                .toList();
    }

    private AvailabilityResponse convertAvailabilityTo(Availability availability) {
        AvailabilityResponse response = new AvailabilityResponse();
        response.setId(availability.getId());
        response.setDayOfWeek(response.getDayOfWeek());
        response.setEndTime(response.getEndTime());
        response.setStartTime(response.getStartTime());
        this.setRelatedResponse(availability, response);

        return response;
    }

    public Availability findById(Long id){
        return availabilityRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Availability não encontrado com id" + id +" !"));
    }

    public AvailabilityResponse findAvailabilityById(Long id){
        Availability availability = this.findById(id);
        AvailabilityResponse response = new AvailabilityResponse();
        response.setId(availability.getId());
        response.setDayOfWeek(response.getDayOfWeek());
        response.setEndTime(response.getEndTime());
        response.setStartTime(response.getStartTime());
        this.setRelatedResponse(availability, response);

        return response;
    }

    public AvailabilityResponse updateAvailability(AvailabilityRequest request, Long id) {
        Availability availability = this.findById(id);
        Teacher teacher = teacherService.findById(request.getTeacherId());

        availability.setDayOfWeek(request.getDayOfWeek());
        availability.setTeacher(teacher);
        availability.setEndTime(request.getEndTime());
        availability.setStartTime(request.getStartTime());

        Availability savedAvailability = availabilityRepository.save(availability);

        AvailabilityResponse response = new AvailabilityResponse();
        response.setId(savedAvailability.getId());
        response.setDayOfWeek(response.getDayOfWeek());
        response.setEndTime(response.getEndTime());
        response.setStartTime(response.getStartTime());
        this.setRelatedResponse(savedAvailability, response);

        return response;
    }

    public void deleteAvailability(Long id){
        Availability availability = this.findById(id);
        availabilityRepository.delete(availability);
    }

    private void setRelatedResponse(Availability availability, AvailabilityResponse response) {
        TeacherResponse teacherResponse = new TeacherResponse();
        teacherResponse.setId(availability.getTeacher().getId());
        teacherResponse.setName(availability.getTeacher().getName());
        teacherResponse.setEmail(availability.getTeacher().getEmail());
        response.setTeacherResponse(teacherResponse);

    }
}
