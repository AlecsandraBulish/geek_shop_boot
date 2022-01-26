package ru.bulish.spring.geek_shop_boot.configuration;


import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import ru.bulish.spring.geek_shop_boot.component.ShoppingCart;
import ru.bulish.spring.geek_shop_boot.service.ServiceProduct;

/**
 * Class Security Configuration
 * @author spring security
 * @see WebSecurityConfigurerAdapter
 */
@RequiredArgsConstructor
@EnableWebSecurity
public class MySecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * Field userDetailsService keeps details about each user
     * @see UserDetailsService
     */
    private final UserDetailsService userDetailsService;

    /**
     * Method limits access to specific url
     * @param http is parameter that coming from url and then is used for checking if specific user has access to
     * specific url or don't
     * @see HttpSecurity
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/product/new/**").hasAnyRole("ADMIN", "MANAGER")
                .antMatchers(HttpMethod.POST, "/category/**", "/product/**").hasAnyRole("ADMIN", "MANAGER")
                .antMatchers("/user/list").hasAnyRole("ADMIN")
                .antMatchers("/user/new").hasAnyRole("ADMIN", "MANAGER")
                .anyRequest().permitAll()
                .and()
                .logout()
                .logoutSuccessUrl("/product/list")
                .and()
                .formLogin()
                .defaultSuccessUrl("/product/list");
    }

    /**
     * Method create a bean of BCryptPasswordEncoder
     * @see BCryptPasswordEncoder
     */
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Method create a bean of DaoAuthenticationProvider
     * @see DaoAuthenticationProvider
     */
    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        authenticationProvider.setUserDetailsService(userDetailsService);
        return authenticationProvider;
    }
}


