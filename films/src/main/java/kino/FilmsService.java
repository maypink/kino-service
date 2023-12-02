package kino;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class FilmsService {

    public static void main(String[] args) {
        SpringApplication.run(FilmsService.class, args);
    }
}
