package ru.nchernetsov.oauth.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class CustomAccessTokenConverter extends DefaultAccessTokenConverter {

    @Autowired
    private TokenStore tokenStore;

    @Override
    public OAuth2Authentication extractAuthentication(Map<String, ?> claims) {
        OAuth2Authentication authentication = super.extractAuthentication(claims);
        authentication.setDetails(claims);
        return authentication;
    }

    public Map<String, Object> getExtraInfo(OAuth2Authentication auth) {
        OAuth2AuthenticationDetails details = (OAuth2AuthenticationDetails) auth.getDetails();
        OAuth2AccessToken accessToken = tokenStore.readAccessToken(details.getTokenValue());
        return accessToken.getAdditionalInformation();
    }

}