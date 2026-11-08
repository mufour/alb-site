# Administration ALB — connexion de navigateur (lot 7)

## Ce qui change

Ce lot remplace l'authentification HTTP Basic de l'API par une authentification par **session côté serveur**.

- `GET /api/auth/csrf` : public, retourne `headerName` et `token`, crée une session si nécessaire.
- `POST /api/auth/login` : corps `application/x-www-form-urlencoded` avec `username` et `password` et en-tête CSRF. Renvoie 200 et `{"authenticated":true}` ou 401.
- `GET /api/auth/me` : utilisateur connecté (200 avec nom de connexion, 401 sinon).
- `POST /api/auth/logout` : avec session et jeton CSRF, invalide la session, renvoie 204.
- `GET /api/public/**` : toujours accessible sans connexion.
- `/api/**` hors endpoints publics : rôle ADMIN requis.
- POST/PUT/PATCH/DELETE : exigent la session ADMIN **et** le jeton CSRF. Sans jeton CSRF, un 403 est normal.

**HTTP Basic est désactivé dans ce lot.** Les anciennes requêtes Bruno avec Basic Auth devront être adaptées. Aucun mot de passe ne doit être commité.

## Parcours manuel dans Bruno

Toutes les requêtes utilisent le même serveur `http://localhost:8080` et **le même cookie `JSESSIONID`** (activer la persistance des cookies de Bruno ou propager le cookie reçu).

1. GET `/api/auth/csrf` sans authentification. Conserver le champ `token` et le cookie `JSESSIONID`.
2. POST `/api/auth/login`, `Content-Type: application/x-www-form-urlencoded`, champs `username` et `password`. Ajouter l'en-tête `X-CSRF-TOKEN` avec le jeton de l'étape 1, et transmettre le cookie de session. Attendu : **200**.
3. GET `/api/auth/me` avec le cookie : **200** et `username`.
4. GET `/api/auth/csrf` **après connexion** avec le cookie : relever le **nouveau** jeton.
5. GET `/api/admin/news/page?page=0&size=10` avec cookie : **200**.
6. POST `/api/admin/news` sans jeton CSRF mais avec le cookie : **403**.
7. POST `/api/admin/news` avec cookie, nouveau `X-CSRF-TOKEN` et JSON `{}` : **400** (validation du corps).
8. POST `/api/auth/logout` avec cookie et jeton CSRF : **204**. La session devient invalide.
9. GET `/api/auth/me` sans session valide : **401**.

Le jeton CSRF doit être **redemandé après chaque login ou logout** ; Spring Security réinitialise le jeton lors de ces événements. Le cookie de session est `HttpOnly` : le navigateur le transmet automatiquement, le JavaScript ne doit pas le lire.

Le endpoint `/api/auth/login` attend actuellement des données de formulaire, **pas un corps JSON**. Pour une future interface JavaScript, utiliser `URLSearchParams` avec `credentials: 'same-origin'` et l'en-tête CSRF.

## Configuration du serveur

La variable `ALB_ADMIN_PASSWORD` reste le mot de passe ADMIN. `ALB_ADMIN_USERNAME` vaut `admin` par défaut.

Le projet applique une expiration de session de 30 minutes, un cookie `HttpOnly` et `SameSite=Lax`.

- Développement local HTTP : `ALB_SESSION_COOKIE_SECURE=false`.
- Production exclusivement sur HTTPS : `ALB_SESSION_COOKIE_SECURE=true` (obligatoire).
- Déployer de préférence le site public, le back-office et l'API sous **un même domaine / origine** afin d'éviter les problèmes de CORS et de cookies.
- Activer HTTPS avec redirection depuis HTTP, sauvegarder les données, et empêcher l'exposition des secrets.
- Le compte administrateur demeure **en mémoire**, chargé des variables au démarrage ; c'est un premier socle, pas une gestion multi-utilisateurs. Renforcer la politique d'authentification (limitation des tentatives, comptes persistants, audit) avant une production exposée.
- La configuration actuelle H2, `ddl-auto=update`, et la console H2 doivent être remplacées/désactivées dans le profil de production.

## Recette

Dans `fr.alb.backend` : `mvn clean verify`, puis redémarrer Spring Boot et tester les étapes ci-dessus.

**Ne pas fusionner tant que les tests automatisés et le parcours Bruno de session ne sont pas validés.**
