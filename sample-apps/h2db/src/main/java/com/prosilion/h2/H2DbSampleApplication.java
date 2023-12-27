package com.prosilion.h2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.prosilion.h2.H2Database;

import javax.sql.DataSource;

@SpringBootApplication
public class H2DbSampleApplication implements CommandLineRunner {
  @Autowired
  private H2Database h2Database;

  public static void main(String[] args) {
    SpringApplication.run(H2DbSampleApplication.class, args);
  }

  @Override
  public void run(String... args) throws Exception {
    DataSource dataSource = h2Database.getDataSource();
    System.out.println(String.format("####################### [%s] #####################", dataSource.getConnection()));
  }
}
