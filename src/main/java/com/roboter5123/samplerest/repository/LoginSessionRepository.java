package com.roboter5123.samplerest.repository;
import com.roboter5123.samplerest.model.LoginSession;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoginSessionRepository extends JpaRepository<LoginSession, String> {

    long deleteByLoginToken(String loginToken);

    LoginSession findByLoginToken(String loginToken);

}