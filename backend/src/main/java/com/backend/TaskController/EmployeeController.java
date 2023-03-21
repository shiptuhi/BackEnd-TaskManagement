package com.backend.TaskController;

import com.backend.TaskModel.Employee;
import com.backend.TaskRepo.EmployeeRepo;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/dashboard")
@RequiredArgsConstructor
public class EmployeeController {

	private final EmployeeRepo employeeRepo;

	@GetMapping("/employee-detail")
	public List<Employee> getEmployees() {
		return employeeRepo.findEmployees();
	}

}
