jQuery(function($) {
    var $bandef = $('.bandef'),
        $row = $bandef.parent(),
        rowWidth = $row.width(),
        bandefWidth = 0;

    // Calcul de la largeur totale des images
    $bandef.find('img').each(function() {
        bandefWidth += $(this).outerWidth(true); // Inclut les marges
    });

    // Dupliquer les images pour assurer un défilement continu
    while (bandefWidth < rowWidth * 2) {
        $bandef.append($bandef.html());
        bandefWidth += bandefWidth;
    }

    // Ajout des styles dynamiques pour l'animation
    $('head').append('<style>@keyframes marquee { 0% { transform: translateX(0); } 100% { transform: translateX(-' + bandefWidth / 2 + 'px); } }</style>');
});


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