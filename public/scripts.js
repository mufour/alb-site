/* Animation du bandeau d'images de la page d'accueil */
const track = document.querySelector('.bandef-track');
const bandef = document.querySelector('.bandef');

let speed = 1;
let position = 0;
let isPaused = false;
let direction = -1;

/* Dupliquer les images */
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

/* Début de menu burger */
var sidenav = document.getElementById("mySidenav");
var openBtn = document.getElementById("openBtn");
var closeBtn = document.getElementById("closeBtn");

openBtn.onclick = openNav;
closeBtn.onclick = closeNav;

/* Définir la largueur de la side navigation à 250px */
function openNav() {
  sidenav.classList.add("active");
}

/* Définir la largueur de la side navigation à 0px */
function closeNav() {
  sidenav.classList.remove("active");
}


/* Formulaire */
document.getElementById('email').addEventListener('focus', function () {
            messageElt = document.createElement('div');
            messageElt.setAttribute('id', 'message');
            messageElt.textContent = "Un lien d'activation sera envoyé à cette adresse"
            emailElt.parentNode.insertBefore(messageElt, emailElt.nextSibling);
        })

        document.getElementById('email').addEventListener('blur', function () {
            document.getElementById('message').remove();
        })

        document.querySelector('form').addEventListener('submit', function (e) {
            e.preventDefault();
            if (e.target.password.value === e.target.confirmpw.value) {
                alert("Votre mot de passe est valide, la demande a bien été envoyée");
            } else {
                const confirmpwElt = document.querySelector('label[for="confirmpw"]');
                confirmpwElt.textContent += " - Vos mots de passe ne coïncident pas";
                confirmpwElt.style.color = "red";
            }
        });