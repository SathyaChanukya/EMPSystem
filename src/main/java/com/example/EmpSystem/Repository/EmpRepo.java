package com.example.EmpSystem.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.EmpSystem.Entity.Employee;

@Repository
public interface EmpRepo extends JpaRepository<Employee,Integer> {
	List<Employee> findByNameContainingIgnoreCaseOrId(String name,int id);
}
