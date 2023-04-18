package com.backend.TaskController;

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

import com.backend.TaskAuthentication.WorkItemRequest;
import com.backend.TaskModel.Employee;
import com.backend.TaskModel.Module;
import com.backend.TaskModel.Project;
import com.backend.TaskModel.WorkDo;
import com.backend.TaskModel.WorkItem;
import com.backend.TaskModel.WorkPriority;
import com.backend.TaskModel.WorkStatus;
import com.backend.TaskRepo.WorkDoRepo;

import lombok.RequiredArgsConstructor;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/work-do")
@RequiredArgsConstructor
public class WorkDoController {
	
private final WorkDoRepo workDoRepo;
	
	@GetMapping("/list")
	public ResponseEntity<List<WorkDo>> getWorkDos() {

		return new ResponseEntity<>(workDoRepo.findWorkDos(), HttpStatus.OK);
	}
//	@PostMapping("/form")
//	public ResponseEntity<WorkItem> createWorkItem(@RequestBody WorkItemRequest newWorkItem){
//		WorkItem wk = new WorkItem();
//		wk.setCode(newWorkItem.getCode());
//		wk.setName(newWorkItem.getName());
//		
//		Module mod = moduleRepo.findModuleById(newWorkItem.getModuleid());
//		wk.setModule_workitem(mod);
//		
//		Project prj = projectRepo.findProjectById(newWorkItem.getProjectid());
//		wk.setProject_workitem(prj);
//		
//		WorkPriority pri = workPriorityRepo.getPriority(newWorkItem.getPriorityid());
//		wk.setPriority(pri);
//		
//		Employee emp1 = employeeRepo.findEId(newWorkItem.getEmp1id());
//		wk.setEmp_workitem_1(emp1);
//		
//		Employee emp2 = employeeRepo.findEId(newWorkItem.getEmp2id());
//		wk.setEmp_workitem_2(emp2);
//		
//		wk.setDateStart(newWorkItem.getDateStart());
//		wk.setDateEnd(newWorkItem.getDateEnd());
//		wk.setContent(newWorkItem.getContent());
//		
//		WorkStatus sta = workStatusRepo.getStatus(newWorkItem.getStatus());
//		wk.setStatus_workitem(sta);
//		
//		
//		return new ResponseEntity<>(workItemRepo.save(wk), HttpStatus.CREATED);
//	}
//	
//	@PutMapping("/form/{id}")
//	public ResponseEntity<WorkItem> updateWorkItem(@PathVariable("id") long id, @RequestBody WorkItemRequest upWorkItem){
//		WorkItem wk = workItemRepo.findWorkItemById(id);
//		wk.setCode(upWorkItem.getCode());
//		wk.setName(upWorkItem.getName());
//		
//		Module mod = moduleRepo.findModuleById(upWorkItem.getModuleid());
//		wk.setModule_workitem(mod);
//		
//		Project prj = projectRepo.findProjectById(upWorkItem.getProjectid());
//		wk.setProject_workitem(prj);
//		
//		WorkPriority pri = workPriorityRepo.getPriority(upWorkItem.getPriorityid());
//		wk.setPriority(pri);
//		
//		Employee emp1 = employeeRepo.findEId(upWorkItem.getEmp1id());
//		wk.setEmp_workitem_1(emp1);
//		
//		Employee emp2 = employeeRepo.findEId(upWorkItem.getEmp2id());
//		wk.setEmp_workitem_2(emp2);
//		
//		wk.setDateStart(upWorkItem.getDateStart());
//		wk.setDateEnd(upWorkItem.getDateEnd());
//		wk.setContent(upWorkItem.getContent());
//		
//		WorkStatus sta = workStatusRepo.getStatus(upWorkItem.getStatus());
//		wk.setStatus_workitem(sta);
//		
//		
//		return new ResponseEntity<>(workItemRepo.save(wk), HttpStatus.CREATED);
//	}
//	@GetMapping("/search/{id}")
//	public ResponseEntity<WorkItem> searchWorkitem(@PathVariable("id") long id) {
//		
//		Optional<WorkItem> searchData = workItemRepo.findById(id);
//		if (searchData.isPresent()) {
//			return new ResponseEntity<>(searchData.get(), HttpStatus.OK);
//		} else {
//			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//		}
//	}
//	
//	@DeleteMapping("/delete/{id}")
//	public ResponseEntity<?> deleteWorkitem(@PathVariable(name = "id") Long id) { 
//		workItemRepo.deleteById(id);
//		
//		return new ResponseEntity< >("Successfully", HttpStatus.OK);
//	}

}
