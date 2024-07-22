package com.library.library1.controller;

import com.library.library1.entity.Employee;
import com.library.library1.serviece.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @GetMapping(value = "/employee/get/{e_id}", produces = "application/json")
    public Employee getEmployee(@PathVariable Long e_id) {
        try {
            Employee employee = employeeService.getEmployeeById(e_id);
            if (employee != null) {
                System.out.println(employee);
                return employee;
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    @GetMapping("/employee/get/all")
    public List<Employee> getAllEmployees() {
        try {
            List<Employee> employees = employeeService.getAllEmployees();
            if (employees != null){
                System.out.println(employees);
                return employees;
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @PostMapping("/employee/save")
    public Object saveEmployee(@RequestBody Employee employee) {
        if (employee.getE_name() == null & employee.getE_name() == null){
            return "provide employee";
        }
        Employee employee1 = employeeService.saveEmployee(employee);
        System.out.println(employee1);
        return employee1 + "added successfully";
    }
    @PostMapping("/employee/sava/all")
    public String saveAllEmployees(@RequestBody List<Employee> employees) {
        try {
            List<Employee> employees1 = employeeService.saveAllEmployees(employees);
            System.out.println(employees1);
            return employees1.toString() + "added successfully";
        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @PutMapping("/employee/update/{e_id}")
    public String updateEmployee(@PathVariable Long e_id, @RequestBody Employee employee) {
        try {
            Optional<Employee> employee1 = Optional.ofNullable(employeeService.getEmployeeById(e_id));
            if (employee1.isPresent()) {
                employee.setE_id(e_id);
                employeeService.saveEmployee(employee);
                System.out.println(employee);
                return employee.toString() + "updated successfully";
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return "Employee not found";
    }

    @DeleteMapping("/employee/delete/{e_id}")
    public String deleteEmployee(@PathVariable Long e_id) {
        try {
            Optional<Employee> employee1 = Optional.ofNullable(employeeService.getEmployeeById(e_id));
            if (employee1.isPresent()) {
                employeeService.deleteEmployee(e_id);
                System.out.println(employee1);
                return employee1.toString() + "deleted successfully";
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return "Employee not found to delete";
    }

}
