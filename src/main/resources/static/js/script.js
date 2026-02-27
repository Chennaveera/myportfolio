const roles = [ "Software Developer","Java Developer", "Spring Boot Developer"];
let i = 0, j = 0;
const typing = document.getElementById("typing");

function type() {
  if (j < roles[i].length) {
    typing.textContent += roles[i][j++];
    setTimeout(type, 100);
  } else {
    setTimeout(erase, 1500);
  }
}

function erase() {
  if (j > 0) {
    typing.textContent = roles[i].substring(0, --j);
    setTimeout(erase, 50);
  } else {
    i = (i + 1) % roles.length;
    setTimeout(type, 500);
  }
}

type();


// Skill Animation on Scroll
const skillSection = document.querySelector("#skills");
const skillBars = document.querySelectorAll(".skill-fill");

const observer = new IntersectionObserver(entries => {
  entries.forEach(entry => {
    if (entry.isIntersecting) {
      skillBars.forEach(bar => {
        bar.style.width = bar.getAttribute("data-width");
      });
    }
  });
}, { threshold: 0.4 });

/*observer.observe(skillSection);*/

function showModal(message) {
    document.getElementById("popupMessage").textContent = message;
    document.getElementById("popupModal").style.display = "block";
}

function closeModal() {
    document.getElementById("popupModal").style.display = "none";
}

function toggleMenu() {
    const nav = document.getElementById("navLinks");
    nav.classList.toggle("active");
}

function toggleDropdown() {
    const menu = document.getElementById("dropdownMenu");
    menu.classList.toggle("show");
}

/* Close menu when clicking outside */
window.addEventListener("click", function (e) {
    const nav = document.getElementById("navLinks");
    const hamburger = document.querySelector(".hamburger");

    if (!nav.contains(e.target) && !hamburger.contains(e.target)) {
        nav.classList.remove("active");
    }
});

