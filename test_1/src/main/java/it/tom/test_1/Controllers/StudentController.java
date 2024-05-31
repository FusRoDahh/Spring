package it.tom.test_1.Controllers;

import it.tom.test_1.Entities.Student;
import it.tom.test_1.Repositories.StudentRepository;
import it.tom.test_1.Services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;
    @Autowired
    private StudentRepository studentRepository;

    @PostMapping
    public Student postStudent(@RequestBody Student student) {
        return studentRepository.save(student);
    }

    @GetMapping("/list")
    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    @GetMapping("/{id}")
    public Student getStudent(@PathVariable Long id) {
        return studentRepository.findById(id).orElse(new Student());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateStudent(@PathVariable Long id, @RequestBody Student student) {
        Student studentToUpdate = studentRepository.findById(id).orElse(null);
        if (studentToUpdate == null) {
            return ResponseEntity.notFound().build();
        } else {
            studentToUpdate.setName(student.getName());
            studentToUpdate.setSurname(student.getSurname());
            studentToUpdate.setIsWorking(student.getIsWorking());
            Student updatedStudent = studentRepository.save(studentToUpdate);
            return ResponseEntity.ok(updatedStudent);
        }
    }

    @PatchMapping("/isWorking/{id}")
    public Student updateIsWorking(@PathVariable Long id, @RequestBody Boolean working) {
        return studentService.changeIsWorking(id, working);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable Long id) {
        Student student = studentRepository.findById(id).orElse(null);
        if (student == null) {
            return ResponseEntity.notFound().build();
        } else {
            studentRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
    }

}
