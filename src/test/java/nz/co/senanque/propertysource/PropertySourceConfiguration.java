package nz.co.senanque.propertysource;

import nz.co.senanque.resourceloader.TestBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
@PropertySource(value="classpath:config.properties",localOverride=true) // note this is not the Spring PropertySource
public class PropertySourceConfiguration {

	@Autowired
    Environment env;

	@Bean
	public TestBean testBean() {
		return new TestBean();
	}
}
