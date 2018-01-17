package com.vietis.carpark.config;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vietis.carpark.entity.User;
import com.vietis.carpark.repository.UserRepository;

@Service("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService, ApplicationListener<AuthenticationSuccessEvent> {

	private UserRepository userService;

	public CustomUserDetailsService(UserRepository userService) {
		this.userService = userService;
	}

	@Transactional
	public UserDetails loadUserByUsername(String ssoId) throws UsernameNotFoundException {
		try {
			User user = userService.findByEmailAndIsDeleted(ssoId, false);
			if (user == null) {
				throw new UsernameNotFoundException("Username not found");
			}
			return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
					user.getStatus() == User.ACTIVE, true, true, true, getGrantedAuthorities(user));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	// public List<SaFunction> getAllFunction() {
	// return functionService.findAllFunctions();
	// }

	private List<GrantedAuthority> getGrantedAuthorities(User user) {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		try {
			// // Kiểm tra type user = system hay member
			// // for (SaRoleUser userProfile : user.getSaRoleUsers()) {
			// // System.out.println("UserProfile : " + userProfile);
			// authorities.add(new SimpleGrantedAuthority("ROLE_" +
			// user.getType()));
			// if (user.getType().equals(Constants.USERTYPE_SYSTEM)) {
			// List<SaFunction> functionList =
			// functionService.findFunctionsByUserId(user.getId());
			// for (SaFunction function : functionList) {
			// authorities.add(new SimpleGrantedAuthority("ROLE_" +
			// function.getCode()));
			// }
			// }
			authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
			authorities.add(new SimpleGrantedAuthority("USER"));
			// }
		} catch (Exception e) {
			e.printStackTrace();
		}
		return authorities;
	}

	@Autowired
	private HttpSession session;

	@Override
	public void onApplicationEvent(AuthenticationSuccessEvent arg0) {
		session.setAttribute("sessionj", "value");
	}

}
