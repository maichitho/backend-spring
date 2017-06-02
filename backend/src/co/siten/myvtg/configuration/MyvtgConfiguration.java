package co.siten.myvtg.configuration;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
/**
 * 
 * @author thomc
 *
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "co.siten.myvtg")
public class MyvtgConfiguration extends WebMvcConfigurerAdapter{
	
	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setViewClass(JstlView.class);
		viewResolver.setPrefix("/WEB-INF/views/");
		viewResolver.setSuffix(".jsp");
		registry.viewResolver(viewResolver);
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/**").addResourceLocations("/");
	}
	
	 @Bean
	    public static PropertyPlaceholderConfigurer properties(){
	      PropertyPlaceholderConfigurer ppc = new PropertyPlaceholderConfigurer();
	      ClassPathResource[] resources = new ClassPathResource[ ]
	        { new ClassPathResource( "api.properties" ) };
	      ppc.setLocations( resources );
	      ppc.setIgnoreUnresolvablePlaceholders( true );
	      return ppc;
	    }

}