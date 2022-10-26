package com.soulrebel.evaluacion_java_nisum.repository;

import com.soulrebel.evaluacion_java_nisum.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);

    User findById(UUID id);

    User findByToken(String token);


}
