package com.task.faiflyapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.task")
public class FaiflyAppApplication {

  public static void main(String[] args) {
    SpringApplication.run(FaiflyAppApplication.class, args);
  }

}
