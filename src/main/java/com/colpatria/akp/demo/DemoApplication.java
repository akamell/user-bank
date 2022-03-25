package com.colpatria.akp.demo;

import com.colpatria.akp.demo.security.JWTAuthFilter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@EnableWebSecurity
	@Configuration
	class WebSecurityConfig extends WebSecurityConfigurerAdapter {

		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http.csrf().disable()
				.addFilterAfter(new JWTAuthFilter(), UsernamePasswordAuthenticationFilter.class)
				.authorizeRequests()
				.anyRequest().permitAll();
				// .antMatchers(HttpMethod.POST, "/api/login").permitAll()
				// .antMatchers(HttpMethod.POST, "/api/user").permitAll()
				// .antMatchers(HttpMethod.GET,
				// 	"/v3/api-docs/**",
            	// 	"/swagger-ui/**",
				// 	"/swagger-ui.html"
				// ).permitAll()
				//.anyRequest().authenticated();
		}
	}
}
