<!doctype html>
<html lang="fr">

<head>
    <meta charset="utf-8">
    <title>Au quotidien | Amicale Laïque de Bacalan</title>
    <meta name="keywords" content="association,bacalan,ateliers,amicale">
    <meta name="author" content="Raoul de Colombel">
    <meta name="description" content="Notre mission : Soutenir l'école publique et animer la vie locale.">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="public/styles.css" rel="stylesheet" type="text/css">
    <link rel="icon" type="image/png" href="images/logo-bacalan@0.5x.png">
    <script src="public/js/scripts.js" defer></script>
</head>

<body id="amicale">

    <?php require_once 'components/header.php'; ?>
    <?php require_once 'components/submenu-amicale.php'; ?>

    <main id="main">
        <div class="title">
            <h1>Amicale Laïque de Bacalan</h1>
            <h2>Soutenir l'école publique et animer la vie locale<br>Expression de chacun et chacune, épanouissement
                pour tous</h2>
        </div>
        <div>
            <h3>Au quotidien</h3>
            <hr>
        </div>
        <div class="text">
            <p>
                L'amicale anime un lieu de proximité ouvert à toutes et tous, fondé sur les valeurs de
                solidarité et de partage.
            <p>Elle met à disposition des espaces et des services favorisant
                l’entraide et l’autonomie des habitants : coin jeux pour les enfants, espace livres, accès à
                l’informatique, à l’impression, ainsi qu’un espace d’échange gratuit où chacun peut déposer ou trouver
                vêtements propres, livres, jouets ou objets du quotidien.</p>
            <p>Un point d’information sur la vie du quartier
                complète ces actions dans l'optique de renforcer le lien social et la participation active des habitants
                à la vie locale.</p>
        </div>
        <div class="w-12-content">
            <h4>Coin enfants</h4>
            <img src="images/tapis_jeux_eveil.webp" alt="coin_jeux">
        </div>
        <div class="cards">
            <div class="card">
                <br><img src="images/picto_atelier.png" width="100" height="100" alt="picto_atelier">
                <p><a class="bouton-picto" href="/ateliers">ATELIERS</a>
                </p>
                <p>Culturels<br>Sportifs<br>Musicaux</p>
            </div>
            <div class="card">
                <br><img src="images/chaise-et-table.png" width="100" height="100" alt="picto_atelier_scolaire">
                <p><a class="bouton-picto" href="/etablissements">ACTIONS
                        SCOLAIRES
                    </a></p>
                <p>Collège Blanqui<br>Ecoles</p>
            </div>
            <div class="card">
                <br><img src="images/fichier-et-dossier.png" width="100" height="100" alt="pico_aide_admin">
                <p><a class="bouton-picto" href="/aide_administrative">AIDE
                        ADMINISTRATIVE</a></p>
                <p>Sur rendez-vous<br>Sans rendez-vous</p>
            </div>
        </div>
    </main>

    <?php require_once 'components/footer.php'; ?>

</body>

</html>