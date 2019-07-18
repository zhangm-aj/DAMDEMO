package life.zm.damdemo.damdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class DamdemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DamdemoApplication.class, args);
    }

}
