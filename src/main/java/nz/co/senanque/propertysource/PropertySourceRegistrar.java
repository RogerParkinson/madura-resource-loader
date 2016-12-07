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
package nz.co.senanque.propertysource;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionReaderUtils;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.util.ClassUtils;
import org.springframework.util.StringUtils;

/**
 * @author Roger Parkinson
 *
 */
public class PropertySourceRegistrar implements ImportBeanDefinitionRegistrar  {
	
	private Logger m_logger = LoggerFactory.getLogger(this.getClass());

	protected String resolveBasePackage(String basePackage) {
		return ClassUtils.convertClassNameToResourcePath(basePackage);
	}
	
	public void registerBeanDefinitions(
			AnnotationMetadata importingClassMetadata,
			BeanDefinitionRegistry registry) {

		Map<String, Object> attributes = importingClassMetadata.getAnnotationAttributes("nz.co.senanque.propertysource.PropertySource");

		BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(PropertySourcesPlaceholderConfigurer.class);
		beanDefinitionBuilder.addPropertyValue("beanName", attributes.get("name"));
		if (!StringUtils.isEmpty(attributes.get("encoding"))) {
			beanDefinitionBuilder.addPropertyValue("fileEncoding", attributes.get("encoding"));
		}
		beanDefinitionBuilder.addPropertyValue("ignoreResourceNotFound", attributes.get("ignoreResourceNotFound"));
		beanDefinitionBuilder.addPropertyValue("localOverride", attributes.get("localOverride"));
		beanDefinitionBuilder.addPropertyValue("locations", attributes.get("value"));
		
		BeanDefinition beanDefinition = beanDefinitionBuilder.getBeanDefinition();
		BeanDefinitionHolder definitionHolder = new BeanDefinitionHolder(beanDefinition, String.valueOf(System.identityHashCode(attributes)));

		registerBeanDefinition(definitionHolder, registry);
	}

	protected void registerBeanDefinition(BeanDefinitionHolder definitionHolder, BeanDefinitionRegistry registry) {
		BeanDefinitionReaderUtils.registerBeanDefinition(definitionHolder, registry);
	}
}
