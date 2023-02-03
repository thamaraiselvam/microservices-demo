package com.example.employeeservice.controller;

import java.util.List;

import com.example.employeeservice.model.Employee;
import com.example.employeeservice.repository.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import static net.logstash.logback.argument.StructuredArguments.keyValue;

@RestController
public class EmployeeController {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeController.class);

    @Autowired
    EmployeeRepository repository;

    @PostMapping("/")
    public Employee add(@RequestBody Employee employee) {
        LOGGER.info("Add employee", keyValue("employee", employee), keyValue("endpoint", "/"));
        return repository.save(employee);
    }

    @GetMapping("/{id}")
    public Employee findById(@PathVariable("id") String id) {
        LOGGER.info("Find employee", keyValue("id", id), keyValue("endpoint", "/{id}"));
        return repository.findById(id).get();
    }

    @GetMapping("/")
    public Iterable<Employee> findAll() {
        LOGGER.info("Find all employees", keyValue("endpoint", "/"));
        return repository.findAll();
    }

    @GetMapping("/department/{departmentId}")
    public List<Employee> findByDepartment(@PathVariable("departmentId") String departmentId) {
        LOGGER.info("Find employee by departmentId", keyValue("departmentId", departmentId), keyValue("endpoint", "/department/{departmentId}"));
        return repository.findByDepartmentId(departmentId);
    }

    @GetMapping("/organization/{organizationId}")
    public List<Employee> findByOrganization(@PathVariable("organizationId") String organizationId) {
        LOGGER.info("Find employee by organizationId", keyValue("organizationId", organizationId), keyValue("endpoint", "/organizationId/{organizationId}"));
        return repository.findByOrganizationId(organizationId);
    }

}