package com.treboandtrebo.project.backend.repository;

import com.treboandtrebo.project.backend.entity.ApplicationUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

//TODO: replace this class with a correct ApplicationUser JPARepository implementation
@Repository
public interface ApplicationUserRepository extends JpaRepository<ApplicationUser, Long> {
    ApplicationUser findUserByEmail(String email);
}
