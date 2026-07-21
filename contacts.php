<!doctype html>
<html lang="fr">

<head>
    <meta charset="utf-8">
    <title>Contact | Amicale Laïque de Bacalan</title>
    <meta name="keywords" content="association,bacalan,ateliers,amicale">
    <meta name="author" content="Raoul de Colombel">
    <meta name="description" content="Notre mission : Soutenir l'école publique et animer la vie locale.">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="public/styles.css" rel="stylesheet" type="text/css">
    <link rel="icon" type="image/png" href="images/logo-bacalan@0.5x.png">
    <script src="public/js/scripts.js" defer></script>
    <script src="https://cdn.tiny.cloud/1/md0fnvtpfjljywo92gi18b189qennzsh5y7jpr9q97an59a2/tinymce/8/tinymce.min.js" referrerpolicy="origin" crossorigin="anonymous"></script>

</head>

<body id="contact">

    <?php require_once 'components/header.php'; ?>

    <main id="main">
        <div class="title">
            <h1>Amicale Laïque de Bacalan</h1>
            <h2>Soutenir l'école publique et animer la vie locale<br>Expression de chacun et chacune, épanouissement
                pour tous</h2>
        </div>
        <div>
            <h3>Nous contacter</h3>
            <hr>
        </div>
        <div class="row">
            <form action="">
                <div class="champs">
                    <div class="champ">
                        <label for="name">Votre nom et/ou nom de votre structure | Obligatoire</label><br>
                        <input type="text" name="name" id="name" required>
                    </div>
                    <div class="champ">
                        <label for="email">Votre adresse mail | Obligatoire</label><br>
                        <input type="email" name="email" id="email" required>
                    </div>
                    <div class="champ">
                        <label for="message">Votre message | Obligatoire</label><br>
                        <textarea name="message" id="message" rows="10"></textarea>
                    </div>
                    <div class="coche">
                        <input type="checkbox" id="consent" required>
                        <label for="consent">
                            J'accepte que mes données soient utilisées pour répondre à ma demande.
                        </label>
                    </div>
                    <div class="submit">
                        <input type="submit" value="ENVOYER VOTRE MAIL" class="submit-btn">
                    </div>
                </div>
            </form>
        </div>
        <div>
            <h3>Informations utiles</h3>
            <hr>
        </div>
        <div class="cards">
            <iframe
                src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d5654.842477854241!2d-0.5487180238819056!3d44.87407587261905!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0xd5528e9b85ffa79%3A0x65801ba846a73165!2sAmicale%20La%C3%AFque%20de%20Bacalan!5e0!3m2!1sfr!2sfr!4v1754163046016!5m2!1sfr!2sfr"
                width="800" height="610" referrerpolicy="no-referrer-when-downgrade"></iframe>
        </div>
        <div class="cards">
            <div class="card-contact">
                <h4>Amicale des établissements scolaires laïques de Bacalan</h4>
                <p>SIRET : 78183781000022 – APE : 9499Z</p>
                <p>N° préfecture : W332005648</p>
                <p>Boite postale :</p>
                <p>2, Rue Duquesne</p>
                <p>33300 - BORDEAUX</p>
                <p>Entrée du public :</p>
                <p>5, Rue Joseph Brunet</p>
                <p>33300 - BORDEAUX</p>
            </div>
            <div class="card-contact">
                <h5>Réseaux sociaux</h5>
                <p><a href="https://www.facebook.com/amicalelaique.debacalan/">Page
                        Facebook <img src="/images/fbpicto.png" alt="fb-picto" width="28" height="28"></a></p>
                <p>https://www.facebook.com/amicalelaique.debacalan</p>
                <p><a href="https://www.instagram.com/amicalelaiquedebacalan/">Page Instagram <img
                            src="/images/insta-picto.svg" alt="insta-picto" width="24" height="24"></a></p>
                <p>https://www.instagram.com/amicalelaiquedebacalan/</p>
            </div>
        </div>
        <div class="cards">
            <div class="card-contact">
                <h5>Téléphones</h5>
                <p>Téléphone fixe :</p>
                <p>05 56 50 85 60</p>
                <p>Portable Education :</p>
                <p>07 81 82 74 87</p>
                <p>Portable Administratif :</p>
                <p>07 81 15 34 13</p>
            </div>
            <div class="card-contact">
                <h5>Adresses e-mails</h5>
                <p>e-mail direction :</p>
                <p>amicalebacalan@free.fr</p>
                <p>e-mail administratif :</p>
                <p>alb33.info@gmail.com ou</p>
                <p>secretariatamicale33@gmail.com</p>
            </div>
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

    <div class="modal" id="contact-modal">
        <div class="modal-content">
            <button class="modal-close" id="modal-close">
                <img src="images/x-circle.svg" alt="Bouton fermeture" width="32px" height="32px" id="close-btn">
            </button>
            <h2>Message envoyé</h2>
            <p>Merci pour votre message.</p>
            <p>Nous vous répondrons dès que possible.</p>
        </div>
    </div>

</body>

</html>