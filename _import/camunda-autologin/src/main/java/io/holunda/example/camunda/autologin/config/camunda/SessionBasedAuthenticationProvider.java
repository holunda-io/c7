package io.holunda.example.camunda.autologin.config.camunda;

import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.rest.security.auth.AuthenticationProvider;
import org.camunda.bpm.engine.rest.security.auth.AuthenticationResult;
import org.camunda.bpm.engine.rest.security.auth.ProcessEngineAuthenticationFilter;
import org.camunda.bpm.webapp.impl.security.auth.Authentications;
import org.camunda.bpm.webapp.impl.security.auth.ContainerBasedAuthenticationFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.DispatcherType;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.EnumSet;

import static java.util.Collections.singletonMap;

public class SessionBasedAuthenticationProvider implements AuthenticationProvider {

    @Override
    public AuthenticationResult extractAuthenticatedUser(HttpServletRequest request, ProcessEngine engine) {
        Authentications authentications = Authentications.getFromSession(request.getSession());

        final String userId;
        if (authentications != null && authentications.hasAuthenticationForProcessEngine(engine.getName())) {
            userId = authentications.getAuthenticationForProcessEngine(engine.getName()).getName();
        } else {
            userId = "admin";
        }
        return new AuthenticationResult(userId, true);
    }

    @Override
    public void augmentResponseByAuthenticationChallenge(HttpServletResponse response, ProcessEngine engine) {
        // noop
    }

    @Configuration
    public static class CamundaWebAppsSecurityConfiguration {

        @Bean
        public FilterRegistrationBean<ContainerBasedAuthenticationFilter> containerBasedAuthenticationFilterRegistrationBean() {
            FilterRegistrationBean<ContainerBasedAuthenticationFilter> registrationBean = new FilterRegistrationBean<>();
            registrationBean.setFilter(new ContainerBasedAuthenticationFilter());
            registrationBean.setInitParameters(singletonMap(
                ProcessEngineAuthenticationFilter.AUTHENTICATION_PROVIDER_PARAM,
                SessionBasedAuthenticationProvider.class.getCanonicalName()));
            registrationBean.addUrlPatterns("/app/*", "/api/*", "/lib/*");
            registrationBean.setDispatcherTypes(EnumSet.of(DispatcherType.REQUEST));
            return registrationBean;
        }
    }
}
