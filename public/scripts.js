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

if (burgerBtn && mainNavLinks && submenu) {
    // Fonction pour basculer l'affichage des deux menus
    function toggleMenus() {
        mainNavLinks.classList.toggle('open');
        submenu.classList.toggle('open');
    }
    // Écouteur d'événement pour le bouton burger
    burgerBtn.addEventListener('click', toggleMenus);
    // Fermer les menus en cliquant sur un lien
    const mainNavItems = mainNavLinks.querySelectorAll('a');
    mainNavItems.forEach(link => {
        link.addEventListener('click', toggleMenus);
    });
    const secNavItems = secNav.querySelectorAll('a');
    secNavItems.forEach(link => {
        link.addEventListener('click', toggleMenus);
    });
}

/* ---------------- Formulaire ---------------- */

/* ---------------- Message au clic du champ e-mail ----------------*/
/*

const emailInput = document.getElementById('email');

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

}
*/

/* ---------------- Modal ---------------- */

const form = document.querySelector('form');
const modal = document.getElementById('contact-modal');
const modalClose = document.getElementById('modal-close');

if (form && modal && modalClose) {
    // Ouverture après validation du formulaire
    form.addEventListener('submit', (e) => {
        e.preventDefault();
        modal.classList.add('open');
        form.reset();
    });
    // Fermeture avec le btn-icon
    modalClose.addEventListener('click', () => {
        modal.classList.remove('open');
    });

    // Fermeture clic hors modal
    modal.addEventListener('click', (e) => {
        if (e.target === modal) {
            modal.classList.remove('open');
        }
    });
    // Fermeture avec la touche Échap
    document.addEventListener('keydown', (e) => {
        if (
            e.key === 'Escape' &&
            modal.classList.contains('open')
        ) {
            modal.classList.remove('open');
        }
    });
}