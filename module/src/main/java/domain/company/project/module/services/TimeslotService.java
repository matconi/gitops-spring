package domain.company.project.module.services;

import domain.company.project.module.domain.entities.Timeslot;
import domain.company.project.module.dto.request.TimeslotRequest;
import domain.company.project.module.dto.response.TimeslotResponse;
import domain.company.project.module.repositories.TimeslotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TimeslotService {
    @Autowired
    private TimeslotRepository timeslotRepository;

    public TimeslotResponse createTimeslot(TimeslotRequest request) {
        Timeslot timeslot = new Timeslot();
        timeslot.setDayOfWeek(request.getDayOfWeek());
        timeslot.setStartTime(request.getStartTime());
        timeslot.setEndTime(request.getEndTime());

        Timeslot savedTimeslot = timeslotRepository.save(timeslot);

        TimeslotResponse response = new TimeslotResponse();
        response.setId(savedTimeslot.getId());
        response.setDayOfWeek(response.getDayOfWeek());
        response.setStartTime(response.getStartTime());
        response.setEndTime(response.getEndTime());

        return response;
    }

    public List<TimeslotResponse> listTimeslots() {
        List<Timeslot> timeslotes = timeslotRepository.findAll();
        return timeslotes.stream()
                .map(this::convertTimeslotTo)
                .toList();
    }

    private TimeslotResponse convertTimeslotTo(Timeslot timeslot) {
        TimeslotResponse response = new TimeslotResponse();
        response.setId(timeslot.getId());
        response.setDayOfWeek(timeslot.getDayOfWeek());
        response.setStartTime(timeslot.getStartTime());
        response.setEndTime(timeslot.getEndTime());

        return response;
    }

    public Timeslot findById(Long id){
        return timeslotRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Timeslot não encontrado com id" + id +" !"));
    }

    public TimeslotResponse findTimeslotById(Long id){
        Timeslot timeslot = this.findById(id);
        TimeslotResponse response = new TimeslotResponse();
        response.setId(timeslot.getId());
        response.setDayOfWeek(response.getDayOfWeek());
        response.setStartTime(response.getStartTime());
        response.setEndTime(response.getEndTime());
        return response;
    }

    public TimeslotResponse updateTimeslot(TimeslotRequest request, Long id) {
        Timeslot timeslot = this.findById(id);
        timeslot.setDayOfWeek(request.getDayOfWeek());
        timeslot.setStartTime(request.getStartTime());
        timeslot.setEndTime(request.getEndTime());

        Timeslot savedTimeslot = timeslotRepository.save(timeslot);

        TimeslotResponse response = new TimeslotResponse();
        response.setId(savedTimeslot.getId());
        response.setDayOfWeek(response.getDayOfWeek());
        response.setStartTime(response.getStartTime());
        response.setEndTime(response.getEndTime());

        return response;
    }

    public void deleteTimeslot(Long id){
        Timeslot timeslot = this.findById(id);
        timeslotRepository.delete(timeslot);
    }
}
