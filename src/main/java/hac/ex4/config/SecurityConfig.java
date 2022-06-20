package hac.ex4.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * Security Config
 */
@Configuration
@EnableWebMvc
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        // user builder
        User.UserBuilder user = User.withDefaultPasswordEncoder();

        // register admin with role ADMIN
        auth.inMemoryAuthentication().withUser(user.username("admin").password("admin").roles("ADMIN"));
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .anyRequest().authenticated()

                // config custom login
                .and()
                .formLogin()
                .loginPage("/admin/login")
                .loginProcessingUrl("/authenticateTheUser")
                .permitAll()

                // config logout
                .and()
                .logout().permitAll();

        http.formLogin().defaultSuccessUrl("/admin/home", true);
    }


}
