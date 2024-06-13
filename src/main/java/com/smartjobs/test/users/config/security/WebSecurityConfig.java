package com.smartjobs.test.users.config.security;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class WebSecurityConfig {

	private static final String[] AUTH_WHITELIST = {
			"/v3/api-docs/**",
			"/v3/api-docs",
			"/swagger-ui/**",
			"/v1/security/authenticate",
	};

	private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
	private final AuthenticationProvider authenticationProvider;
	private final JwtRequestFilter jwtRequestFilter;
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {

		httpSecurity
				.cors(AbstractHttpConfigurer::disable)
				.csrf(AbstractHttpConfigurer::disable)
				.authorizeHttpRequests(req ->
						req.requestMatchers(AUTH_WHITELIST)
								.permitAll()
								.requestMatchers(GET, "/v1/users").hasAnyAuthority("READ_PRIVILEGE")
								.requestMatchers(POST, "/v1/users").hasAnyAuthority("WRITE_PRIVILEGE")
								.anyRequest()
								.authenticated()
				)
				.exceptionHandling(aut ->
						aut.authenticationEntryPoint(jwtAuthenticationEntryPoint))
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.authenticationProvider(authenticationProvider)
				.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

		return  httpSecurity.build();
	}
}