package domain.company.project.module.controllers;

import domain.company.project.module.dto.request.TeacherRequest;
import domain.company.project.module.dto.response.TeacherResponse;
import domain.company.project.module.services.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teachers")
public class TeacherController {
    @Autowired
    private TeacherService teacherService;

    @PostMapping
    public ResponseEntity<TeacherResponse> createTeacher(@RequestBody TeacherRequest teacherRequest){
        TeacherResponse teacherResponse = teacherService.createTeacher(teacherRequest);
        return new ResponseEntity<>(teacherResponse, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<TeacherResponse>> listTeachers(){
        List<TeacherResponse> teacheres = teacherService.listTeachers();
        return ResponseEntity.ok(teacheres);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TeacherResponse> findTeacher(@PathVariable Long id){
        TeacherResponse teacherResponse = teacherService.findTeacherById(id);
        return ResponseEntity.ok(teacherResponse);
    }

    @PutMapping
    public ResponseEntity<TeacherResponse> updateTeacher(@RequestBody TeacherRequest teacherRequest, @PathVariable Long id){
        TeacherResponse teacherResponse = teacherService.updateTeacher(teacherRequest, id);
        return new ResponseEntity<>(teacherResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteTeacher(@PathVariable Long id){
        teacherService.deleteTeacher(id);
        return ResponseEntity.noContent().build();
    }
}
