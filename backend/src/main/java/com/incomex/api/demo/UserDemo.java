package com.incomex.api.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.incomex.api.entities.User;
import com.incomex.api.repositories.UserRepository;

@Component
public class UserDemo {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@EventListener
    public void appReady(ApplicationReadyEvent event) {
		User user =  userRepository.findByName("test");
		if(user == null) {
			user = new User();
			user.setName("test");
			user.setPassword(passwordEncoder.encode("test"));
			userRepository.save(user);
		}
    }
}
