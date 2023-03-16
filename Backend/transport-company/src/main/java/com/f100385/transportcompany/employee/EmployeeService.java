package com.f100385.transportcompany.employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {
    void add(Employee employee);
    void update(int id, Employee employee);
    void remove(int id);
    List<Employee> getAll();
    Employee get(int id);

    List<Employee> getAll(Optional<String> from, Optional<String> to);
}
