//package com.itis.kikoff.security;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
///**
// * 26.02.2021
// * 6. spring-boot-demo
// *
// * @author Sidikov Marsel (First Software Engineering Platform)
// * @version v1.0
// */
//@EnableWebSecurity
//public class WebSecurity extends WebSecurityConfigurerAdapter {
//    @Autowired
//    @Qualifier("customUserDetailsService")
//    private UserDetailsService userDetailsService;
//
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
////        http.csrf().disable();
//        http.authorizeRequests()
//                .antMatchers("/papers/**").permitAll()
//                .antMatchers("/signUp").permitAll() // доступна всем
//                .antMatchers("/profile").authenticated()
//                .antMatchers("/ajax").authenticated()
//                .antMatchers("/files/**").permitAll()// только аутентифицирован
//                .and()
//                .formLogin() // описываем страницу входа
//                .loginPage("/signIn") //наша страница входа расположена по указанному адресу
//                .usernameParameter("email")// в качестве имени пользователя с этой страницы уходит email
//                .defaultSuccessUrl("/profile") // после успешного входа пользователь должен перейти на указанный url
//                .failureUrl("/signIn?error"); // если была ошибка, то на указанный url
//    }
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
//    }
//}
