# Tech It Easy

A small app I built to learn Spring Boot and REST API development. It's a backend for a TV store - managing televisions and accessories like remotes, CI modules, and wall brackets.

## What's in here?

- REST API with full CRUD for TVs and accessories
- Spring Security with JWT authentication
- PostgreSQL database with JPA
- Input validation

## Tech stack

- Java 21
- Spring Boot 3.5.6
- PostgreSQL
- Spring Security + JWT

## How to run

1. Have PostgreSQL running on port 5432 (the default)
2. Create a database called `tech-it-easy-api`
3. Set the `POSTGRESQL_PASSWORD` environment variable
4. Run:
   ```bash
   ./mvnw spring-boot:run
   ```

## Assignment

If you're curious about the original assignment, check out [ASSIGNMENT-INSTRUCTIONS.md](ASSIGNMENT-INSTRUCTIONS.md) (it's in Dutch though!).
