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

/* Set the width of the side navigation to 250px */
function openNav() {
  sidenav.classList.add("active");
}

/* Set the width of the side navigation to 0 */
function closeNav() {
  sidenav.classList.remove("active");
}