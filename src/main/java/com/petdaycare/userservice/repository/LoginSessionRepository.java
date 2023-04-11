package com.petdaycare.userservice.repository;
import com.petdaycare.userservice.model.LoginSession;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoginSessionRepository extends JpaRepository<LoginSession, String> {

    long deleteByLoginToken(String loginToken);

    LoginSession findByLoginToken(String loginToken);

}