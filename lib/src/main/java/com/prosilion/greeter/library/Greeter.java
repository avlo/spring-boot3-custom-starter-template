package com.prosilion.greeter.library;

import java.time.LocalDateTime;

/**
 * A bean of this class instantiates in either one of two ways:
 * 1) If container doesn't already have one, GreeterAutoConfiguration.java
 *      will create one.
 * 2) Pre-existing by some other mechanism (currently does not occur)
 */
public class Greeter {
  private final GreetingConfig greetingConfig;

  /**
   * currently, GreetingConfig parameter/object/bean DI/wired exclusively by GreeterAutoConfiguration.java
   */
  public Greeter(GreetingConfig greetingConfig) {
    this.greetingConfig = greetingConfig;
  }

  public String greet(LocalDateTime localDateTime) {
    String name = greetingConfig.getProperty(GreeterConfigParams.USER_NAME);
    int hourOfDay = localDateTime.getHour();

    if (hourOfDay >= 5 && hourOfDay < 12) {
      return String.format("Hello %s, %s", name, greetingConfig.get(GreeterConfigParams.MORNING_MESSAGE));
    } else if (hourOfDay >= 12 && hourOfDay < 17) {
      return String.format("Hello %s, %s", name, greetingConfig.get(GreeterConfigParams.AFTERNOON_MESSAGE));
    } else if (hourOfDay >= 17 && hourOfDay < 20) {
      return String.format("Hello %s, %s", name, greetingConfig.get(GreeterConfigParams.EVENING_MESSAGE));
    } else {
      return String.format("Hello %s, %s", name, greetingConfig.get(GreeterConfigParams.NIGHT_MESSAGE));
    }
  }

  public String greet() {
    return greet(LocalDateTime.now());
  }
}
