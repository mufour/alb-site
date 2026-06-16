/* ---------------- Animation du bandeau d'images de la page d'accueil ---------------- */
const bandef = document.querySelector('.bandef');
let speed = 1;
let position = 0;
let isPaused = false;
let direction = -1;

/* Dupliquer les images */
const track = document.querySelector('.bandef-track');

if (track) {
    track.innerHTML += track.innerHTML;
    function animate() {
        if (!isPaused) {
            position += speed * direction;
            if (Math.abs(position) >= track.scrollWidth / 2) {
                position = 0;
            }
            track.style.transform = `translateX(${position}px)`;
        }
        requestAnimationFrame(animate);
    }
    animate();

    /* Arrêt défilement hors écran*/
    const observer = new IntersectionObserver((entries) => {
        entries.forEach(entry => {
            if (entry.isIntersecting) {
                isPaused = false;
            } else {
                isPaused = true;
            }
        });

    }, {
        threshold: 0.1
    });
    observer.observe(bandef);

    /* Boutons */
    const toggleBtn = document.getElementById('toggle-move');
    toggleBtn.addEventListener('click', () => {
        isPaused = !isPaused;
        toggleBtn.textContent = isPaused ? 'Reprendre' : 'Pause';
    });
    document.getElementById('speed-up')
        .addEventListener('click', () => {
            speed += 1;
        });
    document.getElementById('speed-down')
        .addEventListener('click', () => {

            if (speed > 1) {
                speed -= 1;
            }

        });
}

/* ---------------- Menu Burger ---------------- */
const burgerBtn = document.getElementById('burger-btn');
const mainNavLinks = document.getElementById('nav-links');
const submenu = document.querySelector('.submenu');

if (burgerBtn && mainNavLinks) {
    function toggleMenus() {
        mainNavLinks.classList.toggle('open');
        if (submenu) {
            submenu.classList.toggle('open');
        }
    }
    // Écouteur d'événement pour le bouton burger
    burgerBtn.addEventListener('click', toggleMenus);
    // Fermer les menus en cliquant sur un lien
    const mainNavItems = mainNavLinks.querySelectorAll('a');
    mainNavItems.forEach(link => {
        link.addEventListener('click', toggleMenus);
    });
    const submenuItems = submenu.querySelectorAll('a');
    submenuItems.forEach(link => {
        link.addEventListener('click', toggleMenus);
    });
}

/* ---------------- Modal aide administrative ---------------- */
const demarches = {
    ameli: [
        "Créer un compte ameli",
        "Faire une demande de carte vitale",
        "Consulter ses remboursements"
    ],
    caf: [
        "Faire une déclaration trimestrielle",
        "Ecrire un mail concernant son dossier",
        "Faire une demande de prime d'activité",
        "Déclarer sa situation"
    ],
    msa: [
        "Créer son compte MSA",
        "Consulter ses droits",
        "Imprimer une attestation"
    ],
    urssaf: [
        "Déclarer et payer",
        "Créer son autoentreprise"
    ],
    francetravail: [
        "S'actualiser",
        "Transmettre ses documents",
        "Ecrire un message à son conseiller"
    ],
    carsat: [
        "Transmettre ses documents",
        "Faire une demande de retraite"
    ],
    mdph: [
        "Ordonner ses documents",
        "Faire un renouvellement de droit"
    ],
    prefecture: [
        "Ecrire un mail",
        ""
    ],
    tbm: [
        "Demander ue tarification solidaire",
        "Contacter un conseiller téléphonique",
        "Ajouter un ayant droit"
    ]
}
const logos = document.querySelectorAll('#logos img');
const modalHelp = document.getElementById('aide-modal');

if (demarches && logos && modalHelp) {
    logos.forEach(img => {
        img.addEventListener('click', () =>
            modalHelp.classList.add('open')
        )
    })
    modalHelp.addEventListener('click', (e) => {
        if (e.target === modalHelp) {
            modalHelp.classList.remove('open');
        }
    });
};

/* ---------------- Filtre recherche ---------------- */

const card = document.querySelectorAll('.card-partner');
const filtre = document.getElementById('partner-filter')

filtre.addEventListener('input', () => {
    const recherche = filtre.value.toLowerCase();
    card.forEach(card => {
        const nom = card.querySelector('h4').textContent.toLowerCase();
        const resultat = nom.includes(recherche);
        card.style.display = resultat ? "" : "none";
    })
})

/* ---------------- Formulaire ---------------- */

/* ---------------- Message au clic du champ e-mail ----------------*/
/*const emailInput = document.getElementById('email');

if (form && emailInput) {

    emailInput.addEventListener('focus', () => {
        if (!document.getElementById('message-info')) {
            const messageElt = document.createElement('div');
            messageElt.id = 'message-info';
            messageElt.textContent =
                "Nous utiliserons cette adresse pour vous répondre.";
            emailInput.parentNode.insertBefore(
                messageElt,
                emailInput.nextSibling
            );
        }
    });

    emailInput.addEventListener('blur', () => {
        const messageElt =
            document.getElementById('message-info');
        if (messageElt) {
            messageElt.remove();
        }
    });

}*/

/* ---------------- Modal formulaire ---------------- */
const form = document.querySelector('form');
const modal = document.getElementById('contact-modal');
const modalClose = document.getElementById('modal-close');

if (form && modal && modalClose) {
    form.addEventListener('submit', (e) => {
        e.preventDefault();
        const contenuTexte =
            tinymce.get('message')
                .getContent({ format: 'text' });
        if (contenuTexte.trim() === '') {
            alert('Veuillez saisir un message.');
            return;
        }
        modal.classList.add('open');
        form.reset();
        tinymce.get('message').setContent('');
    });
    modalClose.addEventListener('click', () => {
        modal.classList.remove('open');
    });
    modal.addEventListener('click', (e) => {
        if (e.target === modal) {
            modal.classList.remove('open');
        }
    });
    document.addEventListener('keydown', (e) => {
        if (
            e.key === 'Escape' &&
            modal.classList.contains('open')
        ) {
            modal.classList.remove('open');
        }
    });
}

/* ---------------- Tiny MCE ---------------- */
if (document.getElementById('message')) {
    tinymce.init({
        selector: '#message',
        height: 250,
        menubar: false,
        toolbar: false,
        statusbar: false,
        branding: false,
        language: 'fr-FR'
    });
}