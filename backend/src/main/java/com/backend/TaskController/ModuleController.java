package com.backend.TaskController;

import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RestController;

import com.backend.TaskModel.Module;
import com.backend.TaskModel.Project;
import com.backend.TaskModel.Employee;
import com.backend.TaskRepo.ModuleRepo;
import com.backend.TaskRepo.ProjectRepo;
import com.backend.TaskRepo.EmployeeRepo;
import com.backend.TaskAuthentication.ModuleRequest;

import lombok.RequiredArgsConstructor;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/module")
@RequiredArgsConstructor
public class ModuleController {
	
	public final ModuleRepo moduleRepo;
	
	private final ProjectRepo projectRepo;
	
	private final EmployeeRepo employeeRepo;
	
	@GetMapping("/list")
	public ResponseEntity<List<Module>> getModules() {

		return new ResponseEntity<>(moduleRepo.findModules(), HttpStatus.OK);
	}
	
	@PostMapping(path="/form", consumes = { "application/json" })
	public ResponseEntity<Module> createModule(@RequestBody ModuleRequest newMod) {
		Module mod = new Module();
		mod.setCode(newMod.getCode());
		mod.setName(newMod.getName());
		
		Project prj = projectRepo.findProjectById(newMod.getProjectid());
		mod.setProject(prj);
		
		List<Employee> e = new ArrayList<>();
		
		Employee emp = employeeRepo.findEId(newMod.getEmpid());
		e.add(emp);
		mod.setEmp(e);
		
		
		mod.setDateStart(newMod.getDateStart());
		mod.setNote(newMod.getNote());

		return new ResponseEntity<>(moduleRepo.save(mod), HttpStatus.CREATED);
	}
	
	@PutMapping("/form/{id}")
	public ResponseEntity<Module> updateModule(@PathVariable("id") long id,@RequestBody ModuleRequest upModule) {
		Module mod = moduleRepo.findModuleById(id);
		mod.setCode(upModule.getCode());
		mod.setName(upModule.getName());
		
		Project prj = projectRepo.findProjectById(upModule.getProjectid());
		mod.setProject(prj);
		
		List<Employee> e = new ArrayList<>();
		
		Employee emp = employeeRepo.findEId(upModule.getEmpid());
		e.add(emp);
		mod.setEmp(e);
		
		mod.setDateStart(upModule.getDateStart());
		mod.setNote(upModule.getNote());
		return new ResponseEntity<>(moduleRepo.save(mod), HttpStatus.CREATED);
	}
	
	@GetMapping("/search/{id}")
	public ResponseEntity<Module> searchModule(@PathVariable("id") long id) {
		
		Optional<Module> searchData = moduleRepo.findById(id);
		if (searchData.isPresent()) {
			return new ResponseEntity<>(searchData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteModule(@PathVariable(name = "id") Long id) { 
		moduleRepo.deleteById(id);
		
		return new ResponseEntity< >("Successfully", HttpStatus.OK);
	}

}
