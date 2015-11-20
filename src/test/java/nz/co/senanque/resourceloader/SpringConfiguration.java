/*******************************************************************************
 * Copyright (c)2015 Prometheus Consulting
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package nz.co.senanque.resourceloader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;

/**
 * @author Roger Parkinson
 *
 */

@Configuration
@ComponentScan(basePackages = {
		"nz.co.senanque.resourceloader"})
public class SpringConfiguration {
	
	@Autowired
    Environment env;

	@Bean
    public MessageSource messageSource() { 
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        ResourceBundleMessageSource messageSource2 = new ResourceBundleMessageSourceExt();
        XMLMessageSource messageSource3 = new XMLMessageSource();
        messageSource3.setResource(new ClassPathResource("/Messages.xml"));
        messageSource.setBasename("Messages");
        messageSource.setParentMessageSource(messageSource2);
        messageSource2.setParentMessageSource(messageSource3);
        return messageSource;
    }
	@Bean
	public TestBean testBean() {
		return new TestBean();
	}
}
