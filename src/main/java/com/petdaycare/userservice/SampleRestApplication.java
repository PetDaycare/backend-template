package com.petdaycare.userservice;
import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableEncryptableProperties
public class SampleRestApplication {

    public static void main(String[] args) {

        SpringApplication.run(SampleRestApplication.class, args);
    }

}
