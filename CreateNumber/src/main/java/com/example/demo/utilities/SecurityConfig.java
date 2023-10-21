package com.example.demo.utilities;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
//@EnableMethodSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	CustomAuthenticationProvider customAuthenticationProvider;
	
	/*
	 * @Bean public InMemoryUserDetailsManager userDetailsService(PasswordEncoder
	 * passwordEncoder) { UserDetails user =
	 * User.withUsername("user").password(passwordEncoder.encode("password")).roles(
	 * "USER").build();
	 * 
	 * UserDetails admin =
	 * User.withUsername("admin").password(passwordEncoder.encode("admin")).roles(
	 * "USER", "ADMIN") .build();
	 * 
	 * return new InMemoryUserDetailsManager(user, admin); }
	 * 
	 * @Bean public SecurityFilterChain filterChain(HttpSecurity http) throws
	 * Exception { http.csrf(csrf->csrf.disable()).authorizeRequests(auth->{
	 * auth.antMatchers("/test").hasRole("ynbd"); }).httpBasic(); return
	 * http.build(); }
	
	*/
	 @Override
	    public void configure(WebSecurity web) {
	        web.ignoring().antMatchers("/create");
	    }
 
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.httpBasic().and().authorizeRequests()
		.antMatchers(HttpMethod.POST,"/login")
		.authenticated()
		// Make H2-Console non-secured; for debug purposes
		.and().csrf().ignoringAntMatchers("/h2-console/**")
		// Allow pages to be loaded in frames from
		// the same origin; needed for H2-Console
		.and().headers().frameOptions().sameOrigin().and().cors().and().csrf().disable();
		
		
		/**http.httpBasic().and().authorizeRequests()
		
		//.antMatchers(HttpMethod.POST, "/create/**").permitAll()
		.antMatchers("/login").authenticated()
		// Make H2-Console non-secured; for debug purposes
		.and().csrf().ignoringAntMatchers("/h2-console/**")
		// Allow pages to be loaded in frames from
		// the same origin; needed for H2-Console
		.and().headers().frameOptions().sameOrigin().and().cors().and().csrf().disable();**/
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
		return encoder;
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(customAuthenticationProvider);
	}

}
