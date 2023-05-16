package com.backend.TaskController;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;

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

import com.backend.TaskAuthentication.WorkDoRequest;
import com.backend.TaskModel.Employee;
import com.backend.TaskModel.Module;
import com.backend.TaskModel.Project;
import com.backend.TaskModel.WorkDo;
import com.backend.TaskModel.WorkItem;

import com.backend.TaskRepo.EmployeeRepo;
import com.backend.TaskRepo.ModuleRepo;
import com.backend.TaskRepo.ProjectRepo;
import com.backend.TaskRepo.WorkDoRepo;
import com.backend.TaskRepo.WorkItemRepo;

import lombok.RequiredArgsConstructor;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/work-do")
@RequiredArgsConstructor
public class WorkDoController {

	private final WorkDoRepo workDoRepo;
	private final WorkItemRepo workItemRepo;
	private final ModuleRepo moduleRepo;
	private final ProjectRepo projectRepo;
	private final EmployeeRepo employeeRepo;

	@GetMapping("/list")
	public ResponseEntity<List<WorkDo>> getWorkDoes() {

		return new ResponseEntity<>(workDoRepo.findWorkDoes(), HttpStatus.OK);
	}

	@PostMapping("/form")
	public ResponseEntity<?> createWorkDo(@RequestBody WorkDoRequest newWorkDo) {
		if (workDoRepo.existsByCode(newWorkDo.getCode())) {
			return new ResponseEntity<>("Error: Code is already taken!", HttpStatus.BAD_REQUEST);
		}
		WorkDo wd = new WorkDo();
		String prefix = "DO_";
		LocalDate time = LocalDate.now();
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");
		String date = time.format(dateTimeFormatter);
		long code = workDoRepo.generatedCode();
		String generateCode = prefix + date + "_" + code;
		
		wd.setCode(generateCode);
		wd.setName(newWorkDo.getName());

		WorkItem wk = workItemRepo.findWorkItem(newWorkDo.getWorkitem());
		wd.setWorkItem(wk);

		Module mod = moduleRepo.findModule(newWorkDo.getModule());
		wd.setModule_workdo(mod);

		Project prj = projectRepo.findProject(newWorkDo.getProject());
		wd.setProject_workdo(prj);

		wd.setType(newWorkDo.getTypeofwork());

		wd.setPriority(newWorkDo.getPriority());

		wd.setGroup(newWorkDo.getGroupofwork());

		Employee emp3 = employeeRepo.findE(newWorkDo.getEmp3());
		wd.setEmp_workdo_3(emp3);

		Employee emp4 = employeeRepo.findE(newWorkDo.getEmp4());
		wd.setEmp_workdo_4(emp4);

		wd.setDateStart(newWorkDo.getDateStart());
		wd.setDateEnd(newWorkDo.getDateEnd());

		wd.setContent(newWorkDo.getContent());

		wd.setStatus(newWorkDo.getStatus());

		wd.setNote(newWorkDo.getNote());
		wd.setOt(newWorkDo.getOt());

		return new ResponseEntity<>(workDoRepo.save(wd), HttpStatus.OK);
	}

	@PutMapping("/form/{id}")
	public ResponseEntity<?> updateWorkDo(@PathVariable("id") long id, @RequestBody WorkDoRequest upWorkDo) {

		WorkDo wd = workDoRepo.findWorkDoById(id);
		if (!wd.getCode().equals(upWorkDo.getCode()) && workDoRepo.existsByCode(upWorkDo.getCode())) {
			return new ResponseEntity<>("Error: Code is already taken!", HttpStatus.BAD_REQUEST);
		}
//		wd.setCode(upWorkDo.getCode());
		wd.setName(upWorkDo.getName());

		WorkItem wk = workItemRepo.findWorkItem(upWorkDo.getWorkitem());
		wd.setWorkItem(wk);

		Module mod = moduleRepo.findModule(upWorkDo.getModule());
		wd.setModule_workdo(mod);

		Project prj = projectRepo.findProject(upWorkDo.getProject());
		wd.setProject_workdo(prj);

		wd.setType(upWorkDo.getTypeofwork());

		wd.setPriority(upWorkDo.getPriority());

		wd.setGroup(upWorkDo.getGroupofwork());

		Employee emp3 = employeeRepo.findE(upWorkDo.getEmp3());
		wd.setEmp_workdo_3(emp3);

		Employee emp4 = employeeRepo.findE(upWorkDo.getEmp4());
		wd.setEmp_workdo_4(emp4);

		wd.setDateStart(upWorkDo.getDateStart());
		wd.setDateEnd(upWorkDo.getDateEnd());

		wd.setContent(upWorkDo.getContent());

		wd.setStatus(upWorkDo.getStatus());

		wd.setNote(upWorkDo.getNote());
		wd.setOt(upWorkDo.getOt());

		return new ResponseEntity<>(workDoRepo.save(wd), HttpStatus.CREATED);
	}

