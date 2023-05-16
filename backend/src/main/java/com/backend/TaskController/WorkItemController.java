package com.backend.TaskController;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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

import com.backend.TaskModel.WorkItem;
import com.backend.TaskModel.Module;
import com.backend.TaskModel.Project;
import com.backend.TaskModel.Employee;


import com.backend.TaskRepo.WorkItemRepo;
import com.backend.TaskRepo.ModuleRepo;
import com.backend.TaskRepo.ProjectRepo;
import com.backend.TaskRepo.EmployeeRepo;

import com.backend.TaskAuthentication.WorkItemRequest;

import lombok.RequiredArgsConstructor;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/work-item")
@RequiredArgsConstructor
public class WorkItemController {
	
	private final WorkItemRepo workItemRepo;
	private final ModuleRepo moduleRepo;
	private final ProjectRepo projectRepo;
	private final EmployeeRepo employeeRepo;

	
	@GetMapping("/list")
	public ResponseEntity<List<WorkItem>> getWorkItems() {

		return new ResponseEntity<>(workItemRepo.findWorkItems(), HttpStatus.OK);
	}
	
	@GetMapping("/list/name/")
	public ResponseEntity<List<String>> getWorkItems_Name(@RequestParam(value = "module", required = false) String module) {

		return new ResponseEntity<>(workItemRepo.findWorkItem_Name(module), HttpStatus.OK);
	}
	
	@PostMapping("/form")
	public ResponseEntity<?> createWorkItem(@RequestBody WorkItemRequest newWorkItem){
		if(workItemRepo.existsByCode(newWorkItem.getCode())) {
			return new ResponseEntity<>("Error: Code is already taken!",HttpStatus.BAD_REQUEST); 
		}
		WorkItem wk = new WorkItem();
		String prefix = "IT_";
		LocalDate time = LocalDate.now();
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");
		String date = time.format(dateTimeFormatter);
		long code = workItemRepo.generatedCode();
		String generateCode = prefix + date + "_" + code;
		
		wk.setCode(generateCode);
		wk.setName(newWorkItem.getName());
		
		Module mod = moduleRepo.findModule(newWorkItem.getModule());
		wk.setModule_workitem(mod);
		
		Project prj = projectRepo.findProject(newWorkItem.getProject());
		wk.setProject_workitem(prj);
		

		wk.setPriority(newWorkItem.getPriority());
		
		Employee emp1 = employeeRepo.findE(newWorkItem.getEmp1());
		wk.setEmp_workitem_1(emp1);
		
		Employee emp2 = employeeRepo.findE(newWorkItem.getEmp2());
		wk.setEmp_workitem_2(emp2);
		
		wk.setDateStart(newWorkItem.getDateStart());
		wk.setDateEnd(newWorkItem.getDateEnd());
		wk.setContent(newWorkItem.getContent());
		
		wk.setStatus(newWorkItem.getStatus());
		
		
		return new ResponseEntity<>(workItemRepo.save(wk), HttpStatus.OK);
	}
	
	@PutMapping("/form/{id}")
	public ResponseEntity<?> updateWorkItem(@PathVariable("id") long id, @RequestBody WorkItemRequest upWorkItem){

		WorkItem wk = workItemRepo.findWorkItemById(id);
		if (!wk.getCode().equals(upWorkItem.getCode()) && workItemRepo.existsByCode(upWorkItem.getCode())) {
			return new ResponseEntity<>("Error: Code is already taken!",HttpStatus.BAD_REQUEST); 
		}
//		wk.setCode(upWorkItem.getCode());
		wk.setName(upWorkItem.getName());
		
		Module mod = moduleRepo.findModule(upWorkItem.getModule());
		wk.setModule_workitem(mod);
		
		Project prj = projectRepo.findProject(upWorkItem.getProject());
		wk.setProject_workitem(prj);
		
		wk.setPriority(upWorkItem.getPriority());
		
		Employee emp1 = employeeRepo.findE(upWorkItem.getEmp1());
		wk.setEmp_workitem_1(emp1);
		
		Employee emp2 = employeeRepo.findE(upWorkItem.getEmp2());
		wk.setEmp_workitem_2(emp2);
		
		wk.setDateStart(upWorkItem.getDateStart());
		wk.setDateEnd(upWorkItem.getDateEnd());
		wk.setContent(upWorkItem.getContent());
		
		wk.setStatus(upWorkItem.getStatus());
		
		
		return new ResponseEntity<>(workItemRepo.save(wk), HttpStatus.OK);
	}
	@GetMapping("/search/{id}")
	public ResponseEntity<WorkItem> getWorkitem(@PathVariable("id") long id) {
		
		Optional<WorkItem> searchData = workItemRepo.findById(id);
		if (searchData.isPresent()) {
			return new ResponseEntity<>(searchData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/")
	public ResponseEntity<List<WorkItem>> searchWorkitem(@RequestParam(value = "code", required = false) String code, 
													@RequestParam(value = "name", required = false) String name,
													@RequestParam(value = "module_workitem", required = false) String module_workitem,
													@RequestParam(value = "project_workitem", required = false) String project_workitem,
													@RequestParam(value = "priority", required = false) String priority,
													@RequestParam(value = "emp_workitem_1", required = false) String emp_workitem_1
//													@RequestParam(value = "emp_workitem_2", required = false) String emp_workitem_2,
//													@RequestParam(value = "dateStart", required = false) String dateStart,
//													@RequestParam(value = "dateEnd", required = false) String dateEnd, 
//													@RequestParam(value = "content", required = false) String content, 
//													@RequestParam(value = "status", required = false) String status
													) 
	{
		
		List<WorkItem> wi = workItemRepo.searchWorkItems(code, name, module_workitem, 
												project_workitem, priority, emp_workitem_1);
//												emp_workitem_2, dateStart,
//												dateEnd, content, status
		
		return new ResponseEntity<>(wi, HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteWorkitem(@PathVariable(name = "id") long id) { 
		workItemRepo.deleteById(id);
		
		return new ResponseEntity<>("Successfully", HttpStatus.OK);
	}

}
