package com.example.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.demo.services.ServicioCliente;
import com.example.demo.services.impl.ImplServicioCliente;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	@Qualifier("servicioCliente")
	private ImplServicioCliente servicioCliente;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		
		http
		.authorizeRequests()
		.antMatchers("/","/auth/**","/css/**","/assets/**","/imagenes/**","/js/**","/registrarse/**","/nuevoCliente**")
		.permitAll()
		.anyRequest().authenticated()
		.and()
	.formLogin()
		.loginPage("/auth/login")
		.defaultSuccessUrl("/logueado",true)
		.loginProcessingUrl("/auth/logueado")
		.permitAll()
		.and()
	.logout()
		.permitAll()
		.logoutUrl("logout")
		.logoutSuccessUrl("/auth/login?logout")
		.permitAll();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		
		auth.userDetailsService(servicioCliente).passwordEncoder(PasswordEncoder());
	}
	
	@Bean
	public BCryptPasswordEncoder PasswordEncoder() {
		
		return new BCryptPasswordEncoder();
	}
}
