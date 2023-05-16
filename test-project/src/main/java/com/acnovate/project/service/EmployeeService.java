package com.acnovate.project.service;

import com.acnovate.project.entity.Employee;
import com.acnovate.project.exception.UserNotFoundException;

import java.util.List;
import java.util.Map;

public interface EmployeeService {

    public void saveEmployee(Map<String,String> employee);

    public Map<String,String> getSupervisors(String employeeName)throws UserNotFoundException;

    public List<Employee> getAllEmployees();

}
