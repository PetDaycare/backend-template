package com.roboter5123.samplerest.repository;
import com.roboter5123.samplerest.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUserId(Long userId);


}