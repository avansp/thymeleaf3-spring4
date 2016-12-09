package myexample;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ITemplateResolver;

/**
 * 
 * Thymeleaf + Spring MVC configuration
 * 
 * This is the main configuration for Thymeleaf 3 + Spring 4 with MVC. 
 * It's useful as a skeleton for typical other applications.
 *
 */
@Configuration
@EnableWebMvc
@ComponentScan("myexample")
public class ThymeleafSpringConfig extends WebMvcConfigurerAdapter implements ApplicationContextAware {
	
	private static final String UTF8 = "UTF-8";
	private ApplicationContext applicationContext;

	/**
	 *  The setApplicationContext is needed to initialize the object for our web application.
	 *  This method is in the ApplicationContextAware interface.
	 *  We set a static ApplicationContext object stored in this class.
	 */
	@Override
	public void setApplicationContext(ApplicationContext _applicationContext) {
		this.applicationContext = _applicationContext;
	}
	
	/**
	 * TemplateEngine is a concept from Thymeleaf. We only need one TemplateEngine for the whole application,
	 * except if we need to have many dialects.
	 *  
	 * Once created, an instance of TemplateEngine has to be typically configured a mechanism for resolving templates.
	 * If no Template Resolvers are configured, TemplateEngine instances will use a StringTemplateResolver instance which 
	 * will consider templates being specified for processing as the template contents.
	 * 
	 * Since we are using Spring, and Thymeleaf has an integrated Spring framework, we can just create SpringTemplateEngine
	 * and set what to resolve (see templateResolver() method below).
	 */
	private TemplateEngine templateEngine() {
		SpringTemplateEngine engine = new SpringTemplateEngine();
		engine.setTemplateResolver(templateResolver());
		return engine;
	}
	
	/**
	 * Now, here is where we tell Spring to know where to resolver resources, prefix and what to render.
	 * That includes:
	 *   - the application context object,
	 *   - where are the templates, and
	 *   - the template mode to render, which is a HTML file.
	 */
	private ITemplateResolver templateResolver() {
		SpringResourceTemplateResolver resolver = new SpringResourceTemplateResolver();
		resolver.setApplicationContext(this.applicationContext);
		resolver.setPrefix("/WEB-INF/templates/");
		resolver.setTemplateMode(TemplateMode.HTML);
		return resolver;
	}
	
	/**
	 * All MVC frameworks for web applications provide a way to address views. 
	 * Spring provides view resolvers, which enable you to render models in a browser without tying you 
	 * to a specific view technology. Out of the box, Spring enables you to use JSPs, Velocity templates 
	 * and XSLT views, for example.
	 * 
	 * But here, we're going to make Thymeleaf as our view resolver.
	 */
	@Bean
	public ViewResolver viewResolver() {
		ThymeleafViewResolver resolver = new ThymeleafViewResolver();
		resolver.setTemplateEngine(templateEngine());
		resolver.setCharacterEncoding(UTF8);
		return resolver;
	}

	
}
