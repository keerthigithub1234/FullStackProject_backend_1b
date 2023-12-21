package com.backend.fullstackbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.fullstackbackend.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
