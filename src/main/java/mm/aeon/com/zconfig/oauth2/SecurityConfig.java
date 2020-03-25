package mm.aeon.com.zconfig.oauth2;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * <br/>
 * This is overall security configuration.
 */

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter implements WebMvcConfigurer {

	@Resource(name = "UserService")
	private UserDetailsService userDetailsService;

	@Autowired
	public CustomPasswordEncoder passwordEncoder;

	@Value("${properties.imageBaseFilePath}")
	private String imageBaseFilePath;

	@Value("${properties.couponImageFolder}")
	private String couponImageFolder;

	@Value("${properties.profileImageFolder}")
	private String profileImageFolder;

	@Value("${properties.newsImageFolder}")
	private String newsImageFolder;

	@Value("${properties.promotionImageFolder}")
	private String promotionImageFolder;

	@Value("${properties.messageImageFolder}")
	private String messageImageFolder;

	@Value("${properties.freeMessageImageFolder}")
	private String freeMessageImageFolder;

	@Value("${properties.plMessageImageFolder}")
	private String plMessageImageFolder;

	@Value("${properties.digitalApplicationImageFolder}")
	private String digitalApplicationImageFolder;

	@Value("${properties.purchaseImageFolder}")
	private String purchaseImageFolder;

	@Value("${properties.howToUseVideoFolder}")
	private String howToUseVideoFolder;

	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Autowired
	public void globalUserDetails(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().anonymous().disable().authorizeRequests().antMatchers("/api/**").permitAll();
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/profile-image-files/**").addResourceLocations("file:///" + imageBaseFilePath + profileImageFolder);
		registry.addResourceHandler("/coupon-image-files/**").addResourceLocations("file:///" + imageBaseFilePath + couponImageFolder);
		registry.addResourceHandler("/news-image-files/**").addResourceLocations("file:///" + imageBaseFilePath + newsImageFolder);
		registry.addResourceHandler("/promotion-image-files/**").addResourceLocations("file:///" + imageBaseFilePath + promotionImageFolder);
		registry.addResourceHandler("/message-image-files/**").addResourceLocations("file:///" + imageBaseFilePath + messageImageFolder);
		registry.addResourceHandler("/free-message-image-files/**").addResourceLocations("file:///" + imageBaseFilePath + freeMessageImageFolder);
		registry.addResourceHandler("/pl-message-image-files/**").addResourceLocations("file:///" + imageBaseFilePath + plMessageImageFolder);
		registry.addResourceHandler("/digital-application-image-files/**").addResourceLocations("file:///" + imageBaseFilePath + "/" + digitalApplicationImageFolder + "/");
		registry.addResourceHandler("/purchase-image-files/**").addResourceLocations("file:///" + imageBaseFilePath + "/" + purchaseImageFolder + "/");
		registry.addResourceHandler("/how-to-use-video/**").addResourceLocations("file:///" + imageBaseFilePath + howToUseVideoFolder);
	}
}