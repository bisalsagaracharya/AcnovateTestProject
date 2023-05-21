package com.acnovate.project.service;

import com.acnovate.project.entity.Employee;
import com.acnovate.project.exception.UserNotFoundException;
import com.acnovate.project.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public void saveEmployee(Map<String, String> employee) {
        employee.forEach((employeeName, supervisor) -> {
            Employee employee1 = new Employee();
            employee1.setEmployeeName(employeeName);
            employee1.setSupervisor(supervisor);
            employeeRepository.save(employee1);
        });
    }

    @Override
    public Map<String, String> getSupervisors(String employeeName)throws UserNotFoundException {
        Employee employee = employeeRepository.findByEmployeeName(employeeName);
        if (employee == null) {
           throw new UserNotFoundException("No Supervisors found for employee",employeeName);
        }
        Map<String, String> supervisors = new HashMap<>();
        supervisors.put("supervisorName", employee.getSupervisor());
        Employee supervisor = employeeRepository.findByEmployeeName(employee.getSupervisor());
        if (supervisor != null) {
            supervisors.put("supervisorOfSupervisorName", supervisor.getSupervisor());
        }
        return supervisors;
    }

    @Override
    public List<Employee> getAllEmployees() {
        return this.employeeRepository.findAll();
    }

}

