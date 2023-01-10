package com.quintero.service;

import com.quintero.exception_handler.EmployeeNotFound;
import com.quintero.model.Employee;
import com.quintero.model.EmployeeDao;
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
public class EmployeeController {

    @Autowired      //creates object for us
    EmployeeDao service;
    @GetMapping("/employees")
    public List<Employee> getAll(){
        return service.getAllEmployees();
    }

    @GetMapping("/employees/{empId}")
    public EntityModel<Employee> getEmployeeById(@PathVariable int empId){
        Employee employee = service.getEmployeeById(empId);

        if(null == employee)
            throw new EmployeeNotFound("Employee not found.");
        ///create a model to show all employees
        EntityModel<Employee> model = EntityModel.of(employee);
        Link link = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAll()).withRel("all-employees");
        ///add link to model
        model.add(link);
        return model;
    }

    @PostMapping("/employees/user")
    public ResponseEntity<Object> saveEmployee(@Valid @RequestBody Employee emp){
        Employee newEmployee = service.saveEmployee(emp);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("{employeeId}")
                .buildAndExpand(newEmployee.getEmployeeId())
                .toUri();
        ///this helps create a URI which similar to saying created by using the Header

        return ResponseEntity.created(uri).build();

    }

    @DeleteMapping(path="/employees/delete/{empId}")
    public void deleteEmployee(@PathVariable int empId){
        Employee emp = service.deleteEmployee(empId);
        if(null == emp)
            throw new EmployeeNotFound("Employee not found.");
    }


}
