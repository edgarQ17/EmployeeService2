package com.quintero.service;

import com.quintero.exception_handler.EmployeeNotFound;
import com.quintero.model.Department;
import com.quintero.model.DepartmentRepository;
import com.quintero.model.Employee;
import com.quintero.model.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
public class EmployeeRepoController {
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    DepartmentRepository departmentRepository;
    @GetMapping("/jpa/employees")
    public List<Employee> getAll(){

        return employeeRepository.findAll();
    }


    @GetMapping("/jpa/employees/{empId}")
    public EntityModel<Employee> getEmployeeById(@PathVariable long empId) {
        Employee employee = employeeRepository.findById(empId).get();

        if (null == employee)
            throw new EmployeeNotFound("Employee not found.");
        ///create a model to show all employees
        EntityModel<Employee> model = EntityModel.of(employee);
        Link link = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAll()).withRel("all-employees");
        ///add link to model
        model.add(link);
        return model;
    }

    @PostMapping("/jpa/employees/user")
    public ResponseEntity<Object> saveEmployee(@Valid @RequestBody Employee emp){
        Employee newEmployee = employeeRepository.save(emp);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("{employeeId}")
                .buildAndExpand(newEmployee.getId())
                .toUri();
        ///this helps create a URI which similar to saying created by using the Header
////
        return ResponseEntity.created(uri).build();

    }

    @DeleteMapping(path="/employees/delete/{empId}")
    public void deleteEmployee(@PathVariable long empId){
        Employee emp = employeeRepository.getById(empId);
        if(null == emp)
            throw new EmployeeNotFound("Employee not found.");
        employeeRepository.deleteById(empId);
    }

    ///this method adds employees id to a department and creates the department with unique id if doesn't exists
    @PostMapping("/jpa/adddepartment/{empId}")
    public ResponseEntity<Object> saveEmployee(@PathVariable Long empId, @RequestBody Department department){
        Employee employee = employeeRepository.findById(empId).get();
        if (null == employee)
            throw new EmployeeNotFound("Employee not found.");

        department.setEmployee(employee);///this adds it to the dpartment object
        departmentRepository.save(department);//this adds it to db saves using JPA MEthods
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("{empId}")
                .buildAndExpand(employee.getId())
                .toUri();
        ///this helps create a URI which similar to saying created by using the Header

        return ResponseEntity.created(uri).build();

    }






}
