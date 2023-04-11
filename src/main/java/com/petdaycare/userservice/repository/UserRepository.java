package com.petdaycare.userservice.repository;
import com.petdaycare.userservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByeMail(String eMail);

    User findByUserId(Long userId);


}