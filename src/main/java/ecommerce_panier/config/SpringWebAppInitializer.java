package ecommerce_panier.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;


//Configuration no XML (spring-mvc-demo-serlet.xml) 
public class SpringWebAppInitializer implements WebApplicationInitializer{

	
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