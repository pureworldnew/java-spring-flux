package com.stackdev.springwebflux.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackdev.springwebflux.models.Users;
import com.stackdev.springwebflux.repositories.UserRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class UserService {
	
	@Autowired
	UserRepository userRepository;
	
	// Mono 0 - 1 // single 
	// Flux 0 - N // reactive sequence of items
	
	public Mono<Users> getUserById(Long id) { return userRepository.findById(id);}
	
	public Flux<Users> getUsers() { return userRepository.findAll();}
	
	public void addUser(Users users) {
		userRepository.save(users).subscribe();
	}
	
	public Mono<Users> updateUser(Users user) {
		return userRepository.findById(user.getId())
				.switchIfEmpty(Mono.error(new Exception("User Not Found")))
				.map(olderUser -> {
					if(user.getSurname() != null) olderUser.setSurname(user.getSurname());
					if(user.getUsername() != null) olderUser.setUsername(user.getUsername());
					if(user.getName() != null) olderUser.setName(user.getName());
					if(user.getEmail() != null) olderUser.setEmail(user.getEmail());
					return olderUser;
				})
				.flatMap(userRepository::save);
	}
	
	public Mono<Void> deleUser(Long id) {
		return userRepository.deleteById(id)
			.switchIfEmpty(Mono.error(new Exception("User Not Found")));
	}
	
	
}
