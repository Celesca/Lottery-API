# Start Project

`cd posttest` 

`docker-compose -f .\docker-compose.yml up`

The docker compose will starting the database and the server immediately.

## Unit Testing including
   - AdminControllerTest
   - UserControllerTest

`./gradlew test`

## Linter (Spotless)

`./gradlew spotlessCheck`

`./gradlew spotlessApply`
