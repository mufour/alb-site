# Actualités : API publique et administration

## Accès public (sans authentification)
- `GET /api/public/news` : actualités publiées uniquement.

## Administration (HTTP Basic, compte ADMIN)
- `GET /api/admin/news` : toutes les actualités, y compris les brouillons.
- `GET /api/admin/news/{id}` : consulter une actualité, même non publiée.
- `POST /api/admin/news` : créer une actualité ; `published` vaut `false` par défaut.
- `PUT /api/admin/news/{id}` : modifier une actualité.
- `PATCH /api/admin/news/{id}/publish` : publier une actualité.
- `PATCH /api/admin/news/{id}/unpublish` : dépublier une actualité.
- `DELETE /api/admin/news/{id}` : supprimer une actualité.

Les anciens chemins `/api/news` sont conservés comme alias **administrateur uniquement** pour faciliter la transition. Les anciens appels GET anonymes vers `/api/news` reçoivent désormais 401 : migrer le site public vers `/api/public/news`.

Par sécurité, les autres chemins `/api/**` non préfixés par `/api/public/` sont également réservés aux administrateurs, y compris les requêtes GET. Les consommateurs de l'API doivent être adaptés en conséquence.

## Vérifications manuelles (Bruno)
1. Sans authentification, `GET /api/public/news` → 200.
2. Sans authentification, `GET /api/news` et `GET /api/admin/news` → 401.
3. Avec HTTP Basic valide, `GET /api/admin/news` → 200, brouillons inclus.
4. Créer une actualité avec `published:false` ; elle est absente de `/api/public/news`.
5. `PATCH /api/admin/news/{id}/publish` avec authentification → actualité visible publiquement.
6. `PATCH /api/admin/news/{id}/unpublish` avec authentification → actualité de nouveau masquée.
7. Sans authentification, les requêtes PATCH → 401.

Les tests utilisent un compte fictif. Ne jamais versionner `.env` ni de mot de passe réel.

> HTTP Basic sans protection CSRF est transitoire pour le développement local. Ne pas exposer tel quel comme back-office de production : prévoir authentification par session, HTTPS et CSRF.
