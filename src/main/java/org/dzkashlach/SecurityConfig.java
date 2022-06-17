package org.dzkashlach;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter
{
    @Override
    public void configure(WebSecurity webSecurity)
    {
        webSecurity
                .ignoring()
//                .antMatchers(HttpMethod.GET,"/index","/done");
                .antMatchers(HttpMethod.GET,"/index","/done","/token")
                .antMatchers(HttpMethod.POST,"/payment-requests");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
//                        .antMatchers(HttpMethod.POST,"/payment-requests").authenticated()
                        .antMatchers(HttpMethod.GET,"/signin").authenticated()
//                        .anyRequest().permitAll()
                )
                .oauth2Login(withDefaults());
        http.csrf().disable();
    }
}
