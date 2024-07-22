package com.library.library1.serviece;

import com.library.library1.Repository.EmployeeRepo;
import com.library.library1.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepo employeeRepo;

    public List<Employee> getAllEmployees() {
        return employeeRepo.findAll();
    }
    public Employee getEmployeeById(Long e_id) {
        return employeeRepo.findById(e_id).get();
    }
    public Employee saveEmployee(Employee employee) {
        return employeeRepo.save(employee);
    }
    public List<Employee> saveAllEmployees(List<Employee> employees) {
        return employeeRepo.saveAll(employees);
    }
    public Employee updateEmployee(Employee employee) {
        return employeeRepo.save(employee);
    }
    public void deleteEmployee(Long e_id) {
        employeeRepo.deleteById(e_id);
    }
}
