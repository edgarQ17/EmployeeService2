package com.quintero.model;


import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Size(min=3,max=5,message = "Invalid chars for name")
    private String name;
    @Email       //automatic validation from validation
    private String email;

    @OneToMany(mappedBy = "department")
    private List<Department> allDepartments;
    public Employee() {
    }

    public Employee(int employeeId, String name, String email) {
        this.id = employeeId;
        this.name = name;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Department> getAllDepartments() {
        return allDepartments;
    }

    public void setAllDepartments(List<Department> allDepartments) {
        this.allDepartments = allDepartments;
    }
}
