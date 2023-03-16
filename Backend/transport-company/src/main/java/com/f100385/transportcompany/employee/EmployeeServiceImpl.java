package com.f100385.transportcompany.employee;

import com.f100385.transportcompany.other.exception.EmployeeNotFoundException;
import com.f100385.transportcompany.transport_company.TransportCompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private TransportCompanyRepository transportCompanyRepository;

    @Override
    public void add(Employee employee) {
        employee.setTransportCompany(transportCompanyRepository.findTransportCompanyById(1));
        this.employeeRepository.save(employee);
    }

    @Override
    public void update(int id, Employee employee) {
        Optional<Employee> tempEmployee = this.employeeRepository.findById(id);

        if (tempEmployee.isPresent()) {
            Employee validEmployee = tempEmployee.get();

            validEmployee.setFirstName(employee.getFirstName());
            validEmployee.setLastName(employee.getLastName());
            validEmployee.setAge(employee.getAge());
            validEmployee.setSalary(employee.getSalary());
            validEmployee.setQualification(employee.getQualification());
            validEmployee.setTransportCompany(transportCompanyRepository.findTransportCompanyById(1));

            this.employeeRepository.save(validEmployee);
        } else {
            throw new EmployeeNotFoundException(id);
        }
    }

    @Override
    public void remove(int id) {
        this.employeeRepository.deleteById(id);
    }

    @Override
    public List<Employee> getAll() {
        return this.employeeRepository.findAll();
    }

    @Override
    public List<Employee> getAll(Optional<String> from, Optional<String> to) {
        double fromValue = 0;
        double toValue;
        if (from.isPresent() && !from.get().isEmpty()) {

            try {
                double fromConverted = Double.parseDouble(from.get());
                fromValue = fromConverted;
            } catch (NumberFormatException e) {
                fromValue = 0;
            }

            if (fromValue < 0) fromValue = 0;
        }

        if (to.isPresent() && !to.get().isEmpty()) {

            try {
                double toConverted = Double.parseDouble(to.get());
                toValue = toConverted;
            } catch (NumberFormatException e) {
                return this.employeeRepository.findAllBySalaryAfter(fromValue);
            }

            if (toValue < fromValue) toValue = fromValue;

            return this.employeeRepository.findAllBySalaryBetween(fromValue, toValue);
        } else {
            return this.employeeRepository.findAllBySalaryAfter(fromValue);
        }
    }

    @Override
    public Employee get(int id) {
        Optional<Employee> employee = this.employeeRepository.findById(id);
        if (employee.isPresent()) {
            return employee.get();
        } else {
            throw new EmployeeNotFoundException(id);
        }
    }
}
