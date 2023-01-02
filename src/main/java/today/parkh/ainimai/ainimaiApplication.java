package today.parkh.ainimai;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ainimaiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ainimaiApplication.class, args);
    }

}
