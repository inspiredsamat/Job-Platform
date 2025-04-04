package kz.inspiredsamat.telegram.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;


@Configuration
@Getter
@Setter
@ConfigurationProperties("telegram.bot")
public class BotConfiguration {

  private String token;
  private String username;
}
