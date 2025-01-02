package dev.mikablondo.test_jdk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MainApplication {

 public static void main(String[] args) {
  SpringApplication.run(MainApplication.class, args);

  Java21Features.testSequencedCollection();
  Java21Features.testPatternMatching();
  Java21Features.testRecordPatterns();
  Java21Features.testStringTemplates();
  Java21Features.testScopedValues();
  Java21Features.testVirtualThreads();
 }
}