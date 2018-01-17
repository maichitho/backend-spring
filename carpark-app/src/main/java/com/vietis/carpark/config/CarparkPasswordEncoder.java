package com.vietis.carpark.config;

import org.springframework.security.crypto.password.PasswordEncoder;

public class CarparkPasswordEncoder implements PasswordEncoder{
	
	@Override
	public String encode(CharSequence plain) {
		return plain.toString();
	}

	@Override
	public boolean matches(CharSequence plain, String encodedPass) {
		return plain.equals(encodedPass);
	}
}
