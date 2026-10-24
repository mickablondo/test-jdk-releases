package dev.mikablondo.test_jdk;

import java.security.GeneralSecurityException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MainApplication {

 static void main(String[] args) throws GeneralSecurityException {
  SpringApplication.run(MainApplication.class, args);

  Java25Features.testPrimitivePatterns(12);
  Java25Features.testScopedValues();
  Java25Features.testFlexibleConstructorBodies();
  Java25Features.testKeyDerivationFunction();
 }
}