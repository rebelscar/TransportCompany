package com.f100385.transportcompany.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    public List<Employee> all(@RequestParam(name = "from") Optional<String> from,
                              @RequestParam(name = "to")   Optional<String> to) {
        return employeeService.getAll(from, to);
    }

    @GetMapping("/{id}")
    public Employee one(@PathVariable int id) {
        return employeeService.get(id);
    }

    @PostMapping
    public void newEmployee(@RequestBody Employee employee) {
        employeeService.add(employee);
    }

    @PutMapping("/{id}")
    public void editEmployee(@PathVariable int id, @RequestBody Employee newEmployee) {
        employeeService.update(id, newEmployee);
    }

    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable int id) {
        employeeService.remove(id);
    }
}
