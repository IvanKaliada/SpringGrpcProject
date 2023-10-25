package com.iikaliada.spring.grpc.project.repository;

import com.iikaliada.spring.grpc.project.entity.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Integer> {
}
