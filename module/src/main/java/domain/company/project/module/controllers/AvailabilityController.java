package domain.company.project.module.controllers;

import domain.company.project.module.dto.request.solver.AvailabilitySolverRequest;
import domain.company.project.module.dto.response.AvailabilityResponse;
import domain.company.project.module.services.AvailabilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/availabilities")
public class AvailabilityController {
    @Autowired
    private AvailabilityService availabilityService;

    @PostMapping
    public ResponseEntity<AvailabilityResponse> createAvailability(@RequestBody AvailabilitySolverRequest availabilityRequest){
        AvailabilityResponse availabilityResponse = availabilityService.createAvailability(availabilityRequest);
        return new ResponseEntity<>(availabilityResponse, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<AvailabilityResponse>> listAvailabilities(){
        List<AvailabilityResponse> availabilities = availabilityService.listAvailabilities();
        return ResponseEntity.ok(availabilities);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AvailabilityResponse> findAvailability(@PathVariable Long id){
        AvailabilityResponse availabilityResponse = availabilityService.findAvailabilityById(id);
        return ResponseEntity.ok(availabilityResponse);
    }

    @PutMapping
    public ResponseEntity<AvailabilityResponse> updateAvailability(@RequestBody AvailabilitySolverRequest availabilityRequest, @PathVariable Long id){
        AvailabilityResponse availabilityResponse = availabilityService.updateAvailability(availabilityRequest, id);
        return new ResponseEntity<>(availabilityResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteAvailability(@PathVariable Long id){
        availabilityService.deleteAvailability(id);
        return ResponseEntity.noContent().build();
    }
}
