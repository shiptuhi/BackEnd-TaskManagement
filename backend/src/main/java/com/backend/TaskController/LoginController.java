package com.backend.TaskController;

import com.backend.TaskModel.Employee;
import com.backend.TaskModel.Role;
import com.backend.TaskRepo.EmployeeRepo;
import com.backend.TaskRepo.RoleRepo;
import com.backend.TaskAuthentication.JwtAuthenticationResponse;
import com.backend.TaskSecurity.JwtTokenProvider;
import com.backend.TaskAuthentication.SignupRequest;
//import com.backend.TaskService.LoginService;

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
	private final RoleRepo roleRepo;
//	private final LoginService loginService;
	private final AuthenticationManager authenticationManager;
	private final JwtTokenProvider jwtTokenProvider;

	@PostMapping("/signin")
	public ResponseEntity<JwtAuthenticationResponse> signin(@Valid @RequestBody SigninRequest signinRequest){

		Authentication auth = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(signinRequest.getUsername(), signinRequest.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(auth);
		UserPrincipal userPrincipal = (UserPrincipal) auth.getPrincipal();
		String jwt = jwtTokenProvider.generateJwtToken(userPrincipal);

		Employee emp = employeeRepo.find(signinRequest.getUsername());

		return ResponseEntity.ok(new JwtAuthenticationResponse(jwt, emp));

	}

	@PostMapping("/signup")
	public ResponseEntity<?> signup(@Valid @RequestBody SignupRequest newEmp) {

		if (employeeRepo.existsByUsername(newEmp.getUsername())) {
			return new ResponseEntity<>("Error: Username is already taken!", HttpStatus.BAD_REQUEST);
		}
		if(employeeRepo.existsByPhoneNo(newEmp.getPhoneNo())) {
			return new ResponseEntity<>("Error: Phone number is already taken!",HttpStatus.BAD_REQUEST);
		}
		if(employeeRepo.existsByEmail(newEmp.getEmail())) {
			return new ResponseEntity<>("Error: Email is already taken!",HttpStatus.BAD_REQUEST);
		}
		// Create new user's account
		Employee emp = new Employee();
		emp.setUsername(newEmp.getUsername());
		
//		encoder.encode(signUpRequest.getPassword()));
		emp.setPassword(newEmp.getNew_password());
		emp.setName(newEmp.getName());
		emp.setEmail(newEmp.getEmail());
		emp.setPhoneNo(newEmp.getPhoneNo());
		emp.setGender(newEmp.getGender());
		emp.setDepartment(newEmp.getDepartment());
		emp.setStatus(newEmp.getStatus());
		Set<String> strRoles = newEmp.getRole();
		Set<Role> roles = new HashSet<>();
		if (strRoles == null) {
		      return ResponseEntity.ok(new RuntimeException("Error: Role is not found."));
		} else {
			strRoles.forEach(role -> {
				switch (role) {
				case "admin" :
					Role adminRole = roleRepo.findByName("Admin").orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			        roles.add(adminRole);
			        break;
				case "Team Leader" :
					Role teamleaderRole = roleRepo.findByName("Team Leader").orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			        roles.add(teamleaderRole);
			        break;
				case "Tester" :
					Role testerRole = roleRepo.findByName("Tester").orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			        roles.add(testerRole);
			        break;
				case "FrontEnd" :
					Role frontendRole = roleRepo.findByName("FrontEnd").orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			        roles.add(frontendRole);
			        break;
				case "BackEnd" :
					Role backendRole = roleRepo.findByName("BackEnd").orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			        roles.add(backendRole);
			        break;
				}
			});
	       }
		emp.setRole(roles);

		return new ResponseEntity<>(employeeRepo.save(emp),HttpStatus.OK);

	}
	
	 @PostMapping("/signout")
	 public ResponseEntity<?> logoutUser() {
//		 UserPrincipal userPrincipal = (UserPrincipal) auth.getPrincipal();
//		 SecurityContextHolder.getContext().setAuthentication(auth);
		
		UserPrincipal userPrincipal = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	    Long userId = userPrincipal.getId();
//	    refreshTokenService.deleteByUserId(userId);
	    return new ResponseEntity<>("Log out successful!", HttpStatus.OK);
	  }

}
