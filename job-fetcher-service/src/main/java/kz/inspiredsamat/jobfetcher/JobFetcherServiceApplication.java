package kz.inspiredsamat.jobfetcher;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class JobFetcherServiceApplication {

  public static void main(String[] args) {
    SpringApplication.run(JobFetcherServiceApplication.class, args);
  }
}