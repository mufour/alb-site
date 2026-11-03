# ALB — lot 6 : socle de livraison actualités

## Périmètre livré dans cette PR
- `GET /api/admin/news/page?page=0&size=20&sort=createdAt,desc` : liste paginée réservée à ADMIN, avec métadonnées Spring Page. Taille maximale : 100.
- `GET /api/admin/news` : liste historique conservée pour compatibilité (non paginée).
- `imageId` : validation `@Positive` pour les créations et modifications d'actualités.
- Test unitaire de pagination.

## Vérifications locales obligatoires
1. `mvn clean verify` dans `fr.alb.backend`.
2. Avec Bruno : GET paginé sans Basic Auth -> 401 ; avec Basic Auth -> 200, vérifier `content`, `totalElements`, `totalPages`.
3. GET paginé avec `size=101` -> 400 ; `size=10` -> 200.
4. POST /api/admin/news avec `imageId: -1` et authentification -> 400.
5. GET /api/public/news sans authentification -> 200 ; brouillons absents.

## Priorités des prochains lots avant production
1. Authentification navigateur par session, cookies Secure/HttpOnly/SameSite, protection CSRF et déconnexion ; pas de Basic Auth en production.
2. API médias : upload contrôlé (type réel, taille, nom, autorisations), stockage persistant, références et suppression sûre.
3. Interface d'administration : connexion, liste paginée, formulaires, publication, médias.
4. Exploitation : configuration prod, HTTPS, Flyway, sauvegarde/restauration PostgreSQL, CI et recette.

**Cette PR ne rend pas le projet prêt pour la production.** Ne pas fusionner sans validation utilisateur.
