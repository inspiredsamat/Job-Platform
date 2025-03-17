package kz.inspiredsamat;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@Slf4j
@EnableConfigurationProperties
@SpringBootApplication
public class TelegramApplication {
    public static void main(String[] args) {
        SpringApplication.run(TelegramApplication.class, args);
    }
}