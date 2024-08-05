package com.diefthyntis.ConnexionWebapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.diefthyntis.ConnexionWebapp.model.User;


public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
