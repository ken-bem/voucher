package com.rumroute.core;

import com.rumroute.common.FlashMessage;
import com.rumroute.dao.UserCRUD.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
public class    WebSecurityConfig  extends WebSecurityConfigurerAdapter{

    @Autowired
    private UserService service;


    @Bean
    public BCryptPasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http
           .httpBasic()
                .and()
                    .authorizeRequests()
                    .antMatchers(HttpMethod.POST, "/login", "/api/redeem_voucher")
                .permitAll()
                    .antMatchers("/resources/**", "/**")
                .permitAll()
                .and()
                .formLogin()
                    .loginPage("/login")
                    .permitAll()
                    .successHandler(loginSuccess())
                    .failureHandler(loginFailure())
                    .and()
                .logout()
                    .permitAll();

    }


    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                .antMatchers(HttpMethod.POST, "/api/spotsadd","/api/register","/api/checkEmail", "/api/deleteSpot")
                .and()
                .ignoring()
                    .antMatchers(HttpMethod.GET,"/api/**")
                .and()
                    .ignoring()
                .antMatchers("/test/**");
    }


    private AuthenticationSuccessHandler loginSuccess() {
        return (request, response, authentication) -> response.sendRedirect("/");
    }

    private AuthenticationFailureHandler loginFailure() {

        return (request, response, exception) -> {
            exception.printStackTrace();

            request.getSession()
                    .setAttribute("flash", new FlashMessage("Incorrect username and/or password. Please try again.", FlashMessage.Status.FAILURE));

            response.sendRedirect("/login");
        };
    }


    @Autowired
    private void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception{

        auth.authenticationProvider(authProvider());
        auth.inMemoryAuthentication().withUser("kenneth").password("boulab1031").roles("Tourist");
    }

    @Bean
    public DaoAuthenticationProvider authProvider() {

        DaoAuthenticationProvider authentication = new DaoAuthenticationProvider();

        authentication.setUserDetailsService(service);
        authentication.setPasswordEncoder(encoder());
        return authentication;

    }


    @Bean(name = "auth")
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }
}
