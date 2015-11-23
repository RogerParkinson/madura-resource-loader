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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import javax.annotation.PostConstruct;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.util.Assert;

/**
 * @author Roger Parkinson
 *
 */
public class ResourceBundleMessageSourceExt extends ResourceBundleMessageSource implements BeanFactoryAware {

	private BeanFactory m_beanFactory;
	private String[] mybasenames = new String[0];

	public ResourceBundleMessageSourceExt() {

	}

	public void setBasenames(String... basenames) {
		if (basenames != null) {
			this.mybasenames = new String[basenames.length];
			for (int i = 0; i < basenames.length; i++) {
				String basename = basenames[i];
				Assert.hasText(basename, "Basename must not be empty");
				this.mybasenames[i] = basename.trim();
			}
		}
		else {
			this.mybasenames = new String[0];
		}
	}

	@PostConstruct
	public void init() {
		Map<String, Object> map = ((DefaultListableBeanFactory)m_beanFactory).getBeansWithAnnotation(MessageResource.class);
		List<String> basenamesList = new ArrayList<String>();
		for (String b: mybasenames) {
			basenamesList.add(b);
		}
		for (Object o: map.values()) {
			String pckge = o.getClass().getPackage().getName().replace('.', '/')+'/';
			String baseNames = o.getClass().getAnnotation(MessageResource.class).value();
			StringTokenizer st = new StringTokenizer(baseNames,",");
			while (st.hasMoreTokens()) {
				String token = st.nextToken();
				if (!token.contains("/")) {
					token = pckge + token;
				}
				basenamesList.add(token);
			}
		}
		super.setBasenames(basenamesList.toArray(new String[basenamesList.size()]));
//		MessageSource parentMessageSource = getParentMessageSource();
//		if (parentMessageSource != null) {
//			Method methods[] = parentMessageSource.getClass().getMethods();
//			for (Method method: methods) {
//				if (method.isAnnotationPresent(PostConstruct.class)) {
//					try {
//						method.invoke(parentMessageSource, new Object[]{});
//					} catch (Exception e) {
//						throw new RuntimeException(e);
//					}
//				}
//			}
//		}
	}

	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
		m_beanFactory = beanFactory;
	}
}
