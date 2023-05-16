package com.backend.TaskConfig;

import com.backend.TaskService.LoginService;
import com.backend.TaskSecurity.JwtAuthenticationFilter;
import com.backend.TaskSecurity.JwtAuthenticationEntryPoint;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;


import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;

import org.springframework.security.web.SecurityFilterChain;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig {

	private final LoginService loginService;

	private final JwtAuthenticationEntryPoint unauthorizedHandler;

	@Bean
	public JwtAuthenticationFilter jwtAuthenticationFilter() {
		return new JwtAuthenticationFilter();
	}

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

		authProvider.setUserDetailsService(loginService);
		authProvider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());

		return authProvider;
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
		return config.getAuthenticationManager();
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.cors().and().csrf().disable()
				.exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
				.authorizeRequests()
					.antMatchers("/api/auth/signin").permitAll()
					.antMatchers("/api/auth/signup").hasAnyAuthority("Admin")
					.antMatchers("/api/employee/**").permitAll()
					
					.antMatchers("/api/customer/list/").hasAnyAuthority("Admin", "Team Leader", "Tester", "FrontEnd", "BackEnd")
					.antMatchers("/api/customer/form").hasAnyAuthority("Admin", "Team Leader")	
					.antMatchers("/api/customer/form/**").hasAnyAuthority("Admin", "Team Leader")

					
					.antMatchers("/api/project/list/").hasAnyAuthority("Admin", "Team Leader", "Tester", "FrontEnd", "BackEnd")
					.antMatchers("/api/project/form").hasAnyAuthority("Admin", "Team Leader")
					.antMatchers("/api/project/form/**").hasAnyAuthority("Admin", "Team Leader")
					
					.antMatchers("/api/module/list/").hasAnyAuthority("Admin", "Team Leader", "Tester", "FrontEnd", "BackEnd")
					.antMatchers("/api/module/form").hasAnyAuthority("Admin", "Team Leader")
					.antMatchers("/api/module/form/**").hasAnyAuthority("Admin", "Team Leader")
					
					.antMatchers("/api/work-item/list/").hasAnyAuthority("Admin", "Team Leader", "Tester", "FrontEnd", "BackEnd")
					.antMatchers("/api/work-item/form").hasAnyAuthority("Admin", "Team Leader")
					.antMatchers("/api/work-item/form/**").hasAnyAuthority("Admin", "Team Leader")
					
					.antMatchers("/api/work-do/list/").hasAnyAuthority("Admin", "Team Leader", "Tester", "FrontEnd", "BackEnd")
					.antMatchers("/api/work-do/form").hasAnyAuthority("Admin", "Team Leader", "Tester", "FrontEnd", "BackEnd")
					.antMatchers("/api/work-do/form/**").hasAnyAuthority("Admin", "Team Leader", "Tester", "FrontEnd", "BackEnd")
					.antMatchers("/api/work-do/delete/**").hasAnyAuthority("Admin", "Team Leader", "Tester", "FrontEnd", "BackEnd")

				.anyRequest().authenticated();

		http.authenticationProvider(authenticationProvider());

		http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);

		return http.build();
	}
}
