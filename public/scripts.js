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
const secNav = document.getElementById('menu-amicale');

if (burgerBtn && mainNavLinks && secNav) {
    // Fonction pour basculer l'affichage des deux menus
    function toggleMenus() {
        mainNavLinks.classList.toggle('open');
        secNav.classList.toggle('open');
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
const form = document.querySelector('form');
const emailInput = document.getElementById('email');

if (form && emailInput) {
    document.getElementById('email').addEventListener('focus', function () {
        const messageElt = document.createElement('div'); messageElt.setAttribute('id', 'message');
        messageElt.textContent = "Un lien d'activation sera envoyé à cette adresse"
    })

    document.getElementById('email').addEventListener('blur', function () {
        document.getElementById('message').remove();
    })
}