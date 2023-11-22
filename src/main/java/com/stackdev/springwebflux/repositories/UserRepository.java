package com.stackdev.springwebflux.repositories;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import com.stackdev.springwebflux.models.Users;

@Repository
public interface UserRepository extends ReactiveCrudRepository<Users, Long> {

}
