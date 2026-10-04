# SDLC Sample App

Application Spring Boot minimale qui sert de **repo consommateur** pour éprouver le [SDLC Blueprint](https://github.com/Palo-IT-Labs/sdlc-blueprint).

Elle ne contient que les fichiers propres à un repo d'équipe :
- `.github/workflows/sdlc.yml` : appelle le pipeline commun du blueprint ;
- `.sdlc.yml` : configuration (ici, la stack est détectée automatiquement) et déclarations ;
- `.github/CODEOWNERS` : relecteurs obligatoires ;
- `.github/pull_request_template.md` : checklist de relecture.

Les règles (protection de main, relecture, contrôle requis, secrets) viennent du blueprint.

## Lancer en local

Prérequis : JDK 21 ou plus récent.

```bash
./mvnw verify            # build et tests
./mvnw spring-boot:run   # démarre l'application
curl "http://localhost:8080/api/hello?name=Lamyaa"
curl http://localhost:8080/actuator/health
```

## Scénarios de test

| # | Scénario | Résultat attendu | Fiche |
|---|---|---|---|
| 1 | Premier push sur `main` | Le pipeline `SDLC` détecte `java-maven`, build et tests au vert | 04 |
| 2 | Pousser directement sur `main` (après application du socle) | Refusé : PR obligatoire | 02 |
| 3 | Force-push sur `main` | Refusé | 02 |
| 4 | PR sans approbation | Merge bloqué (« Review required ») | 03 |
| 5 | Approuver, puis pousser un nouveau commit | L'approbation est annulée | 03 |
| 6 | PR qui casse un test (`HelloControllerTests`) | Contrôle `sdlc / build-test` rouge, merge bloqué | 04 |
| 7 | Pousser un secret de test | Push refusé par la push protection | 05 |
| 8 | `scripts/check.sh Palo-IT-Labs/sdlc-sample-app` (depuis le blueprint) | Points L1 du socle à OK | Tous |
