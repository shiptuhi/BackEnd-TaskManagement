package com.backend.TaskController;

import com.backend.TaskModel.Employee;
import com.backend.TaskRepo.EmployeeRepo;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/employee")
@RequiredArgsConstructor
public class EmployeeController {

	private final EmployeeRepo employeeRepo;

	@GetMapping("/list")
	public ResponseEntity<List<Employee>> getEmployees() {

		return new ResponseEntity<>(employeeRepo.findEmployees(), HttpStatus.OK);
	}

}
