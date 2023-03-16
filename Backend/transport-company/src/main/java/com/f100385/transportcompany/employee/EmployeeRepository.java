package com.f100385.transportcompany.employee;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    void deleteById(Integer id);
    List<Employee> findAllBySalaryBetween(double salaryStart, double salaryEnd);
    List<Employee> findAllBySalaryBefore(double value);
    List<Employee> findAllBySalaryAfter(double value);
}
