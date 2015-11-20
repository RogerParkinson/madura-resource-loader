package nz.co.senanque.xmlconfiguration;

import nz.co.senanque.resourceloader.TestBean;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"Spring-context.xml"})
public class SpringXMLConfigurationTest {
	
	@Autowired TestBean m_testBean;
	
	@Test
	public void testMessages() {
		m_testBean.runTest();
    }

}
