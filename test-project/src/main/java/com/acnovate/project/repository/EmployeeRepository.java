package com.acnovate.project.repository;

import com.acnovate.project.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long> {
    Employee findByEmployeeName(String employeeName);
    Employee findBySupervisor(String supervisor);
}