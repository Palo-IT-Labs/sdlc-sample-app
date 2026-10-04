# SDLC Sample App

Application Spring Boot minimale qui sert de **repo consommateur** pour éprouver le [SDLC Blueprint](https://github.com/Palo-IT-Labs/sdlc-blueprint).

Elle ne contient que les fichiers propres à un repo d'équipe :
- `.github/workflows/ci.yml` : appelle le workflow partagé du blueprint ;
- `.github/CODEOWNERS` : relecteurs obligatoires ;
- `.github/pull_request_template.md` : checklist de relecture.

Les règles (protection de main, relecture, contrôles requis, secrets) sont appliquées par le blueprint.

## Lancer en local

Prérequis : JDK 21 ou plus récent.

```bash
./mvnw verify            # build et tests
./mvnw spring-boot:run   # démarre l'application
curl "http://localhost:8080/api/hello?name=Lamyaa"
curl http://localhost:8080/actuator/health
```

## Scénarios de test du socle

| # | Scénario | Résultat attendu | Fiche |
|---|---|---|---|
| 1 | Pousser directement sur `main` | Refusé : passage par une PR obligatoire | 02 |
| 2 | Force-push sur `main` | Refusé | 02 |
| 3 | Ouvrir une PR sans approbation | Merge bloqué (« Review required ») | 03 |
| 4 | Approuver, puis pousser un nouveau commit | L'approbation est annulée | 03 |
| 5 | PR qui casse un test (`HelloControllerTests`) | Contrôle `ci / build-test` rouge, merge bloqué | 04 |
| 6 | Pousser un secret de test | Push refusé par la push protection | 05 |
| 7 | `scripts/check-socle.sh Palo-IT-Labs/sdlc-sample-app` | « Résultat : socle conforme. » | Tous |
