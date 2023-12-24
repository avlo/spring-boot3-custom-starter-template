package com.prosilion.greeter.autoconfigure;

import com.prosilion.greeter.library.Greeter;
import com.prosilion.greeter.library.GreeterConfigParams;
import com.prosilion.greeter.library.GreetingConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnClass(Greeter.class)

/**
 *  GreeterProperties bean created (and autowired into this class) with its fields in either one of two states:
 *    1) fields properly populated iff:
 *      a) an application.properties file exists
 *      b) requisite fields are defined within the file
 *  otherwise:
 *    2) field values set to null
 */
@EnableConfigurationProperties(GreeterProperties.class)
public class GreeterAutoConfiguration {
  private final GreeterProperties greeterProperties;

  @Autowired
  public GreeterAutoConfiguration(GreeterProperties greeterProperties) {
    this.greeterProperties = greeterProperties;
  }

  /**
   * GreetingConfig bean manually created here iff user-defined GreetingConfig bean does not exist.
   *
   * furthermore, this GreetingConfig instances field values are populated in either one of two ways:
   *   1) via GreeterProperties bean non-null fields (handled by @EnableConfigurationProperties(GreeterProperties.class), above)
   * otherwise, if any GreeterProperties bean fields are null:
   *   2) set explicit/default string values as seen below
   *
   * careful/note: any GreeterProperties bean with any/all fields pre-populated can only be done via EXTERNAL
   * application.properties file.  i.e., the "greeter-spring-boot-sample-app" application.properties file is EXTERNAL
   */
  @Bean
  @ConditionalOnMissingBean
  public GreetingConfig greeterConfig() {
    String userName = greeterProperties.getUserName() == null ? System.getProperty("user.name") : greeterProperties.getUserName();
    String morningMessage = greeterProperties.getMorningMessage() == null ? "manual/code Good Morning" : greeterProperties.getMorningMessage();
    String afternoonMessage = greeterProperties.getAfternoonMessage() == null ? "manual/code Good Afternoon" : greeterProperties.getAfternoonMessage();
    String eveningMessage = greeterProperties.getEveningMessage() == null ? "manual/code Good Evening" : greeterProperties.getEveningMessage();
    String nightMessage = greeterProperties.getNightMessage() == null ? "manual/code Good Night" : greeterProperties.getNightMessage();

    GreetingConfig greetingConfig = new GreetingConfig();
    greetingConfig.put(GreeterConfigParams.USER_NAME, userName);
    greetingConfig.put(GreeterConfigParams.MORNING_MESSAGE, morningMessage);
    greetingConfig.put(GreeterConfigParams.AFTERNOON_MESSAGE, afternoonMessage);
    greetingConfig.put(GreeterConfigParams.EVENING_MESSAGE, eveningMessage);
    greetingConfig.put(GreeterConfigParams.NIGHT_MESSAGE, nightMessage);
    return greetingConfig;
  }

  @Bean
  @ConditionalOnMissingBean
  public Greeter greeter(GreetingConfig greetingConfig) {
    return new Greeter(greetingConfig);
  }
}
