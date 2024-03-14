# Lottery API

Lottery API is the Java Springboot Backend API which is the part of KBTG ClassNest Java Software Engineering Bootcamp 2.
I'm proud to be invited to Special Workshop from KBTG too. Although this is my final project works.
I still have a lot more to improve such as the use of Mockito and MockMvc.

For all API Endpoints, you can see the origin repository where I forked from.
It's very-detailed projects so I implement this project from user stories.


## Installation

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
