package domain.company.project.module.services;

import domain.company.project.module.domain.entities.Teacher;
import domain.company.project.module.dto.request.TeacherRequest;
import domain.company.project.module.dto.response.TeacherResponse;
import domain.company.project.module.repositories.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherService {
    @Autowired
    private TeacherRepository teacherRepository;

    public TeacherResponse createTeacher(TeacherRequest request) {
        Teacher teacher = new Teacher();
        teacher.setName(request.getName());
        teacher.setEmail(request.getEmail());

        Teacher savedTeacher = teacherRepository.save(teacher);

        TeacherResponse response = new TeacherResponse();
        response.setId(savedTeacher.getId());
        response.setName(savedTeacher.getName());
        response.setEmail(savedTeacher.getEmail());

        return response;
    }

    public List<TeacherResponse> listTeachers() {
        List<Teacher> teacheres = teacherRepository.findAll();
        return teacheres.stream()
                .map(this::convertTeacherTo)
                .toList();
    }

    private TeacherResponse convertTeacherTo(Teacher teacher) {
        TeacherResponse response = new TeacherResponse();
        response.setId(teacher.getId());
        response.setName(teacher.getName());
        response.setEmail(teacher.getEmail());

        return response;
    }

    public Teacher findById(Long id){
        return teacherRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Teacher não encontrado com id" + id +" !"));
    }

    public TeacherResponse findTeacherById(Long id){
        Teacher teacher = this.findById(id);
        TeacherResponse response = new TeacherResponse();
        response.setId(teacher.getId());
        response.setName(teacher.getName());
        response.setEmail(teacher.getEmail());
        return response;
    }

    public TeacherResponse updateTeacher(TeacherRequest request, Long id) {
        Teacher teacher = this.findById(id);
        teacher.setName(request.getName());
        teacher.setEmail(request.getEmail());

        Teacher savedTeacher = teacherRepository.save(teacher);

        TeacherResponse response = new TeacherResponse();
        response.setId(savedTeacher.getId());
        response.setName(savedTeacher.getName());
        response.setEmail(savedTeacher.getEmail());

        return response;
    }

    public void deleteTeacher(Long id){
        Teacher teacher = this.findById(id);
        teacherRepository.delete(teacher);
    }
}
