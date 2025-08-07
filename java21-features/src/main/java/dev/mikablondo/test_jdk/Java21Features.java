package dev.mikablondo.test_jdk;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.SequencedCollection;
import java.util.concurrent.Executors;

@Component
@ConditionalOnProperty("#{T(java.lang.Runtime).version().feature() >= 21}")
public class Java21Features implements JavaFeature {

 @Override
 public void run() {
  System.out.println("Test JDK 21 features");
  testSequencedCollection();
  testPatternMatching();
  testRecordPatterns();
  testStringTemplates();
  testScopedValues();
  testVirtualThreads();
 }

 public static void testSequencedCollection() {
  SequencedCollection<String> sc = new ArrayList<>();
  sc.add("toto");
  sc.add("tata");
  sc.add("atoto");

  System.out.println(sc.getFirst());
  System.out.println(sc.getLast());

  List<String> tries = sc.stream().sorted().toList();

  System.out.println(tries.getFirst());
  System.out.println(tries.getLast());
 }

 public static void testPatternMatching() {
  Object obj = Math.random() > 0.5 ? "Hello, Java 21!" : 42;
  if (obj instanceof String s) {
   System.out.println(STR."Pattern Matching pour instanceof : \{s.toUpperCase()}");
  } else {
   System.out.println("L'objet n'est pas une chaîne de caractères");
  }
 }

 public static void testRecordPatterns() {
  record Point(int x, int y) {}
  Object obj = Math.random() > 0.5 ? new Point(3, 4) : "Not a Point";
  if (obj instanceof Point(int x, int y)) {
   System.out.println(STR."Record Patterns : x = \{x}, y = \{y}");
  } else {
   System.out.println("L'objet n'est pas un Point");
  }
 }

 public static void testStringTemplates() {
  String name = "Java 21";
  int year = LocalDate.now().getYear();
  String template = STR."Hello, \{name}! Welcome to the year \{year}.";
  System.out.println(template);
 }

 public static void testScopedValues() {
  ScopedValue<String> scopedValue = ScopedValue.newInstance();
  ScopedValue.where(scopedValue, "Scoped Value").run(() -> {
   System.out.println(STR."Scoped Value: \{scopedValue.get()}");
  });
 }

 public static void testVirtualThreads() {
  try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
   executor.submit(() -> System.out.println("Hello from a virtual thread!"));
  }
 }
}