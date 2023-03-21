package com.backend.TaskService;

import com.backend.TaskModel.Employee;
import com.backend.TaskRepo.EmployeeRepo;
import com.backend.TaskSecurity.UserPrincipal;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

@Service
public class LoginService implements UserDetailsService {

	@Autowired
	EmployeeRepo employeeRepo;

	@Override
	@Transactional
//    Get user by username
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Employee emp = employeeRepo.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("Not found account with username: " + username));
		
		return UserPrincipal.create(emp);
	}

	@Transactional
	public UserDetails loadUserById(Long id) {
		Employee employee = employeeRepo.findById(id)
				.orElseThrow(() -> new UsernameNotFoundException(String.format("User not found with id: %s", id)));

		return UserPrincipal.create(employee);
	}
}
