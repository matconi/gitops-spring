package domain.company.project.module.controllers;

import domain.company.project.module.dto.request.RoomRequest;
import domain.company.project.module.dto.response.RoomResponse;
import domain.company.project.module.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rooms")
public class RoomController {
    @Autowired
    private RoomService roomService;

    @PostMapping
    public ResponseEntity<RoomResponse> createRoom(@RequestBody RoomRequest roomRequest){
        RoomResponse roomResponse = roomService.createRoom(roomRequest);
        return new ResponseEntity<>(roomResponse, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<RoomResponse>> listRooms(){
        List<RoomResponse> roomes = roomService.listRooms();
        return ResponseEntity.ok(roomes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoomResponse> findRoom(@PathVariable Long id){
        RoomResponse roomResponse = roomService.findRoomById(id);
        return ResponseEntity.ok(roomResponse);
    }

    @PutMapping
    public ResponseEntity<RoomResponse> updateRoom(@RequestBody RoomRequest roomRequest, @PathVariable Long id){
        RoomResponse roomResponse = roomService.updateRoom(roomRequest, id);
        return new ResponseEntity<>(roomResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteRoom(@PathVariable Long id){
        roomService.deleteRoom(id);
        return ResponseEntity.noContent().build();
    }
}
