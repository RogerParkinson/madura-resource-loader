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

import static org.junit.Assert.assertEquals;

import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.context.support.MessageSourceAccessor;

/**
 * @author Roger Parkinson
 *
 */
public class TestBean implements MessageSourceAware {

	private MessageSource m_messageSource;

	public TestBean() {
	}
	
	public void runTest() {
		MessageSourceAccessor messageSourceAccessor = new MessageSourceAccessor(m_messageSource);
		assertEquals("def",messageSourceAccessor.getMessage("abc"));
		assertEquals("123",messageSourceAccessor.getMessage("xyz"));
		assertEquals("Gluten Free",messageSourceAccessor.getMessage("GlutenFree"));
	}

	public void setMessageSource(MessageSource messageSource) {
		m_messageSource = messageSource;
		
	}

}
