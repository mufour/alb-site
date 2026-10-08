# ALB — PostgreSQL local

## Prerequisites
Docker Desktop running, Java 21, Maven, DBeaver. Run commands from `fr.alb.backend/`.

## Start database
```bash
docker compose up -d
docker compose ps
```

Docker downloads the `postgres:16-alpine` image on first run, creates the `alb-postgres` container and persistent named volume. PostgreSQL is exposed at localhost:5433 (container port 5432).

## Start API
```bash
mvn spring-boot:run -Dspring-boot.run.profiles=postgres
```

The `postgres` profile enables PostgreSQL, Flyway and Hibernate schema validation. The default profile remains H2 for existing development/tests. Flyway executes `V1__create_alb_schema.sql` on an empty database.

## DBeaver connection
- Type: PostgreSQL
- Host: localhost
- Port: 5433
- Database: alb_db
- Username: alb_user
- Password: alb_dev_password (local development default only)
- Test Connection, then Finish. Expand Schemas > public > Tables.

## Verify persistence
Use Bruno to POST a news item to `http://localhost:8080/api/news`. In DBeaver, run:
```sql
SELECT id, title, published, created_at FROM news ORDER BY id DESC;
SELECT id, file_name, file_path FROM media ORDER BY id DESC;
SELECT version, description, success FROM flyway_schema_history ORDER BY installed_rank;
```
Restart the Spring Boot app; the inserted rows remain in PostgreSQL. Flyway migration metadata is also persisted.

## Stop / reset
`docker compose down` stops the database and **keeps** the named volume and its data.
`docker compose down -v` **irreversibly deletes local database data**.

## Configuration and security
`ALB_DB_PASSWORD`, `ALB_DB_URL` and `ALB_DB_USER` can override local defaults. Do not use the example password in production or commit real secrets. If you change POSTGRES_PASSWORD after the volume was initialized, the existing database user's password does not automatically change. The `uploads/` directory stores uploaded files separately from PostgreSQL: back up both for complete restoration.

## Scope
The deferred TeamMember ↔ Media relation is intentionally **not** added to this migration. Add it later in a separate Flyway migration. Schema validation and an end-to-end run on the user's Windows Docker environment are still required before merging.


## Manual verification report — 2026-10-08

Executed by the project developer on Windows:
- Docker PostgreSQL container started successfully and accepted connections.
- DBeaver connected to `localhost:5433/alb_db`.
- Spring Boot started successfully with the `postgres` profile; Flyway migration V1 executed.
- All seven domain tables and `flyway_schema_history` were visible in DBeaver.
- News POST and GET succeeded, with the inserted row visible in DBeaver.
- After stopping and restarting Spring Boot, the same news row remained in DBeaver and was accessible from Bruno.
- News PUT and DELETE succeeded; changes were verified in Bruno and DBeaver.

- Automated Maven verification: `mvn clean verify` completed with `BUILD SUCCESS` (reported by the developer on Windows).

Still pending: review of local Git working tree and final pull-request review.
