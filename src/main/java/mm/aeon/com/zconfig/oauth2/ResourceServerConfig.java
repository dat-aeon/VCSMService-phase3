package mm.aeon.com.zconfig.oauth2;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;

/**
 * <br/>
 * This class is used to configure how the resource owner/user access the
 * resources/api.<br/>
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

	private static final String RESOURCE_ID = "resource_id";

	@Override
	public void configure(ResourceServerSecurityConfigurer resources) {
		resources.resourceId(RESOURCE_ID).stateless(false);
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.anonymous().disable().authorizeRequests().antMatchers("/loan-calculator/**").access("hasAuthority('CUSTOMER')").antMatchers("/news-info/**")
				.access("hasAuthority('CUSTOMER')").antMatchers("/promotions-info/**").access("hasAuthority('CUSTOMER')").antMatchers("/customer-info-manage/**")
				.access("hasAuthority('CUSTOMER')").antMatchers("/coupon-info/**").access("hasAuthority('CUSTOMER')").and().exceptionHandling()
				.accessDeniedHandler(new OAuth2AccessDeniedHandler());
	}

}
