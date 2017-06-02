package co.siten.myvtg.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * 
 * @author thomc
 *
 */
@Component
@PropertySource(value = { "classpath:language.properties" })
public class MessageUtil {
	@Autowired
	private Environment environment;

	public String getMessage(String key) {
		try {
			return environment.getProperty(key, key);
		} catch (Exception e) {
			return key;
		}

	}

}
