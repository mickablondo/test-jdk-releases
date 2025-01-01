package dev.mikablondo.test_j21;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.SequencedCollection;
import java.util.concurrent.Executors;

@SpringBootApplication
public class TestJ21Application {

	public static void main(String[] args) {
		SpringApplication.run(TestJ21Application.class, args);

		testSequencedCollection();
		testPatternMatching();
		testRecordPatterns();
		testStringTemplates();
		testScopedValues();
		testVirtualThreads();
	}

	/**
	 * Teste la nouvelle interface SequencedCollection introduite dans Java 21.
	 * Cette interface fournit des méthodes pour accéder directement aux
	 * premiers et derniers éléments d'une collection.
	 */
	private static void testSequencedCollection() {
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

	/**
	 * Teste le pattern matching amélioré pour instanceof introduit dans Java 21.
	 * Cette fonctionnalité permet des vérifications de type et des castings
	 * plus concis et lisibles.
	 */
	private static void testPatternMatching() {
		Object obj = Math.random() > 0.5 ? "Hello, Java 21!" : 42;
		if (obj instanceof String s) {
			System.out.println("Pattern Matching pour instanceof : " + s.toUpperCase());
		} else {
			System.out.println("L'objet n'est pas une chaîne de caractères");
		}
	}

	/**
	 * Teste la nouvelle fonctionnalité des record patterns introduite dans Java 21.
	 * Cette fonctionnalité permet de déconstruire les types record directement
	 * dans le pattern matching.
	 */
	private static void testRecordPatterns() {
		record Point(int x, int y) {}
		Object obj = Math.random() > 0.5 ? new Point(3, 4) : "Not a Point";
		if (obj instanceof Point(int x, int y)) {
			System.out.println("Record Patterns : x = " + x + ", y = " + y);
		} else {
			System.out.println("L'objet n'est pas un Point");
		}
	}

	/**
	 * Teste les templates de chaînes introduits dans Java 21.
	 * Cette fonctionnalité permet une interpolation de chaînes plus facile et plus sûre.
	 */
	private static void testStringTemplates() {
		String name = "Java 21";
		int year = LocalDate.now().getYear();
		String template = STR."Hello, \{name}! Welcome to the year \{year}.";
		System.out.println(template);
	}

	/**
	 * Teste les valeurs à portée introduites dans Java 21.
	 * Cette fonctionnalité permet une gestion plus sûre des variables globales.
	 */
	private static void testScopedValues() {
		ScopedValue<String> scopedValue = ScopedValue.newInstance();
		ScopedValue.where(scopedValue, "Scoped Value").run(() -> {
			System.out.println(STR."Scoped Value: \{scopedValue.get()}");
		});
	}

	/**
	 * Teste les threads virtuels introduits dans Java 21.
	 * Cette fonctionnalité permet une gestion plus efficace des threads.
	 */
	private static void testVirtualThreads() {
		try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
			executor.submit(() -> System.out.println("Hello from a virtual thread!"));
		}
	}
}
