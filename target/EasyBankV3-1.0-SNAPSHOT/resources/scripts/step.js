var currentStep = 1;
showStep(currentStep);

function showStep(step) {
    var steps = document.getElementsByClassName("step");
    if (step < 1) currentStep = 1;
    if (step > steps.length) currentStep = steps.length;
    for (var i = 0; i < steps.length; i++) {
        steps[i].style.display = "none";
    }
    steps[currentStep - 1].style.display = "block";

    var stepButtons = document.getElementsByClassName("col-4 pad-ste");
    for (var i = 0; i < stepButtons.length; i++) {
        stepButtons[i].classList.remove("active");
    }

    var currentStepButton = document.getElementById("link-back-step-" + currentStep);
    currentStepButton.classList.add("active");

    if (currentStep === 1) {
        document.getElementById("prevBtn").style.display = "none";
    } else if (currentStep === steps.length) {
        document.getElementById("prevBtn").style.display = "block";
        document.getElementById("nextBtn").innerHTML = "Done";
    } else {
        document.getElementById("prevBtn").style.display = "block";
        document.getElementById("nextBtn").innerHTML = "Next";
    }
}

function nextStep() {
    var steps = document.getElementsByClassName("step");
    if (currentStep < steps.length) {
        currentStep++;
        showStep(currentStep);
    }
    if (currentStep === 3) {
        saveToLocalStorage();
    }
}

function prevStep() {
    if (currentStep > 1) {
        currentStep--;
        showStep(currentStep);
    }
}

function changeStep(step) {
    showStep(step);
}

function saveToLocalStorage() {
    var loanAmountInput = document.getElementById("loanAmount");
    var monthlyPaymentsInput = document.getElementById("monthlyPayments");
    var simulation = document.getElementById("result").textContent;
    var code = document.getElementById("code");

    var simulationValue = parseFloat(simulation.match(/[\d.]+/));

    localStorage.setItem("montant", loanAmountInput.value);
    localStorage.setItem("duree", monthlyPaymentsInput.value);
    localStorage.setItem("simulation", simulationValue);
    localStorage.setItem("client_code", code.value)
}












function checkAndLoadClient() {
    var clientCode = document.getElementById("code").value;

    fetch('http://localhost:8080/searchClient?code=' + clientCode)
        .then(response => response.text())
        .then(clientDetails => {

            if (clientDetails) {
                var detailsArray = clientDetails.split("\n");

                document.getElementById("nom").value = detailsArray[0].substring("Last Name: ".length);
                document.getElementById("prenom").value = detailsArray[1].substring("First Name: ".length);
                document.getElementById("dateN").value = detailsArray[2].substring("Date of Birth: ".length);
                document.getElementById("tel").value = detailsArray[3].substring("Phone: ".length);
                document.getElementById("adress").value = detailsArray[4].substring("Address: ".length);

                nextStep();
            } else {
                alert('Client not found');
            }
        })
        .catch(error => {
            console.error('Error:', error);
        });
}

