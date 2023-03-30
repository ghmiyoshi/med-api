package br.com.alura.med;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class MedApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(MedApiApplication.class, args);
        System.out.println(new BCryptPasswordEncoder().encode("123456"));
    }

}
