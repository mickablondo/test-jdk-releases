package dev.mikablondo.test_j21;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;
import java.util.SequencedCollection;

@SpringBootApplication
public class TestJ21Application {

	public static void main(String[] args) {
		SpringApplication.run(TestJ21Application.class, args);

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

}
