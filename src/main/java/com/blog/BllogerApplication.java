package com.blog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@SpringBootApplication
public class BllogerApplication {

	public static void main(String[] args) {
		SpringApplication.run(BllogerApplication.class, args);
	}

	@Bean
	public PasswordEncoder getEncodedPassword(){
		return new BCryptPasswordEncoder();
	}
//@Bean
//	public UserDetailsService userDetailsService(){
//		return new InMemoryUserDetailsManager();
//	}

}
