package life.zm.damdemo.damdemo;



import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.scheduling.annotation.EnableScheduling;

import org.springframework.transaction.annotation.EnableTransactionManagement;


@EnableTransactionManagement
@EnableScheduling

@MapperScan(basePackages = "life.zm.damdemo.damdemo.dao.mapper")
@SpringBootApplication
public class DamdemoApplication {
    public static void main(String[] args) { SpringApplication.run(DamdemoApplication.class, args);


    }

}
