package com.github.lithualien.advertisement.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@Configuration
@EnableResourceServer
public class OAuth2ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic().disable()
                .formLogin().disable()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/webjars/**", "/v2/**", "/swagger-ui.html**", "/swagger-resources/**", "/api/users/v1/register", "/api/users/v1/login").permitAll()
                .antMatchers(HttpMethod.POST,"/api/cities/**").hasAnyAuthority("ADMIN", "MANAGER")
                .antMatchers(HttpMethod.PUT,"/api/cities/**", "/api/counties/**").hasAnyAuthority("ADMIN", "MANAGER")
                .antMatchers(HttpMethod.GET, "/api/users/information/**").hasAnyAuthority("ADMIN", "MANAGER", "USER")
                .antMatchers(HttpMethod.GET, "/api/**").permitAll()
                .antMatchers(HttpMethod.POST, "/api/**").hasAnyAuthority("ADMIN", "MANAGER", "USER")
                .antMatchers(HttpMethod.PUT, "/api/**").hasAnyAuthority("ADMIN", "MANAGER", "USER")
                .antMatchers(HttpMethod.DELETE, "/api/**").hasAnyAuthority("ADMIN", "MANAGER");
    }

}
