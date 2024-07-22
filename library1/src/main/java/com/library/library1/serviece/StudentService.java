package com.library.library1.serviece;

import com.library.library1.Repository.StudentRepo;
import com.library.library1.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    @Autowired
    private StudentRepo studentRepo;
    
    public Student getStudent(Long student_id) {
        return studentRepo.findById(student_id).get();
    }

    public List<Student> getAllStudents() {
        return studentRepo.findAll();
    }

    public Student addStudent(Student student) {
        return studentRepo.save(student);
    }

    public List<Student> saveStudents(List<Student> students) {
        return studentRepo.saveAll(students);
    }

    public Student updateStudent(Student student) {
        return studentRepo.save(student);
    }

    public void deleteStudent(Long student_id) {
        studentRepo.deleteById(student_id);
    }
}
