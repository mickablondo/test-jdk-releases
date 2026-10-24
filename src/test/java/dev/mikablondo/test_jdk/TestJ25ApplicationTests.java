package dev.mikablondo.test_jdk;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;

import org.junit.jupiter.api.Test;

class TestJ25ApplicationTests {

  @Test
  void primitivePatternsHandleZeroPositiveAndNegativeValues() {
    assertTrue(captureOutput(() -> Java25Features.testPrimitivePatterns(0))
        .contains("Primitive type patterns: zero"));
    assertTrue(captureOutput(() -> Java25Features.testPrimitivePatterns(25))
        .contains("Primitive type patterns: positive: 25"));
    assertTrue(captureOutput(() -> Java25Features.testPrimitivePatterns(-25))
        .contains("Primitive type patterns: negative: -25"));
  }

  @Test
  void scopedValuesAreAvailableWithinTheirScope() {
    assertTrue(captureOutput(Java25Features::testScopedValues).contains("Scoped value: request-25"));
  }

  @Test
  void flexibleConstructorBodyCreatesPositiveCounter() {
    assertTrue(captureOutput(Java25Features::testFlexibleConstructorBodies)
        .contains("Flexible constructor body: 25"));
  }

  @Test
  void keyDerivationFunctionDerivesAnAesKey() {
    assertTrue(captureOutput(Java25Features::testKeyDerivationFunction)
        .contains("KDF API: dérive une clé AES (32 bytes)"));
  }

  private String captureOutput(ThrowingRunnable action) {
    PrintStream originalOut = System.out;
    ByteArrayOutputStream output = new ByteArrayOutputStream();
    try (PrintStream capturedOut = new PrintStream(output, true, StandardCharsets.UTF_8)) {
      System.setOut(capturedOut);
      action.run();
    } catch (GeneralSecurityException exception) {
      throw new AssertionError("Feature demonstration failed", exception);
    } finally {
      System.setOut(originalOut);
    }
    return output.toString(StandardCharsets.UTF_8).strip();
  }

  @FunctionalInterface
  private interface ThrowingRunnable {
    void run() throws GeneralSecurityException;
  }
}
