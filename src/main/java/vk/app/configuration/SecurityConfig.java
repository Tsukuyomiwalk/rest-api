package vk.app.configuration;

import vk.app.authorization.AuthorizationReject;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static vk.app.authorization.userInfo.Access.*;
import static vk.app.authorization.userInfo.AccessRoles.*;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final AuthorizationFilter filter;
    private final AuthenticationProvider authenticationProvider;
    private final AuthorizationReject authEntryPointReject;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests()
                .antMatchers("/api/auth/**").permitAll()
                .antMatchers("/api/posts/**").hasAnyRole(ADMIN.name(), POSTS.name())
                .antMatchers(HttpMethod.GET, "/api/posts/**").hasAnyAuthority(ADMIN_READ.name(), POSTS_READ.name())
                .antMatchers(HttpMethod.POST, "/api/posts/**").hasAnyAuthority(ADMIN_CREATE.name(), POSTS_CREATE.name())
                .antMatchers(HttpMethod.PUT, "/api/posts/**").hasAnyAuthority(ADMIN_UPDATE.name(), POSTS_UPDATE.name())
                .antMatchers(HttpMethod.DELETE, "/api/posts/**").hasAnyAuthority(ADMIN_DELETE.name(), POSTS_DELETE.name())
                .antMatchers(HttpMethod.GET, "/api/users/**").hasAnyAuthority(ADMIN_READ.name(), USERS_READ.name())
                .antMatchers(HttpMethod.POST, "/api/users/**").hasAnyAuthority(ADMIN_CREATE.name(), USERS_CREATE.name())
                .antMatchers(HttpMethod.PUT, "/api/users/**").hasAnyAuthority(ADMIN_UPDATE.name(), USERS_UPDATE.name())
                .antMatchers(HttpMethod.DELETE, "/api/users/**").hasAnyAuthority(ADMIN_DELETE.name(), USERS_DELETE.name())
                .anyRequest().authenticated()
                .and().authenticationProvider(authenticationProvider)
                .addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling().authenticationEntryPoint(authEntryPointReject);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
