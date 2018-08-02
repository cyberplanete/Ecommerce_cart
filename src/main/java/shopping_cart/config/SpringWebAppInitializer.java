package shopping_cart.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;



public class SpringWebAppInitializer implements WebApplicationInitializer{

	//Configuration no XML (spring-mvc-demo-serlet.xml) 
	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		// Create the 'root' Spring application context
        AnnotationConfigWebApplicationContext appContext = new AnnotationConfigWebApplicationContext();
        //AppConfig du mème package
        appContext.register(ApplicationContextConfig.class);
        appContext.setServletContext(servletContext);
        ServletRegistration.Dynamic répartiteur = servletContext.addServlet("SpringRépartiteur", new DispatcherServlet(appContext));
        répartiteur.setLoadOnStartup(1);
        répartiteur.addMapping("/");
    }
}