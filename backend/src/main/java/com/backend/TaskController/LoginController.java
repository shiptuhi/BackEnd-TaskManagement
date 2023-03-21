package com.backend.TaskController;

import com.backend.TaskModel.Employee;
import com.backend.TaskModel.Role;
import com.backend.TaskRepo.EmployeeRepo;
import com.backend.TaskAuthentication.JwtAuthenticationResponse;
import com.backend.TaskAuthentication.MessageResponse;
import com.backend.TaskSecurity.JwtTokenProvider;
import com.backend.TaskService.LoginService;

import lombok.RequiredArgsConstructor;

import com.backend.TaskAuthentication.SigninRequest;
import com.backend.TaskSecurity.UserPrincipal;

import java.util.HashSet;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.security.authentication.AuthenticationManager;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class LoginController {

	private final EmployeeRepo employeeRepo;
//	private final LoginService loginService;
	private final AuthenticationManager authenticationManager;
	private final JwtTokenProvider jwtTokenProvider;

	@PostMapping("/signin")
	public ResponseEntity<JwtAuthenticationResponse> signin(@Valid @RequestBody SigninRequest signinRequest) {

		Authentication auth = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(signinRequest.getUsername(), signinRequest.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(auth);
		UserPrincipal userPrincipal = (UserPrincipal) auth.getPrincipal();
		String jwt = jwtTokenProvider.generateJwtToken(userPrincipal);

		Employee emp = employeeRepo.find(signinRequest.getUsername());

		return ResponseEntity.ok(new JwtAuthenticationResponse(jwt, emp));

	}

	@PostMapping("/signup")
	public ResponseEntity<?> signup(@Valid @RequestBody Employee newEmp) {

//		if (employeeRepo.existsByUsername(emp.getUsername())) {
//			return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken!"));
//		}
//
//		if (employeeRepo.existsByEmail(signUpRequest.getEmail())) {
//			return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));
//		}

		// Create new user's account
		Employee emp = new Employee();
		emp.setUsername(newEmp.getUsername());
		
//		encoder.encode(signUpRequest.getPassword()));
		
		emp.setName(newEmp.getName());
		emp.setEmail(newEmp.getEmail());
		emp.setPhoneNo(newEmp.getPhoneNo());
		emp.setGender(newEmp.getGender());
		emp.setDepartment(newEmp.getDepartment());
		emp.setStatus(newEmp.getStatus());
		Set<Role> strRoles = newEmp.getRole();
		Set<Role> roles = new HashSet<>();
//		if (strRoles == null) {
//		      Role userRole = roleRepository.findByName(ERole.ROLE_USER)
//		          .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//		      roles.add(userRole);
//		emp.setRole(strRoles);
//		employeeRepo.save(emp);

		return ResponseEntity.ok(new MessageResponse("Đăng ký thành công"));

	}

}
