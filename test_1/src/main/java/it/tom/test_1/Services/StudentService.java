package it.tom.test_1.Services;

import it.tom.test_1.Entities.Student;
import it.tom.test_1.Repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public Student changeIsWorking(Long id, Boolean isWorking) {
        Student student = studentRepository.findById(id).orElse(null);
        if (student == null) {
            return null;
        } else {
            student.setIsWorking(isWorking);
            student = studentRepository.save(student);
            return student;
        }
    }
}
