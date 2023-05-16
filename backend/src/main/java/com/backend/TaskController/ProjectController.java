package com.backend.TaskController;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;


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
import org.springframework.web.bind.annotation.RequestParam;
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
	
	@GetMapping("/list/name")
	public ResponseEntity<List<String>> getProject_Name() {
		return new ResponseEntity<>(projectRepo.listProjectName(), HttpStatus.OK);
	}
	
	@PostMapping(path="/form", consumes = { "application/json" })
	public ResponseEntity<?> createProject(@RequestBody ProjectRequest newPro) {
		Project prj = new Project();
		if(projectRepo.existsByCode(newPro.getCode())) {
			return new ResponseEntity<>("Error: Code is already taken!",HttpStatus.BAD_REQUEST); 
		}
		
		prj.setCode(newPro.getCode());
		prj.setName(newPro.getName());
		prj.setStatus(newPro.getStatus());
		prj.setDateStart(newPro.getDateStart());
		prj.setDateEnd(newPro.getDateEnd());
		 
		Employee emp1 = employeeRepo.findE(newPro.getEmp());
		prj.setEmp(emp1);
		
		Customer cus1 = customerRepo.findCustomer(newPro.getCustomer());
		prj.setCustomer(cus1);
		
		prj.setNote(newPro.getNote());
		
		return new ResponseEntity<>(projectRepo.save(prj), HttpStatus.OK);
	}
	@PutMapping("/form/{id}")
	public ResponseEntity<?> updateProject(@PathVariable("id") long id,@RequestBody ProjectRequest upPro) {

		Project prj = projectRepo.findProjectById(id);
		if(!prj.getCode().equals(upPro.getCode()) && projectRepo.existsByCode(upPro.getCode())) {
			return new ResponseEntity<>("Error: Code is already taken!",HttpStatus.BAD_REQUEST); 
		}
		prj.setCode(upPro.getCode());
		prj.setName(upPro.getName());
		prj.setStatus(upPro.getStatus());
		prj.setDateStart(upPro.getDateStart());
		prj.setDateEnd(upPro.getDateEnd());
		Employee emp1 = employeeRepo.findE(upPro.getEmp());
		prj.setEmp(emp1);
		
		Customer cus1 = customerRepo.findCustomer(upPro.getCustomer());
		prj.setCustomer(cus1);
		prj.setNote(upPro.getNote());
		
		return new ResponseEntity<>(projectRepo.save(prj), HttpStatus.OK);
	}
	@GetMapping("/search/{id}")
	public ResponseEntity<Project> getProject(@PathVariable("id") long id) {
		
		Optional<Project> searchData = projectRepo.findById(id);
		if (searchData.isPresent()) {
			return new ResponseEntity<>(searchData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/")
	public ResponseEntity<List<Project>> searchProject (@RequestParam(value = "code", required = false) String code, 
														@RequestParam(value = "name", required = false) String name,
														@RequestParam(value = "status", required = false) String status, 
//														@RequestParam(value = "dateStart", required = false) String dateStart,
//														@RequestParam(value = "dateEnd", required = false) String dateEnd, 
//														@RequestParam(value = "emp", required = false) Long emp,
														@RequestParam(value = "partner", required = false) String partner, 
														@RequestParam(value = "note", required = false) String note)throws ParseException 
	{
//		 	Date d1 = new SimpleDateFormat("dd-MM-yyyy").parse(dateStart);
//	        Date d2 = new SimpleDateFormat("dd-MM-yyyy").parse(dateEnd);
	        
	
		List<Project> prj = projectRepo.searchProjects(code, name, status, partner, note);
		return new ResponseEntity<>(prj,HttpStatus.OK);
	}
	
	@GetMapping("/search")
	public ResponseEntity<List<Project>> searchProjectsWithSearchBar(@RequestParam("search") String query){
		List<Project> prj = projectRepo.searchProjectsWithSearchBar(query);
		return new ResponseEntity<>(prj,HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteProject(@PathVariable(name = "id") Long id) { 
		projectRepo.deleteById(id);
		
		return new ResponseEntity<>("Successfully", HttpStatus.OK);
	}

}
