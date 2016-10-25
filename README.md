# madura-resource-loader

You have a Spring messageSource defined like this:

```
<bean id="messageSource" class="org.springframework.context.support.ResourceBundleMessageSource">
	<property name="basenames">
		<list>
			<value>nz/co/senanque/validationengine/ValidationMessages</value>
			<value>ApplicationMessages</value>
			<value>nz/co/senanque/login/messages</value>
		</list>
	</property>
</bean>
```
The trouble is two of those entries are from properties files buried in dependent jar files. I would rather not have to configure
those at all in the application. So here is what we can do instead using the madura-resource-loader:
```
<bean id="messageSource" class="nz.co.senanque.resourceloader.ResourceBundleMessageSourceExt">
	<property name="basenames">
		<list>
			<value>ApplicationMessages</value>
		</list>
	</property>
</bean>
```
As long as we are using the component scan on those jar files this will find and load the properties files.
For example we have a class nz/co/senanque/login/RequestValidatorImpl that looks like this:
```
@Component("requestValidator")
@MessageResource("messages")
public class RequestValidatorImpl implements AuthenticationDelegate, MessageSourceAware, RequestValidator {
...
```
As long as this component bean is being loaded by Spring it will cause the nz/co/senanque/login/messages.properties file to
be added to the messageSource. You can override the prepending of the current package by including a '/' in the message resource
eg you could say '/messages'.

You do need to be using the component scanner eg:
```
<context:component-scan base-package="nz.co.senanque.resourceloader, nz.co.senanque.login" />

```
Use of parent message sources is supported in the same way it is for ResourceBundleMessageSource, as is everything else because ResourceBundleMessageSourceExt is just an extension of ResourceBundleMessageSource.

This works best when configured with XML as shown. You can configure it using Java Config but not if you are using it in a chain because the @PostConstruct method will not be called at the right time unless it is at the top of the chain.