package mm.aeon.com.zconfig.oauth2;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import mm.aeon.com.common.VCSMPasswordEncoder;

@Component
public class CustomPasswordEncoder implements PasswordEncoder {
	@Override
	public String encode(CharSequence rawPassword) {
		String hashed = VCSMPasswordEncoder.encode(rawPassword.toString());
		return hashed;
	}

	@Override
	public boolean matches(CharSequence rawPassword, String encodedPassword) {
		String hashed = VCSMPasswordEncoder.encode(rawPassword.toString());
		return hashed.equals(encodedPassword);
	}
}
