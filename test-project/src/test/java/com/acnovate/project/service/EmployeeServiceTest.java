package com.acnovate.project.service;

import com.acnovate.project.entity.Employee;
import com.acnovate.project.exception.UserNotFoundException;
import com.acnovate.project.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;
    private EmployeeServiceImpl employeeService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        employeeService = new EmployeeServiceImpl(employeeRepository);
    }

    @Test
    public void testSaveEmployee() {
        // Arrange
        Map<String, String> employees = new HashMap<>();
        employees.put("Pete", "Nick");
        employees.put("Barbara", "Nick");
        employees.put("Nick","Sophie");
        // Act
        employeeService.saveEmployee(employees);
        // Assert
        ArgumentCaptor<Employee> employeeCaptor = ArgumentCaptor.forClass(Employee.class);
        verify(employeeRepository, times(employees.size())).save(employeeCaptor.capture());
        // Verify the captured employee objects
        for (Employee capturedEmployee : employeeCaptor.getAllValues()) {
            assertEquals(employees.get(capturedEmployee.getEmployeeName()), capturedEmployee.getSupervisor());
        }
      }


    @Test
    public void testGetSupervisors() throws UserNotFoundException {
        // Arrange
        String employeeName = "Pete";
        String supervisorName = "Nick";
        Employee employee = new Employee();
        employee.setEmployeeName(employeeName);
        employee.setSupervisor(supervisorName);
        when(employeeRepository.findByEmployeeName(employeeName)).thenReturn(employee);
        when(employeeRepository.findByEmployeeName(supervisorName)).thenReturn(null);
        // Act
        Map<String, String> result = employeeService.getSupervisors(employeeName);
        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(supervisorName, result.get("supervisor"));
    }

    @Test
    public void testGetAllEmployees() {
        // Arrange
        List<Employee> expectedEmployees = Arrays.asList(
                new Employee(1l,"Pete", "Nick"),
                new Employee(2l,"Barbara", "Nick"),
                new Employee(3l,"Nick", "Sophie"),
                new Employee(4l,"Sophie", "Jonas")
        );
        when(employeeRepository.findAll()).thenReturn(expectedEmployees);
        // Act
        List<Employee> actualEmployees = employeeService.getAllEmployees();
        // Assert
        assertEquals(expectedEmployees, actualEmployees);
        verify(employeeRepository, times(1)).findAll();
    }
}







