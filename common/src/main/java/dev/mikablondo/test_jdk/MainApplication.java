package dev.mikablondo.test_jdk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class MainApplication {

 public static void main(String[] args) {
  ConfigurableApplicationContext context = SpringApplication.run(MainApplication.class, args);
  JavaFeature feature = context.getBean(JavaFeature.class);
  feature.run();
 }
}