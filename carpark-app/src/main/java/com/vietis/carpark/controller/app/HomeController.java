package com.vietis.carpark.controller.app;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {

	@GetMapping("/")
	public String home1() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		auth.getAuthorities();
		return "/home";
	}

	@GetMapping("/home")
	public String home() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		auth.getAuthorities();
		return "/home";
	}

	@GetMapping("/admin")
	public String admin() {
		return "/admin";
	}

	@GetMapping("/user")
	public String user() {
		SecurityContextHolder.getContext().getAuthentication().getAuthorities();
		return "/user";
	}

	@GetMapping("/about")
	public String about() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		auth.getAuthorities();
		return "/about";
	}

	@GetMapping("/login")
	public String login() {
		return "/login";
	}

	@GetMapping("/403")
	public String error403() {
		return "/error/403";
	}

	@GetMapping("/logout")
	public String logout(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			try {
				Object principal;

				principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

				if (principal instanceof UserDetails) {
					String username = ((UserDetails) principal).getUsername();
					SecurityContextLogoutHandler handler = new SecurityContextLogoutHandler();
					handler.logout(request, response, auth);
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return "/";
	}
}
