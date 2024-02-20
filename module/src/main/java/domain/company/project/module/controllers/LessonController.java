package domain.company.project.module.controllers;

import domain.company.project.module.dto.request.LessonRequest;
import domain.company.project.module.dto.response.LessonResponse;
import domain.company.project.module.services.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/lessons")
public class LessonController {
    @Autowired
    private LessonService lessonService;

    @PostMapping
    public ResponseEntity<LessonResponse> createLesson(@RequestBody LessonRequest lessonRequest){
        LessonResponse lessonResponse = lessonService.createLesson(lessonRequest);
        return new ResponseEntity<>(lessonResponse, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<LessonResponse>> listLessons(){
        List<LessonResponse> lessones = lessonService.listLessons();
        return ResponseEntity.ok(lessones);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LessonResponse> findLesson(@PathVariable Long id){
        LessonResponse lessonResponse = lessonService.findLessonById(id);
        return ResponseEntity.ok(lessonResponse);
    }

    @PutMapping
    public ResponseEntity<LessonResponse> updateLesson(@RequestBody LessonRequest lessonRequest, @PathVariable Long id){
        LessonResponse lessonResponse = lessonService.updateLesson(lessonRequest, id);
        return new ResponseEntity<>(lessonResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteLesson(@PathVariable Long id){
        lessonService.deleteLesson(id);
        return ResponseEntity.noContent().build();
    }
}
