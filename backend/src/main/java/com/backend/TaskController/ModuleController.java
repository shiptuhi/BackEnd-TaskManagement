package com.backend.TaskController;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
import org.springframework.web.bind.annotation.RequestParam;
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
	
	@GetMapping("/list/name/")
	public ResponseEntity<List<String>> getModule_Name(@RequestParam(value = "project", required = false) String project) {

		return new ResponseEntity<>(moduleRepo.findModule_Name(project), HttpStatus.OK);
	}
	@PostMapping(path="/form", consumes = { "application/json" })
	public ResponseEntity<?> createModule(@RequestBody ModuleRequest newMod) {
		if(moduleRepo.existsByCode(newMod.getCode())) {
			return new ResponseEntity<>("Error: Code is already taken!",HttpStatus.BAD_REQUEST); 
		}
		String prefix = "MOD_";
		LocalDate time = LocalDate.now();
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");
		String date = time.format(dateTimeFormatter);
		long code = moduleRepo.generatedCode();
		String generateCode = prefix + date + "_" + code;
		
		Module mod = new Module();
		
		mod.setCode(generateCode);
		
		mod.setName(newMod.getName());
		
		Project prj = projectRepo.findProject(newMod.getProject());
		mod.setProject(prj);
		
		List<Employee> e = new ArrayList<>();
		
		Employee emp = employeeRepo.find(newMod.getEmp());
		e.add(emp);
		mod.setEmp(e);
		
		
		mod.setDateStart(newMod.getDateStart());
		mod.setNote(newMod.getNote());

		return new ResponseEntity<>(moduleRepo.save(mod), HttpStatus.OK);
	}
	
	@PutMapping("/form/{id}")
	public ResponseEntity<?> updateModule(@PathVariable("id") long id,@RequestBody ModuleRequest upModule) {
		if(moduleRepo.existsByCode(upModule.getCode())) {
			return new ResponseEntity<>("Error: Code is already taken!",HttpStatus.BAD_REQUEST); 
		}
		Module mod = moduleRepo.findModuleById(id);
		mod.setCode(upModule.getCode());
		mod.setName(upModule.getName());
		
		Project prj = projectRepo.findProject(upModule.getProject());
		mod.setProject(prj);
		
		List<Employee> e = new ArrayList<>();
		
		Employee emp = employeeRepo.find(upModule.getEmp());
		e.add(emp);
		mod.setEmp(e);
		
		mod.setDateStart(upModule.getDateStart());
		mod.setNote(upModule.getNote());
		return new ResponseEntity<>(moduleRepo.save(mod), HttpStatus.OK);
	}
	
	@GetMapping("/search/{id}")
	public ResponseEntity<Module> getModule(@PathVariable("id") long id) {
		
		Optional<Module> searchData = moduleRepo.findById(id);
		if (searchData.isPresent()) {
			return new ResponseEntity<>(searchData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/")
	public ResponseEntity<List<Module>> searchModule(@RequestParam(value = "code", required = false) String code, 
														@RequestParam(value = "name", required = false) String name,
														@RequestParam(value = "project", required = false) String project, 
														@RequestParam(value = "emp", required = false) String emp,
														@RequestParam(value = "note", required = false) String note) 
	{
		List<Module> mod = moduleRepo.findModules(code, name, project, emp, note);

		return new ResponseEntity<>(mod,HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteModule(@PathVariable(name = "id") Long id) { 
		moduleRepo.deleteById(id);
		
		return new ResponseEntity< >("Successfully", HttpStatus.OK);
	}

}
