package com.backend.TaskSecurity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.backend.TaskModel.Employee;
import com.backend.TaskModel.Role;

import lombok.Data;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Data
public class UserPrincipal implements UserDetails {

	private static final long serialVersionUID = 1L;
	private long id;
	@JsonIgnore
	private String username;
	@JsonIgnore
	private String password;
	
	private Collection<? extends GrantedAuthority> authorities;
	private Set<Role> roles;

	public UserPrincipal(Long id, String username, String password,  Set<Role> roles, Collection<? extends GrantedAuthority> authorities) {
//		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.roles = roles;
		this.authorities = authorities;
	}
	
	public static UserPrincipal create(Employee emp) {
		List<GrantedAuthority> authorities = emp.getRole().stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
		
		return new UserPrincipal(emp.getId(), emp.getUsername(), emp.getPassword(), emp.getRole(), authorities);
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return authorities;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
 }