	@GetMapping("/search/{id}")
	public ResponseEntity<WorkDo> getWorkDo(@PathVariable("id") long id) {

		Optional<WorkDo> searchData = workDoRepo.findById(id);
		if (searchData.isPresent()) {
			return new ResponseEntity<>(searchData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/")
	public ResponseEntity<List<WorkDo>> searchWorkDo(@RequestParam(value = "code", required = false) String code,
													@RequestParam(value = "name", required = false) String name,
													@RequestParam(value = "workItem", required = false) String workItem,
													@RequestParam(value = "module_workdo", required = false) String module_workdo,
													@RequestParam(value = "project_workdo", required = false) String project_workdo,
										
													@RequestParam(value = "type", required = false) String type,
													@RequestParam(value = "priority", required = false) String priority,
										
													@RequestParam(value = "group", required = false) String group,
										
													@RequestParam(value = "emp_workdo_3", required = false) String emp_workdo_3,

//													@RequestParam(value = "emp_workdo_4", required = false) String emp_workdo_4,
//													@RequestParam(value = "dateStart", required = false) String dateStart,
//													@RequestParam(value = "dateEnd", required = false) String dateEnd, 

													@RequestParam(value = "content", required = false) String content,
													@RequestParam(value = "status", required = false) String status) {

		List<WorkDo> wd = workDoRepo.searchWorkDoes(code, name, workItem, module_workdo, project_workdo, type, priority,
				group, emp_workdo_3, content, status);
		return new ResponseEntity<>(wd, HttpStatus.OK);
	}

	@GetMapping("/{id}/all-work-do")
	public ResponseEntity<?> getWorkDoByEmployee(@PathVariable(name = "id") Long id) {

		List<WorkDo> wd = workDoRepo.getWorkDoByEmpId(id);
		return new ResponseEntity<>(wd, HttpStatus.OK);
	}

	// Fix
	@GetMapping("/delete/{id}")
	public ResponseEntity<?> deleteWorkDo(@PathVariable("id")  Long id) {

		try {
//			WorkDo w = workDoRepo.findWorkDoById(id);
			workDoRepo.delete(id);
			return new ResponseEntity<>("Successfully", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@GetMapping("/type-of-work")
	public ResponseEntity<?> typeofwork() {
		List<String> stringList  = new ArrayList<String>(Arrays.asList(workDoRepo.test1().split(",")));
		List<Integer> myList = stringList.stream()
	            .map(Integer::valueOf).collect(Collectors.toList());
		return new ResponseEntity<>(myList,HttpStatus.OK);
	}
	
	@GetMapping("/priority")
	public ResponseEntity<?> priority() {
		List<String> stringList = new ArrayList<String>(Arrays.asList(workDoRepo.test2().split(",")));
		List<Integer> myList = stringList.stream()
	            .map(Integer::valueOf).collect(Collectors.toList());
		return new ResponseEntity<>(myList, HttpStatus.OK);
	}
	
	@GetMapping("/group-of-work")
	public ResponseEntity<?> groupofwork() {
		List<String> stringList = new ArrayList<String>(Arrays.asList(workDoRepo.test3().split(",")));
		List<Integer> myList = stringList.stream()
	            .map(Integer::valueOf).collect(Collectors.toList());
		return new ResponseEntity<>(myList, HttpStatus.OK);
	}
	
	@GetMapping("/status")
	public ResponseEntity<?> status() {
		List<String> stringList = new ArrayList<String>(Arrays.asList(workDoRepo.test4().split(",")));
		List<Integer> myList = stringList.stream()
	            .map(Integer::valueOf).collect(Collectors.toList());
		return new ResponseEntity<>(myList, HttpStatus.OK);
	}

}
