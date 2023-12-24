package com.prosilion.greeter.autoconfigure;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * bean-mapper for external-to-module application.properties file
 *
 * note: this class does not know if application.properties file exists.  that responsibility resides in
 *  @EnableConfigurationProperties(GreeterProperties.class) in GreeterAutoConfiguration.java
 */
@ConfigurationProperties(prefix = "prosilion.greeter")
@Getter
@Setter
@NoArgsConstructor
public class GreeterProperties {
  private String userName;
  private String morningMessage;
  private String afternoonMessage;
  private String eveningMessage;
  private String nightMessage;
}
