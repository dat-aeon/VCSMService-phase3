package mm.aeon.com.zconfig.oauth2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import mm.aeon.com.zconfig.AppConfig;

/**
 * <br/>
 * This class is used to configure how the OAuth authorization server
 * works.<br/>
 * 1. supported grant types (e.g. authorization code grant)<br/>
 * 2. authorization code service, to store authorization codes<br/>
 * 3. token store, to store access and refresh tokens (e.g. JwtTokenStore,
 * JdbcTokeStore)<br/>
 * In this class, JwtTokenStore is used for stateless.
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

	static final String GRANT_TYPE_PASSWORD = "password";
	static final String AUTHORIZATION_CODE = "authorization_code";
	static final String REFRESH_TOKEN = "refresh_token";
	static final String IMPLICIT = "implicit";
	static final String SCOPE_READ = "read";
	static final String SCOPE_WRITE = "write";
	static final String SCOPE_TRUST = "trust";
	static final String SCOPE_ADMIN = "ADMIN";
	static final String SCOPE_USER = "USER";
	static final int ACCESS_TOKEN_VALIDITY_SECONDS = 2 * 60 * 60;
	static final int FREFRESH_TOKEN_VALIDITY_SECONDS = 24 * 60 * 60;

	@Autowired
	private AppConfig appConfig;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	public CustomPasswordEncoder passwordEncoder;

	@Bean
	public JwtAccessTokenConverter accessTokenConverter() {
		CustomTokenConverter converter = new CustomTokenConverter();
		converter.setSigningKey("123456789");
		return converter;
	}

	@Bean
	public TokenStore tokenStore() {
		return new JwtTokenStore(accessTokenConverter());
	}

	@Override
	public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
		oauthServer.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()").passwordEncoder(passwordEncoder);
	}

	@Override
	public void configure(ClientDetailsServiceConfigurer configurer) throws Exception {
		configurer.inMemory().withClient(appConfig.getClientId()).secret(appConfig.getClientIdPassword())
				.authorizedGrantTypes(GRANT_TYPE_PASSWORD, AUTHORIZATION_CODE, REFRESH_TOKEN, IMPLICIT).scopes(SCOPE_READ, SCOPE_WRITE, SCOPE_TRUST)
				.accessTokenValiditySeconds(ACCESS_TOKEN_VALIDITY_SECONDS).refreshTokenValiditySeconds(FREFRESH_TOKEN_VALIDITY_SECONDS);
	}

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		endpoints.tokenStore(tokenStore()).authenticationManager(authenticationManager).accessTokenConverter(accessTokenConverter());
	}

}