document.getElementById("nextBtn").addEventListener("click", function () {
    if (currentStep === 3) {
        openModal();
        updateFormFromLocalStorage();

    }
});

function openModal() {
    var modal = document.getElementById("myModal");
    modal.style.display = "block";
}

function closeModal() {
    var modal = document.getElementById("myModal");
    modal.style.display = "none";
}


document.getElementById("closeModal").addEventListener("click", function() {
    closeModal();
});

function updateFormFromLocalStorage() {
    var montant = localStorage.getItem("montant");
    var duree = localStorage.getItem("duree");
    var simulation = localStorage.getItem("simulation");
    var client_code = localStorage.getItem("client_code");

    document.getElementById("montant").value = montant;
    document.getElementById("duree").value = duree;
    document.getElementById("simulation").value = simulation;
    document.getElementById("client_code").value = client_code;
}

window.addEventListener("storage", updateFormFromLocalStorage);

