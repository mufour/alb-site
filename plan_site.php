<!doctype html>
<html lang="fr">

<head>
    <meta charset="utf-8">
    <title>Plan du site | Amicale Laïque de Bacalan</title>
    <meta name="keywords" content="association,bacalan,ateliers,amicale">
    <meta name="author" content="Raoul de Colombel">
    <meta name="description" content="Notre mission : Soutenir l'école publique et animer la vie locale.">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="public/styles.css" rel="stylesheet" type="text/css">
    <link rel="icon" type="image/png" href="images/logo-bacalan@0.5x.png">
    <script src="public/js/scripts.js" defer></script>
</head>

<body id="accueil">

    <?php require_once 'components/header.php'; ?>

    <main id="main">
        <div class="title">
            <h1>Amicale Laïque de Bacalan</h1>
            <h2>Soutenir l'école publique et animer la vie locale<br>Expression de chacun et chacune, épanouissement
                pour tous</h2>
        </div>
        <div>
            <h3>Plan du site</h3>
            <hr>
        </div>
        <div class="row">
            <div class="w-11-content">
                <img src="/images/plan_de_site_arbo.webp" alt="plan-du-site">
            </div>
        </div>
        <br>
        <div class="cards">
            <div class="card">
                <br><img src="images/picto_atelier.png" width="100" height="100" alt="picto_atelier">
                <p><a class="bouton-picto" href="http://www.amicalebacalan.com/ateliers.html">ATELIERS</a>
                </p>
                <p>Culturels<br>Sportifs<br>Musicaux</p>
            </div>
            <div class="card">
                <br><img src="images/chaise-et-table.png" width="100" height="100"
                    alt="picto_atelier_scolaire">
                <p><a class="bouton-picto"
                        href="https://www.amicalebacalan.com/etablissements.html">ACTIONS
                        SCOLAIRES
                    </a></p>
                <p>Collège Blanqui<br>Ecoles</p>
            </div>
            <div class="card">
                <br><img src="images/fichier-et-dossier.png" width="100" height="100" alt="pico_aide_admin">
                <p><a class="bouton-picto" href="https://www.amicalebacalan.com/aide_administrative.html">AIDE
                        ADMINISTRATIVE</a></p>
                <p>Sur rendez-vous<br>Sans rendez-vous</p>
            </div>
        </div>
    </main>

    <?php require_once 'components/footer.php'; ?>

</body>

</html>