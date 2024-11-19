package org.jdc.githublogin.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.oauth2.client.CommonOAuth2Provider;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http)
        throws Exception {
        http.oauth2Login(c -> c.clientRegistrationRepository(clientRegistrationRepository()));
        http.authorizeHttpRequests(c -> c.anyRequest().authenticated());
        return http.build();
    }
    @Bean
    public ClientRegistrationRepository clientRegistrationRepository() {
        var c = clientRegistration();
        return new InMemoryClientRegistrationRepository(c);
    }
    private ClientRegistration clientRegistration() {
        return CommonOAuth2Provider.GITHUB
                .getBuilder("github")
                .clientId("Ov23lioW169jVLkzW8Qw")
                .clientSecret("f016bdbe8b9520218d13c31d73a6dcdbde315cd7")
                .build();
    }
}
