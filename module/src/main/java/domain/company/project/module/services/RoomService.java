package domain.company.project.module.services;

import domain.company.project.module.domain.entities.Room;
import domain.company.project.module.dto.request.RoomRequest;
import domain.company.project.module.dto.response.RoomResponse;
import domain.company.project.module.repositories.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomService {
    @Autowired
    private RoomRepository roomRepository;

    public RoomResponse createRoom(RoomRequest request) {
        Room room = new Room();
        room.setName(request.getName());

        Room savedRoom = roomRepository.save(room);

        RoomResponse response = new RoomResponse();
        response.setId(savedRoom.getId());
        response.setName(savedRoom.getName());

        return response;
    }

    public List<RoomResponse> listRooms() {
        List<Room> roomes = roomRepository.findAll();
        return roomes.stream()
                .map(this::convertRoomTo)
                .toList();
    }

    private RoomResponse convertRoomTo(Room room) {
        RoomResponse response = new RoomResponse();
        response.setId(room.getId());
        response.setName(room.getName());

        return response;
    }

    public Room findById(Long id){
        return roomRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Room não encontrado com id" + id +" !"));
    }

    public RoomResponse findRoomById(Long id){
        Room room = this.findById(id);
        RoomResponse response = new RoomResponse();
        response.setId(room.getId());
        response.setName(room.getName());
        return response;
    }

    public RoomResponse updateRoom(RoomRequest request, Long id) {
        Room room = this.findById(id);
        room.setName(request.getName());

        Room savedRoom = roomRepository.save(room);

        RoomResponse response = new RoomResponse();
        response.setId(savedRoom.getId());
        response.setName(savedRoom.getName());

        return response;
    }

    public void deleteRoom(Long id){
        Room room = this.findById(id);
        roomRepository.delete(room);
    }
}
