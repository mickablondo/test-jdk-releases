# Java, version après version

Un petit projet pour explorer les nouveautés du JDK avec des exemples concrets.
Chaque branche correspond à une version ou à un axe de développement.

## Technologies

[![Java 25](https://img.shields.io/badge/Java-25-orange?logo=openjdk&logoColor=white)](https://www.oracle.com/java/technologies/downloads/)
[![Spring Boot 4.1.2](https://img.shields.io/badge/Spring%20Boot-4.1.2-6DB33F?logo=springboot&logoColor=white)](https://spring.io/projects/spring-boot)
[![Maven](https://img.shields.io/badge/Maven-build-C71A36?logo=apachemaven&logoColor=white)](https://maven.apache.org/)
[![Lombok 1.18.48](https://img.shields.io/badge/Lombok-1.18.48-red?logo=lombok&logoColor=white)](https://projectlombok.org/)
[![JUnit 5](https://img.shields.io/badge/JUnit-5-25A162?logo=junit5&logoColor=white)](https://junit.org/junit5/)

## JDK 25 — branche `master`

La branche principale met en avant les nouveautés de Java 25 :

- **Motifs sur les types primitifs** dans `switch` — JEP 507, fonctionnalité preview.
- **Scoped Values** — JEP 506.
- **Corps de constructeurs flexibles** — JEP 513.
- **API de dérivation de clés (KDF)** — JEP 510.

Les fonctionnalités preview nécessitent un JDK 25 et l’option `--enable-preview`.
Consultez les [notes de version du JDK 25](https://www.oracle.com/java/technologies/javase/25-relnote-issues.html).

## Compiler, tester et lancer

Prérequis : un **JDK 25** installé et configuré dans `JAVA_HOME`. Vérifiez la version utilisée par Maven avec :

```shell
mvn -version
```

La sortie doit indiquer Java 25. Les fonctionnalités preview nécessitent `--enable-preview`, déjà configuré dans le POM pour la compilation, les tests et le lancement.

```shell
# Compiler et exécuter les tests
mvn test

# Construire le JAR
mvn package

# Lancer l'application
mvn spring-boot:run
```

## Autres branches

| État        | Branche                                                                        | Contenu                                                                                                                                                                                                                       |
|-------------|--------------------------------------------------------------------------------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| ✅ Fait     | [`jdk21`](https://github.com/mickablondo/test-jdk-releases/tree/jdk21)         | Exemples des nouveautés du JDK 21. Voir les [notes de version](https://www.oracle.com/java/technologies/javase/21-relnote-issues.html).                                                                                       |
| 🔜 À venir  | [`jdk27`](https://github.com/mickablondo/test-jdk-releases/tree/jdk27)         | Branche réservée au développement à venir autour du JDK 27.                                                                                                                                                                   |
| 🚧 En cours | [`multi-jdk`](https://github.com/mickablondo/test-jdk-releases/tree/multi-jdk) | Étude d’une architecture Maven multi-modules pour tester plusieurs versions du JDK dans un même projet. Chaque module isole les fonctionnalités propres à une version (Java 21, 24, etc.) tout en partageant un socle commun. |

Cette architecture est encore en cours d’exploration.
