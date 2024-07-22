package com.library.library1.controller;

import com.library.library1.entity.Employee;
import com.library.library1.entity.Student;
import com.library.library1.serviece.EmployeeService;
import com.library.library1.serviece.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    @Autowired
    private StudentService studentService;
    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/login")
    public String login(@RequestParam Long username, @RequestParam String password,@RequestParam String type) {
        if (type.equalsIgnoreCase("student")){
            Student student = studentService.getStudent(username);
            if (password.equals(student.getStudent_password())){
                System.out.println("login success "+ studentService.getStudent(username));
                return "student login success";
            }
            System.out.println("login fail");
            return "student login fail";
        }
        if (type.equalsIgnoreCase("employee")){
            Employee employee = employeeService.getEmployeeById(username);
            if(password.equals(employee.getE_password())){
                System.out.println("login success "+ employeeService.getEmployeeById(username));
                return "employee login success";
            }
            System.out.println("login fail");
            return "employee login fail";
        }
        return "to login you have to be a student or a employee in organization";
    }
}
