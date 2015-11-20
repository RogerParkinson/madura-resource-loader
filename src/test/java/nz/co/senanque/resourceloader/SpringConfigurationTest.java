package nz.co.senanque.resourceloader;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SpringConfigurationTest {
	
	ApplicationContext context;
	TestBean m_testBean;
	
	@Before
	public void setup() {
        context = new AnnotationConfigApplicationContext(SpringConfiguration.class);
        m_testBean = context.getBean(TestBean.class);
	}
	@After
	public void takeDown() {
		//
	}
	@Test
	public void testMessages() {
		//m_testBean.runTest();
    }

}
