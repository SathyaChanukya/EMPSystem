package com.example.EmpSystem.Service;

import java.io.PrintWriter;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.EmpSystem.Entity.Employee;

@Service
public class EmpExportService {

	public void exportEmp(List<Employee> emp, PrintWriter writer) {
		 writer.println("id,name,address,email,phnno,salary");

	        for (Employee e : emp) {

	            StringBuilder sb = new StringBuilder();

	            sb.append(e.getId()).append(",");
	            sb.append(e.getName()).append(",");
	            sb.append(e.getAddress()).append(",");
	            sb.append(e.getEmail()).append(",");
	            sb.append(e.getPhnno()).append(",");
	            sb.append(e.getSalary()).append(",");

	            writer.println(sb.toString());
		
	}

}
}
