package com.vietis.carpark.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.access.AccessDeniedHandler;

import com.vietis.carpark.repository.UserRepository;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	@Qualifier("customUserDetailsService")
	UserDetailsService userDetailsService;

	@Autowired
	UserRepository repor;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsServiceBean());
	}

	@Override
	public UserDetailsService userDetailsServiceBean() throws Exception {
		return new CustomUserDetailsService(repor);
	}

	// @Override
	// public void configure(AuthenticationManagerBuilder auth) throws Exception
	// {
	// // auth.userDetailsService(userDetailsService);
	// auth.authenticationProvider(authenticationProvider());
	// }
	//
	// @Bean
	// public SessionRegistry sessionRegistry() {
	// return new SessionRegistryImpl();
	// }

	@Autowired
	private AccessDeniedHandler accessDeniedHandler;

	// roles admin allow to access /admin/**
	// roles user allow to access /user/**
	// custom 403 access denied handler
	@Override
	protected void configure(HttpSecurity http) throws Exception {
//		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED);
		http.sessionManagement().maximumSessions(1000000).sessionRegistry(sessionRegistry());
		http.csrf().disable().authorizeRequests()
				.antMatchers("/", "/home", "/about", "/js/**", "/img/**", "/css/**", "/fonts/**").permitAll()
				.antMatchers("/admin/**").hasAuthority("ADMIN").antMatchers("/user/**").hasAuthority("USER")
				.anyRequest().authenticated().and().formLogin().loginPage("/login").permitAll().and().logout()
				.permitAll().and().exceptionHandling().accessDeniedHandler(accessDeniedHandler);

	}

	@Bean
	public SessionRegistry sessionRegistry() {
		return new SessionRegistryImpl();
	}

	// @Bean
	// public PasswordEncoder passwordEncoder() {
	// return new CarparkPasswordEncoder();
	// }
	//
	// @Bean
	// public DaoAuthenticationProvider authenticationProvider() {
	// DaoAuthenticationProvider authenticationProvider = new
	// DaoAuthenticationProvider();
	// authenticationProvider.setUserDetailsService(userDetailsService);
	// authenticationProvider.setPasswordEncoder(passwordEncoder());
	// return authenticationProvider;
	// }
}
