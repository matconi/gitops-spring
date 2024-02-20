package domain.company.project.module.controllers;

import domain.company.project.module.dto.request.TimeslotRequest;
import domain.company.project.module.dto.response.TimeslotResponse;
import domain.company.project.module.services.TimeslotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/timeslots")
public class TimeslotController {
    @Autowired
    private TimeslotService timeslotService;

    @PostMapping
    public ResponseEntity<TimeslotResponse> createTimeslot(@RequestBody TimeslotRequest timeslotRequest){
        TimeslotResponse timeslotResponse = timeslotService.createTimeslot(timeslotRequest);
        return new ResponseEntity<>(timeslotResponse, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<TimeslotResponse>> listTimeslots(){
        List<TimeslotResponse> timeslotes = timeslotService.listTimeslots();
        return ResponseEntity.ok(timeslotes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TimeslotResponse> findTimeslot(@PathVariable Long id){
        TimeslotResponse timeslotResponse = timeslotService.findTimeslotById(id);
        return ResponseEntity.ok(timeslotResponse);
    }

    @PutMapping
    public ResponseEntity<TimeslotResponse> updateTimeslot(@RequestBody TimeslotRequest timeslotRequest, @PathVariable Long id){
        TimeslotResponse timeslotResponse = timeslotService.updateTimeslot(timeslotRequest, id);
        return new ResponseEntity<>(timeslotResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteTimeslot(@PathVariable Long id){
        timeslotService.deleteTimeslot(id);
        return ResponseEntity.noContent().build();
    }
}
