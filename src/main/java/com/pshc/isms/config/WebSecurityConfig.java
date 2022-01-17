package com.pshc.isms.config;

import com.pshc.isms.service.security.CustomPasswordEncoder;
import com.pshc.isms.service.security.UserDetailServiceImp;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig  extends WebSecurityConfigurerAdapter {
    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailServiceImp();
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new CustomPasswordEncoder();
    }
    // description : 유저 인증정보 설정. 하드코딩 -> jdbc 인증정보 연결
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
    }
    // description : static file 같은 인증이 필요없는 리소스를 이곳에서 설정
    @Override
    public void configure(WebSecurity web) throws Exception {
//        web.ignoring().antMatchers("/css", "/js");
        web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
        web.ignoring().antMatchers("/index.html");
    }
    //description : 리소스 보안 부분
    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests().antMatchers().permitAll()
        http.authorizeRequests().antMatchers("/index").permitAll()
                .and()
                .authorizeRequests().antMatchers("/auth").permitAll()
                .and()
                .formLogin().loginPage("/auth/login.html").usernameParameter("userLoginId").passwordParameter("password").successForwardUrl("/index")
                .and()
                .logout().logoutRequestMatcher(new AntPathRequestMatcher("/auth/logout.html")).logoutSuccessUrl("/");

    }

}
