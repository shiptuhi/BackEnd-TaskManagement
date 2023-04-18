package com.backend.TaskController;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.TaskModel.Customer;
import com.backend.TaskModel.Employee;
import com.backend.TaskModel.Project;
import com.backend.TaskRepo.EmployeeRepo;
import com.backend.TaskRepo.ProjectRepo;
import com.backend.TaskRepo.CustomerRepo;
import com.backend.TaskAuthentication.ProjectRequest;


import lombok.RequiredArgsConstructor;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/project")
@RequiredArgsConstructor
public class ProjectController {
	
	private final ProjectRepo projectRepo;
	
	private final EmployeeRepo employeeRepo;
	
	private final CustomerRepo customerRepo;
	
	@GetMapping("/list")
	public ResponseEntity<List<Project>> getProjects() {

		return new ResponseEntity<>(projectRepo.findProjects(), HttpStatus.OK);
	}
	
	@PostMapping(path="/form", consumes = { "application/json" })
	public ResponseEntity<Project> createProject(@RequestBody ProjectRequest newPro) {
		Project prj = new Project();
		prj.setCode(newPro.getCode());
		prj.setName(newPro.getName());
		prj.setStatus(newPro.getStatus());
		prj.setDateStart(newPro.getDateStart());
		prj.setDateEnd(newPro.getDateEnd());
		 
		Employee emp1 = employeeRepo.findEId(newPro.getEmpid());
		prj.setEmp(emp1);
		
		Customer cus1 = customerRepo.findCustomerById(newPro.getCustomerid());
		prj.setCustomer(cus1);
		
		prj.setNote(newPro.getNote());
		
		return new ResponseEntity<>(projectRepo.save(prj), HttpStatus.CREATED);
	}
	@PutMapping("/form/{id}")
	public ResponseEntity<Project> updateProject(@PathVariable("id") long id,@RequestBody ProjectRequest upPro) {
		Project prj = projectRepo.findProjectById(id);
		prj.setCode(upPro.getCode());
		prj.setName(upPro.getName());
		prj.setStatus(upPro.getStatus());
		prj.setDateStart(upPro.getDateStart());
		prj.setDateEnd(upPro.getDateEnd());
		Employee emp1 = employeeRepo.findEId(upPro.getEmpid());
		prj.setEmp(emp1);
		
		Customer cus1 = customerRepo.findCustomerById(upPro.getCustomerid());
		prj.setCustomer(cus1);
		prj.setNote(upPro.getNote());
		
		return new ResponseEntity<>(projectRepo.save(prj), HttpStatus.CREATED);
	}
	@GetMapping("/search/{id}")
	public ResponseEntity<Project> searchProject(@PathVariable("id") long id) {
		
		Optional<Project> searchData = projectRepo.findById(id);
		if (searchData.isPresent()) {
			return new ResponseEntity<>(searchData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteProject(@PathVariable(name = "id") Long id) { 
		projectRepo.deleteById(id);
		
		return new ResponseEntity< >("Successfully", HttpStatus.OK);
	}

}
