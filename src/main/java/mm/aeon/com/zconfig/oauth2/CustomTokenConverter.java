package mm.aeon.com.zconfig.oauth2;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import mm.aeon.com.dto.LoginUserDto;

public class CustomTokenConverter extends JwtAccessTokenConverter {
	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
		LoginUserDto user = (LoginUserDto) authentication.getPrincipal();
		final Map<String, Object> additionalInfo = new HashMap<String, Object>();

		additionalInfo.put("userInformationResDto", user.getUserInformationResDto());

		((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInfo);
		accessToken = super.enhance(accessToken, authentication);
		return accessToken;
	}
}
