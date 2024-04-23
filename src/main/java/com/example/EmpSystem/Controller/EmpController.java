package com.example.EmpSystem.Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.EmpSystem.Entity.Employee;
import com.example.EmpSystem.Service.EmpExportService;
import com.example.EmpSystem.Service.EmpService;

import jakarta.servlet.http.HttpServletResponse;


@Controller
public class EmpController {
	
	@Autowired
	private EmpService service;
	
	@Autowired
	private EmpExportService export;
	
	@GetMapping("/")
	public String homeEmp() {
		return "home";
	}
	
	@GetMapping("/dashboard")
	public String dashboardEmp(Model m) {
		List<Employee> emp=service.getAllEmp();
		m.addAttribute("emp",emp);
		return "index";
	}
	
	@GetMapping("/addemp")
	public String addemp() {
		return "addemp";
	}
	
	@PostMapping("/register")
	public String empRegister(@ModelAttribute Employee e) {
		service.addEmp(e);
		return "redirect:/dashboard";
	}
	
	@GetMapping("/edit/{id}")
	public String empEdit(@PathVariable int id,Model m) {
		Employee e=service.getEmployeeById(id);
		m.addAttribute("emp",e);
		return "editemp";
	}
	
	@PostMapping("/update")
	public String updateEmp(@ModelAttribute Employee e) {
		service.addEmp(e);
		return "redirect:/dashboard";
	}
	
	@GetMapping("/delete/{id}")
	public String deleteEmp(@PathVariable int id) {
		service.deleteEmployeeById(id);
		return "redirect:/dashboard";
	}
		
	@PostMapping("/search")
	public String searchEmp(@RequestParam("searchText")String searchText,Model m) {
		int id;
		try {
			id=Integer.parseInt(searchText);
		}catch(NumberFormatException e){
			id=-1;
		}
		List<Employee> emp=service.search(searchText,id);
		m.addAttribute("emp",emp);
		return "index";
	}
	
	@GetMapping("/export")
	public void exportEmp(HttpServletResponse res) throws IOException{
		res.setContentType("text/csv");
		res.setHeader("Content-Disposition", "attachment; filename=\"employee_details.csv\"");
		
		List<Employee> emp=service.getAllEmp();
		PrintWriter writer=res.getWriter();
		
		export.exportEmp(emp, writer);
	}
}
