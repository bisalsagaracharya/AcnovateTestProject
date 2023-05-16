package com.acnovate.project.controller;

import com.acnovate.project.entity.Employee;
import com.acnovate.project.exception.UserNotFoundException;
import com.acnovate.project.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping("/employees")
    public ResponseEntity<String> saveEmployees(@RequestBody Map<String, String> employees) {
        this.employeeService.saveEmployee(employees);
        return ResponseEntity.ok("employee add successfully");
    }

    @GetMapping("/employees/{employeeName}/supervisor")
    public ResponseEntity<Map<String, String>> getSupervisors(@PathVariable String employeeName) throws UserNotFoundException {
        Map<String, String> employeeSupervisors= this.employeeService.getSupervisors(employeeName);
        if(employeeSupervisors==null){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(employeeSupervisors);
    }

    @GetMapping("/allEmployees")
    public List<Employee> getAllEmployees(){
        return this.employeeService.getAllEmployees();
   }
}
