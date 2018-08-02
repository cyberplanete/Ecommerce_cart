package shopping_cart.config;


import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class WebMvcConfig implements WebMvcConfigurer{
	
	private static final Charset UTF8 = Charset.forName("UTF-8");

	// Configuration UTF-8 Encodage.
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        StringHttpMessageConverter stringConverter = new StringHttpMessageConverter();
        stringConverter.setSupportedMediaTypes(Arrays.asList(new MediaType("text", "plain", UTF8)));
        converters.add(stringConverter);
 
        
    }
	
	
	//Remplace web.xml
	
	@Override
	public void addResourceHandlers(final ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/js/**").addResourceLocations("/WEB-INF/view/js/");
		registry.addResourceHandler("/styles/**").addResourceLocations("/WEB-INF/ressources/css/");
		registry.addResourceHandler("/img/**").addResourceLocations("/WEB-INF/ressources/img/").setCachePeriod(31556926);
	//registry.addResourceHandler("/*.html/**").addResourceLocations("/WEB-INF/ressources/html/");
	}
	
	
}
