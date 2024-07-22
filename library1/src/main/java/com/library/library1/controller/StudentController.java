package com.library.library1.controller;

import com.library.library1.entity.Student;
import com.library.library1.serviece.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class StudentController {
    @Autowired
    private StudentService studentService;

    @GetMapping("/student/get/{student_id}")
    public Student getStudent(@PathVariable Long student_id) {
        try {
            Student student = studentService.getStudent(student_id);
            if (student != null){
                System.out.println(student);
                return student;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @GetMapping("/student/get/all")
    public List<Student> getAllStudents() {
        try {
            List<Student> students = studentService.getAllStudents();
            if (students != null){
                System.out.println(students);
                return students;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @PostMapping("/student/save")
    public String addStudent(@RequestBody Student student) {

        if (student.getStudent_name() == null & student.getStudent_email() == null){
            return "student not saved";
        }
        Student student1 = studentService.addStudent(student);
        System.out.println(student1);

        return student.toString() + "added successfully";
    }

    @PostMapping("/student/save/all")
    public String saveAllStudents(@RequestBody List<Student> students) {
        if (students == null){
            return "provide students";
        }
        List<Student> students1 = studentService.saveStudents(students);
        System.out.println(students1);
        return students1.toString() + "added successfully";
    }

    @PutMapping("/student/update/{student_id}")
    public String updateStudent(@RequestBody Student student, @PathVariable Long student_id) {
        try {
            Optional<Student> student1 = Optional.ofNullable(studentService.getStudent(student_id));
            if(student1.isPresent()){
                student.setStudent_id(student_id);
                studentService.updateStudent(student);
                System.out.println("update successfully"+student);
            }
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("update failed");
            return "student not updated";
        }
        return "student updated successfully";
    }

    @DeleteMapping("/student/delete/{student_id}")
    public String deleteStudent(@PathVariable Long student_id) {
        try {
            Optional<Student> student = Optional.ofNullable(studentService.getStudent(student_id));
            if (student.isPresent()){
                studentService.deleteStudent(student_id);
                System.out.println("delete successfully"+student);
                return "student deleted successfully";
            }
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("delete failed");
        }
        return "student not found to delete";
    }
}
