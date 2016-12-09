package myexample;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

/**
 * 
 * This is the Spring's bootstrapping to initializer a web application.
 * WebApplicationInitializer is an interface to be implemented in Servlet 3.0+ 
 * environments in order to configure the ServletContext programmatically -- 
 * as opposed to (or possibly in conjunction with) the traditional web.xml-based approach.
 *
 */
public class HelloWebApplicationInitializer implements WebApplicationInitializer {
	
	/**
	 * There is one traditional web.xml based approach, but the modern one that Spring
	 * recommends is to use a configuration.
	 * 
	 * Two classes are needed:
	 *   - a configuration class: see ThymeleafSpringConfig
	 *   - a dispatcher configuration class: we will use the default
	 *   
	 * Spring uses AnnotationConfigWebApplicationContext to do this.
	 */
	
	// We just need to override the startup method
	@Override
	public void onStartup(ServletContext _servletContext) throws ServletException {
		
		// create root application context
		AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
		
		// register our ThymeleafSpringConfig class
		rootContext.register(ThymeleafSpringConfig.class);
		rootContext.setServletContext(_servletContext);
		
		// Spring MVC front controller
		// Register and map the dispatcher servlet
        Dynamic servlet = _servletContext.addServlet("dispatcher", new DispatcherServlet(rootContext));
        servlet.addMapping("/");
        servlet.setLoadOnStartup(1);
	}

}
