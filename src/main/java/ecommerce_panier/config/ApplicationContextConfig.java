package ecommerce_panier.config;

import java.util.Properties;

import javax.activation.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import ecommerce_panier.dao.CommandeDAO;
import ecommerce_panier.dao.CompteDAO;
import ecommerce_panier.dao.ProduitDAO;
import ecommerce_panier.impl.CommandeDAOImplementation;
import ecommerce_panier.impl.CompteDAOImplementation;
import ecommerce_panier.impl.ProduitDAOImpl;

//Remplace config.xml

@EnableWebMvc
@Configuration
@ComponentScan({ "crm.dao","crm.controleur","crm.service","crm.entity","crm.aspect","shopping_cart.config","crm.erreurs" })
@PropertySource("classpath:ds-hibernate-cfg.properties")
public class ApplicationContextConfig implements WebMvcConfigurer{
	
	// La classe Environment sert pour stocker les propriétés
    // chargées par @PropertySource
    @Autowired
    private Environment env;

	@Bean (name = "viewResolver")
	public InternalResourceViewResolver viewResolver() {
	    InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
	    viewResolver.setViewClass(JstlView.class);
	    viewResolver.setPrefix("/WEB-INF/view/");
	    viewResolver.setSuffix(".jsp");
	    return viewResolver;
	}
	
	// Configuration pour l'upload.
    @Bean(name = "multipartResolver")
    public CommonsMultipartResolver multipartResolver() {
        CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver();
         
        // Set Max Size...
        // commonsMultipartResolver.setMaxUploadSize(...);
         
        return commonsMultipartResolver;
    }
    
    @Bean(name = "dataSource")
    public DriverManagerDataSource getDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
 
        // Voir: ds-hibernate-cfg.properties
        dataSource.setDriverClassName(env.getProperty("ds.database-driver"));
        dataSource.setUrl(env.getProperty("ds.url"));
        dataSource.setUsername(env.getProperty("ds.username"));
        dataSource.setPassword(env.getProperty("ds.password"));
         
        System.out.println("## getDataSource: " + dataSource);
         
        return dataSource;
    }
    
   
    @Bean(name = "sessionFactory")
	@Autowired
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(getDataSource());
        sessionFactory.setPackagesToScan(new String[]{"crm.entity"});
        sessionFactory.setHibernateProperties(hibernateProperties());
        return sessionFactory;
    }
    
	@Bean
	@Autowired
	 Properties hibernateProperties() {
	        return new Properties() {

				private static final long serialVersionUID = 1L;
					//Hibernate properties
				{
	                setProperty("hibernate.hbm2ddl.auto", "update");
	                setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5InnoDBDialect");
	                setProperty("hibernate.show_sql", "true");
	            }
	        };
	 }
	
	// Configuration des messages d'erreurs  messages/validateur.properties
	 
	@Bean
	public ResourceBundleMessageSource messageSource() {
	   ResourceBundleMessageSource rb = new ResourceBundleMessageSource();
	   
	   rb.setBasenames(new String[] { "messages/validateur"});
	   return rb;
	}
	
	 @Autowired
	    @Bean(name = "transactionManager")
	    public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory) {
	        HibernateTransactionManager transactionManager = new HibernateTransactionManager(sessionFactory);
	 
	        return transactionManager;
	    }
	 	@Bean(name = "compteDAO")
	    public CompteDAO getApplicantDAO() {
	        return new CompteDAOImplementation();
	    }
	 
	    @Bean(name = "produitDAO")
	    public ProduitDAO getProductDAO() {
	        return new ProduitDAOImpl();
	    }
	 
	    @Bean(name = "commandeDAO")
	    public CommandeDAO getOrderDAO() {
	        return new CommandeDAOImplementation();
	    }
	     
	    @Bean(name = "accountDAO")
	    public CompteDAO getAccountDAO()  {
	        return new CompteDAOImplementation();
	    }
	
}
