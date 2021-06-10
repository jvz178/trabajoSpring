package com.example.demo.controller;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Usuarios;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
public class LoginController {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@PostMapping("/login/")
	public com.example.demo.entity.Usuarios login(@RequestParam("user") String username,
	@RequestParam("password") String pwd){
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(username,pwd));
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String token = getJWTToken(username);
		com.example.demo.entity.Usuarios usuario = new com.example.demo.entity.Usuarios();
		usuario.setUsername(username);
		usuario.setPassword(pwd);
		usuario.setToken(token);
		return usuario;
	}
	
	private String getJWTToken(String username) {
		
		String secretKey = "secret";
		List<GrantedAuthority> grantedAuthorities = AuthorityUtils
				.commaSeparatedStringToAuthorityList("ROLE_CLI");
		
		String token = Jwts.builder().setId("softtekJWT").setSubject(username)
				.claim("authorities", grantedAuthorities.stream()
						.map(GrantedAuthority::getAuthority)
						.collect(Collectors.toList()))
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis()+600000))
				.signWith(SignatureAlgorithm.HS512,
						secretKey.getBytes()).compact();
		return "Bearer "+token;
	}
	
	/*@GetMapping("/auth/login")
	public String login(Model model, @RequestParam(name="error", required=false) String error,
			@RequestParam(name="logout", required=false) String logout) {
		
		model.addAttribute("usuario", new Usuarios());
		model.addAttribute("error",error);
		model.addAttribute("logout",logout);
		return "login";
	}
	
	@GetMapping("/logueado")
	public String logueado() throws Exception {
		return "logueado";
	}
	
	@PostMapping("/logout")
	public String logout() {
		return "redirect:/login";
	}*/
}
