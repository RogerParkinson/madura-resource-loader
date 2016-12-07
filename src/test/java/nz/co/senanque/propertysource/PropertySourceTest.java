package nz.co.senanque.propertysource;

import static org.junit.Assert.assertEquals;
import nz.co.senanque.resourceloader.TestBean;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=PropertySourceConfiguration.class)
public class PropertySourceTest {

	@Autowired ApplicationContext context;
	@Autowired TestBean m_testBean;

	@Value("${config.param}")
	private String injectedValue;
	
	public String getInjectedValue() {
		return injectedValue;
	}
	public void setInjectedValue(String injectedValue) {
		this.injectedValue = injectedValue;
	}
	@Test
	public void testMessages() {
		assertEquals("1234",injectedValue);
    }

}

