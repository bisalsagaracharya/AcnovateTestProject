package com.acnovate.project.controller;

import com.acnovate.project.entity.Employee;
import com.acnovate.project.exception.UserNotFoundException;
import com.acnovate.project.service.EmployeeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class EmployeeControllerTest {

    @Mock
    private EmployeeService employeeService;
    private EmployeeController employeeController;
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        employeeController = new EmployeeController(employeeService);
        objectMapper = new ObjectMapper();
    }
    @Test
    public void testSaveEmployees() throws Exception {
        // Arrange
        Map<String, String> employees = new HashMap<>();
        employees.put("Pete", "Nick");
        employees.put("Barbara", "Nick");
        employees.put("Nick","Sophie");
        // Act
        ResponseEntity<String> response = employeeController.saveEmployees(employees);
        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("employee add successfully", response.getBody());
        verify(employeeService,times(1)).saveEmployee(employees);
    }

    @Test
    public void testGetSupervisors() throws UserNotFoundException {
        // Arrange
        String employeeName = "Pete";
        Map<String, String> expectedSupervisors = new HashMap<>();
        expectedSupervisors.put("supervisor", "Nick");
        when(employeeService.getSupervisors(employeeName)).thenReturn(expectedSupervisors);
        // Act
        ResponseEntity<Map<String, String>> response = employeeController.getSupervisors(employeeName);
        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedSupervisors, response.getBody());

        verify(employeeService, times(1)).getSupervisors(employeeName);
    }

    @Test
    public void testGetAllEmployees() {
        // Arrange
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(1l,"Pete", "Nick"));
        employees.add(new Employee(2l,"Barbara", "Nick"));
        employees.add(new Employee(3l,"Nick","Sophie"));
        employees.add(new Employee(4l,"Sophie","Jonas"));
        when(employeeService.getAllEmployees()).thenReturn(employees);
        // Act
        List<Employee> actualEmployees = employeeController.getAllEmployees();
        // Assert
        assertEquals(employees.size(), actualEmployees.size());
        assertEquals(employees.get(0).getEmployeeName(), actualEmployees.get(0).getEmployeeName());
        assertEquals(employees.get(0).getSupervisor(), actualEmployees.get(0).getSupervisor());
        assertEquals(employees.get(1).getEmployeeName(), actualEmployees.get(1).getEmployeeName());
        assertEquals(employees.get(1).getSupervisor(), actualEmployees.get(1).getSupervisor());
        verify(employeeService, times(1)).getAllEmployees();
    }

}



