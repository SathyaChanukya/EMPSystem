package com.example.EmpSystem.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.EmpSystem.Entity.Employee;
import com.example.EmpSystem.Repository.EmpRepo;

@Service
public class EmpService {
	
	@Autowired
	private EmpRepo repo;
	
	public void addEmp(Employee e) {
		repo.save(e);
	}
	
	public List<Employee> getAllEmp(){
		return repo.findAll();
	}
	
	public Employee getEmployeeById(int id) {
		Optional<Employee> e=repo.findById(id);
		
		if(e.isPresent()) {
			return e.get();
		}
		return null;
	}

	public void deleteEmployeeById(int id) {
		  repo.deleteById(id);
	}
	
	public List<Employee> search(String name,int id){
		return repo.findByNameContainingIgnoreCaseOrId(name, id);
	}

}
