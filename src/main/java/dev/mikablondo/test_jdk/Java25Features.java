package dev.mikablondo.test_jdk;

import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.security.SecureRandom;
import javax.crypto.KDF;
import javax.crypto.SecretKey;
import javax.crypto.spec.HKDFParameterSpec;
import lombok.experimental.UtilityClass;
import lombok.extern.log4j.Log4j2;

/**
 * Classe regroupant les tests des différentes features principales de Java 25.
 * <a href="https://openjdk.org/projects/jdk/25/">JDK25</a>
 *
 * @author micka blondo
 */
@UtilityClass
@Log4j2
public class Java25Features {

  // Création d'une ScopedValue pour stocker un identifiant de requête.
  private static final ScopedValue<String> REQUEST_ID = ScopedValue.newInstance();

  /**
   * Montre les motifs de types primitifs dans une expression {@code switch},
   * en distinguant zéro, les nombres positifs et les nombres négatifs.
   *
   * <p>Nouveauté de Java 25 — JEP 507, troisième preview : les types primitifs
   * peuvent être utilisés comme motifs dans {@code switch} et {@code instanceof}.
   * Cette fonctionnalité est en preview et nécessite {@code --enable-preview}.
   *
   * @param value la valeur entière à tester
   */
  public static void testPrimitivePatterns(int value) {
    String description = switch (value) {
      case 0 -> "zero";
      case int number when number > 0 -> "positive: " + number;
      case int number -> "negative: " + number;
    };

    log.info("Primitive type patterns: {}", description);
  }

  /**
   * Associe temporairement un identifiant de requête à une portée, puis le lit
   * depuis le code exécuté dans cette portée.
   *
   * <p>Nouveauté de Java 25 — JEP 506 : les Scoped Values, auparavant en preview,
   * deviennent une fonctionnalité définitive. Elles permettent de transmettre
   * des données immuables aux méthodes appelées sans les passer explicitement
   * en paramètre ni utiliser un {@link ThreadLocal}.
   */
  public static void testScopedValues() {
    ScopedValue.where(REQUEST_ID, "request-25").run(() -> log.info("Scoped value: {}", REQUEST_ID.get()));
  }

  /**
   * Valide la valeur avant d'appeler le constructeur de la classe parente,
   * puis crée un compteur positif.
   *
   * <p>Nouveauté de Java 25 — JEP 513 : un constructeur peut exécuter des
   * instructions avant l'appel explicite à {@code super(...)} ou {@code this(...)}.
   * Ces instructions ne peuvent pas accéder à l'instance en cours de construction.
   */
  public static void testFlexibleConstructorBodies() {
    PositiveCounter counter = new PositiveCounter(25);
    log.info("Flexible constructor body: {}", counter.value());
  }

  /**
   * Dérive une clé AES de 256 bits avec HKDF-SHA256 à partir d'un secret initial,
   * d'un sel et d'une information de contexte.
   *
   * <p>Nouveauté de Java 25 — JEP 510 : l'API de dérivation de clés (KDF),
   * introduite en preview dans Java 24, devient définitive. Elle fournit ici
   * l'algorithme HKDF-SHA256.
   *
   * @throws GeneralSecurityException si l'algorithme ou la dérivation échoue
   */
  public static void testKeyDerivationFunction() throws GeneralSecurityException {
    byte[] initialKeyMaterial = new byte[32];
    byte[] salt = new byte[16];
    SecureRandom random = new SecureRandom();
    random.nextBytes(initialKeyMaterial);
    random.nextBytes(salt);
    byte[] info = "jdk25-demo".getBytes(StandardCharsets.UTF_8);

    KDF hkdf = KDF.getInstance("HKDF-SHA256");
    HKDFParameterSpec parameters = HKDFParameterSpec.ofExtract()
        .addIKM(initialKeyMaterial)
        .addSalt(salt)
        .thenExpand(info, 32);
    SecretKey key = hkdf.deriveKey("AES", parameters);

    log.info("KDF API: dérive une clé {} ({} bytes)", key.getAlgorithm(), key.getEncoded().length);
  }

  //region PRIVATE METHODS
  /**
   * Représente un compteur.
   */
  private static class Counter {
    private final int value;

    Counter(int value) {
      this.value = value;
    }

    int value() {
      return value;
    }
  }

  /**
   * Représente un compteur positif, qui ne peut être créé qu'avec une valeur positive.
   */
  private static final class PositiveCounter extends Counter {
    PositiveCounter(int value) {
      if (value <= 0) {
        throw new IllegalArgumentException("value must be positive");
      }
      super(value);
    }
  }
  //endregion
}
