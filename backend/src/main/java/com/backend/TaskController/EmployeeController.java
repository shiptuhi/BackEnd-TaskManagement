package com.backend.TaskController;

import com.backend.TaskAuthentication.SignupRequest;
import com.backend.TaskModel.Employee;
import com.backend.TaskModel.Role;
import com.backend.TaskRepo.EmployeeRepo;
import com.backend.TaskRepo.RoleRepo;

import lombok.RequiredArgsConstructor;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/employee")
@RequiredArgsConstructor
public class EmployeeController {

	private final EmployeeRepo employeeRepo;
	private final RoleRepo roleRepo;

	@GetMapping("/list")
	public ResponseEntity<List<Employee>> getEmployees() {

		return new ResponseEntity<>(employeeRepo.findEmployees(), HttpStatus.OK);
	}
	
	@GetMapping("/list/name")
	public ResponseEntity<List<String>> getEmployees_Name() {

		return new ResponseEntity<>(employeeRepo.listEmployee_Name(), HttpStatus.OK);
	}

	@PutMapping("/form/{id}")
	public ResponseEntity<?> updateeEmployee(@PathVariable("id") long id, @Valid @RequestBody SignupRequest upEmp) {
		Employee emp = employeeRepo.findEId(id);
		if (!emp.getUsername().equals(upEmp.getUsername()) && employeeRepo.existsByUsername(upEmp.getUsername())) {
			return new ResponseEntity<>("Error: Username is already taken!", HttpStatus.BAD_REQUEST);
		}
		if (!emp.getEmail().equals(upEmp.getEmail()) && employeeRepo.existsByEmail(upEmp.getEmail())) {
			return new ResponseEntity<>("Error: Email is already taken!", HttpStatus.BAD_REQUEST);
		}
		if (!emp.getPhoneNo().equals(upEmp.getPhoneNo()) && employeeRepo.existsByPhoneNo(upEmp.getPhoneNo())) {
			return new ResponseEntity<>("Error: Phone number is already taken!", HttpStatus.BAD_REQUEST);
		}

		emp.setUsername(upEmp.getUsername());
		// encoder.encode(signUpRequest.getPassword()));
		// emp.setPassword(upEmp.getNew_password());
		emp.setName(upEmp.getName());
		emp.setEmail(upEmp.getEmail());
		emp.setPhoneNo(upEmp.getPhoneNo());
		emp.setGender(upEmp.getGender());
		emp.setDepartment(upEmp.getDepartment());
		emp.setStatus(upEmp.getStatus());
		Set<String> strRoles = upEmp.getRole();
		Set<Role> roles = new HashSet<>();
		if (strRoles == null) {
			return ResponseEntity.ok(new RuntimeException("Error: Role is not found."));
		} else {
			strRoles.forEach(role -> {
				switch (role) {
				case "admin":
					Role adminRole = roleRepo.findByName("Admin")
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(adminRole);
					break;
				case "Team Leader":
					Role teamleaderRole = roleRepo.findByName("Team Leader")
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(teamleaderRole);
					break;
				case "Tester":
					Role testerRole = roleRepo.findByName("Tester")
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(testerRole);
					break;
				case "FrontEnd":
					Role frontendRole = roleRepo.findByName("FrontEnd")
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(frontendRole);
					break;
				case "BackEnd":
					Role backendRole = roleRepo.findByName("BackEnd")
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(backendRole);
					break;
				}
			});
		}
		emp.setRole(roles);

		return new ResponseEntity<>(employeeRepo.save(emp), HttpStatus.OK);
	}

	@GetMapping("/list/{id}")
	public ResponseEntity<Employee> getEmployee(@PathVariable("id") Long id) {
		Optional<Employee> searchData = employeeRepo.findById(id);
		if (searchData.isPresent()) {
			return new ResponseEntity<>(searchData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

//	@PutMapping("/change-password/{id}")
//	public ResponseEntity<?> changePass(@PathVariable("id") Long id, @RequestBody SignupRequest upEmp) {
//		if (employeeRepo.existsByPassword(upEmp.getCurrent_password())) {
//			Employee emp = employeeRepo.findById(id);
//			emp.setPassword(upEmp.getNew_password());
//			return new ResponseEntity<>(employeeRepo.save(emp), HttpStatus.OK);
//
//		}
//		return new ResponseEntity<>("Error: Wrong password ", HttpStatus.BAD_REQUEST);
//	}

	@GetMapping("/")
	public ResponseEntity<List<Employee>> searchEmployee(@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "username", required = false) String username,
			@RequestParam(value = "phoneNo", required = false) String phoneNo,
			@RequestParam(value = "role", required = false) String role) {
		List<Employee> emp = employeeRepo.searchEmployees(name, username, phoneNo, role);

		return new ResponseEntity<>(emp, HttpStatus.OK);
	}

}
