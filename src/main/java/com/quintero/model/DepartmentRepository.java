package com.quintero.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

///extends JpaRepository<Department,long> class and Id type
@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {



}
