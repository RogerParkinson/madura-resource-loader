package nz.co.senanque.resourceloader;

import java.util.Locale;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
        "classpath:applicationTestContext.xml"})
public class SpringXMLTest {
	
	@Autowired ApplicationContext context;
	@Autowired MessageSource messageSource;
	
	@Test
	public void testMessages() {
		String s = messageSource.getMessage("login.title", null, Locale.getDefault());
		Assert.assertEquals("Madura Address Book", s);
		s = messageSource.getMessage("login.title", null, Locale.FRENCH);
		Assert.assertEquals("Bienvenue à Madura", s);
    }

}
