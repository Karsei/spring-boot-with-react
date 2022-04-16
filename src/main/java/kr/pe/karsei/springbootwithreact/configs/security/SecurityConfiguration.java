package kr.pe.karsei.springbootwithreact.configs.security;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.CorsUtils;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
public class SecurityConfiguration {
    @Configuration
    @Order(98)
    static class totalAdapter extends WebSecurityConfigurerAdapter {
        @Override
        public void configure(WebSecurity web) {
            web
                    // 정적 자원은 Security 설정을 적용하지 않는다.
                    .ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
        }

        @Override
        protected void configure(HttpSecurity httpSecurity) throws Exception {
            httpSecurity
                    .antMatcher("/api/**")
                    .csrf().disable()
                    .httpBasic().disable()
                    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                    .and()
                        .authorizeRequests()
                        .requestMatchers(CorsUtils::isPreFlightRequest).permitAll()
                        .antMatchers("/api/**").permitAll()
                    .and()
                        .exceptionHandling()
                        .authenticationEntryPoint((request, response, authException) -> response.sendError(HttpServletResponse.SC_UNAUTHORIZED))
                        .accessDeniedHandler((request, response, accessDeniedException) -> response.sendError(HttpServletResponse.SC_FORBIDDEN));
        }

        @Bean
        public CorsConfigurationSource corsConfigurationSource() {
            CorsConfiguration configuration = new CorsConfiguration();
            configuration.addAllowedOrigin("http://localhost:3000");
            configuration.addAllowedHeader("*");
            configuration.setAllowedMethods(List.of("GET", "POST", "PATCH", "DELETE", "OPTIONS"));
            //configuration.addAllowedMethod("*");
            configuration.setAllowCredentials(true);

            UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
            source.registerCorsConfiguration("/**", configuration);
            return source;
        }
    }

    @Configuration
    @Order(99)
    static class ActuatorAdapter extends WebSecurityConfigurerAdapter {
        @Override
        protected void configure(HttpSecurity httpSecurity) throws Exception {
            httpSecurity
                    .antMatcher("/actuator/**")
                    .csrf().disable()
                    .httpBasic().and()
                    .authorizeRequests()
                        .antMatchers("/actuator/health").permitAll()
                        .antMatchers("/actuator/**").authenticated()
                    .and()
                        .exceptionHandling()
                        .accessDeniedHandler((request, response, accessDeniedException) -> response.sendError(HttpServletResponse.SC_FORBIDDEN));
        }
    }
}