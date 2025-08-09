// Typing effect
var typed = new Typed('#element', {
    strings: ['Web Developer.', 'Data Analyst.'],
    typeSpeed: 60,
    loop: true
});

document.querySelector("#eventBtn").addEventListener("click", function () {
    window.open("event-management.html", "_blank");
});

document.querySelector("#admissionBtn").addEventListener("click", function () {
    window.open("admission.html", "_blank");
});

document.querySelector("#ecommerceBtn").addEventListener("click", function () {
    window.open("ecommerce.html", "_blank");
});

document.getElementById("sendMessageBtn").addEventListener("click", function (e) {
    e.preventDefault();
    alert("Message sent successfully!");
});


