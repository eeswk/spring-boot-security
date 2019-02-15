package com.zerock.security;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.sql.DataSource;

@Log
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        log.info("security config.....");

        http.authorizeRequests().antMatchers("/guest/**").permitAll();
        http.authorizeRequests().antMatchers("/manager/**").hasRole("MANAGER");
        http.authorizeRequests().antMatchers("/admin/**").hasRole("ADMIN");
        http.formLogin().loginPage("/login");
        http.exceptionHandling().accessDeniedPage("/accessDenied");
        //세션 무효화
        http.logout().logoutUrl("/logout").invalidateHttpSession(true);

        //Custom 인증
        http.userDetailsService(zerockUserService);

    }
/*
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        log.info("build Auth global....");

        //inMemory
//        auth.inMemoryAuthentication()
//                .withUser("manager")
//                .password("{noop}1111")
//                .roles("MANAGER");

        //jdbc
        String query1 = "select uid username, upw password, true enabled from tbl_members where uid = ?";
        String query2 = "select member uid, role_name role from tbl_member_roles where member = ?";

        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery(query1)
                .rolePrefix("ROLE_")
                .authoritiesByUsernameQuery(query2)
                .passwordEncoder(new BCryptPasswordEncoder());

    }


    //jdbc 인증
    @Autowired
    DataSource dataSource;
*/
    //Custom 인증
    @Autowired
    ZerockUserService zerockUserService;





}
