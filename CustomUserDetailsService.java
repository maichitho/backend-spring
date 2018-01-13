package com.siten.cms.service;

import java.util.ArrayList;
import java.util.List;

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

import com.siten.cms.model.SaFunction;
import com.siten.cms.model.SaUser;
import com.siten.cms.util.Constants;

@Service("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService, ApplicationListener<AuthenticationSuccessEvent> {

	@Autowired
	private UserService userService;

	@Autowired
	private FunctionService functionService;

	@Transactional
	public UserDetails loadUserByUsername(String ssoId) throws UsernameNotFoundException {
		SaUser user = userService.findByName(ssoId);
		if (user == null) {
			throw new UsernameNotFoundException("Username not found");
		}
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
				user.getStatus().equals("Active"), true, true, true, getGrantedAuthorities(user));
	}

	public List<SaFunction> getAllFunction() {
		return functionService.findAllFunctions();
	}

	private List<GrantedAuthority> getGrantedAuthorities(SaUser user) {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		try {
			// Kiểm tra type user = system hay member
			// for (SaRoleUser userProfile : user.getSaRoleUsers()) {
			// System.out.println("UserProfile : " + userProfile);
			authorities.add(new SimpleGrantedAuthority("ROLE_" + user.getType()));
			if (user.getType().equals(Constants.USERTYPE_SYSTEM)) {
				List<SaFunction> functionList = functionService.findFunctionsByUserId(user.getId());
				for (SaFunction function : functionList) {
					authorities.add(new SimpleGrantedAuthority("ROLE_" + function.getCode()));
				}
			}

			// }
		} catch (Exception e) {
			e.printStackTrace();
		}
		return authorities;
	}

	@Override
	public void onApplicationEvent(AuthenticationSuccessEvent event) {
		String userName = ((UserDetails) event.getAuthentication().getPrincipal()).getUsername();
		userService.login(userName);
	}
}
