# Java, version après version

Un petit projet pour explorer les nouveautés du JDK avec des exemples concrets.
Chaque branche correspond à une version ou à un axe de développement.

## JDK 25 — branche `master`

La branche principale met en avant les nouveautés de Java 25 :

- **Motifs sur les types primitifs** dans `switch` — JEP 507, fonctionnalité preview.
- **Scoped Values** — JEP 506.
- **Corps de constructeurs flexibles** — JEP 513.
- **API de dérivation de clés (KDF)** — JEP 510.

Les fonctionnalités preview nécessitent un JDK 25 et l’option `--enable-preview`.
Consultez les [notes de version du JDK 25](https://www.oracle.com/java/technologies/javase/25-relnote-issues.html).

## Autres branches

| État        | Branche                                                                        | Contenu                                                                                                                                                                                                                       |
|-------------|--------------------------------------------------------------------------------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| ✅ Fait     | [`jdk21`](https://github.com/mickablondo/test-jdk-releases/tree/jdk21)         | Exemples des nouveautés du JDK 21. Voir les [notes de version](https://www.oracle.com/java/technologies/javase/21-relnote-issues.html).                                                                                       |
| 🔜 À venir  | [`jdk27`](https://github.com/mickablondo/test-jdk-releases/tree/jdk27)         | Branche réservée au développement à venir autour du JDK 27.                                                                                                                                                                   |
| 🚧 En cours | [`multi-jdk`](https://github.com/mickablondo/test-jdk-releases/tree/multi-jdk) | Étude d’une architecture Maven multi-modules pour tester plusieurs versions du JDK dans un même projet. Chaque module isole les fonctionnalités propres à une version (Java 21, 24, etc.) tout en partageant un socle commun. |

Cette architecture est encore en cours d’exploration.
