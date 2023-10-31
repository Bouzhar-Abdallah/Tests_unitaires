function updateValues() {
    var loanAmountInput = document.getElementById("loanAmount");
    var loanAmountValue = document.getElementById("loanAmountValue");
    loanAmountValue.innerHTML = loanAmountInput.value;

    var monthlyPaymentsInput = document.getElementById("monthlyPayments");
    var monthlyPaymentsValue = document.getElementById("monthlyPaymentsValue");
    monthlyPaymentsValue.innerHTML = monthlyPaymentsInput.value;
}

function calculateMonthlyPayment() {
    var borrowedCapital = parseFloat(document.getElementById("loanAmount").value);
    var numberOfMonthlyPayments = parseInt(document.getElementById("monthlyPayments").value);

    var annualInterestRate = 0.12;
    var monthlyInterestRate = annualInterestRate / 12;

    var monthlyPayment = (borrowedCapital * monthlyInterestRate) /
        (1 - Math.pow(1 + monthlyInterestRate, -numberOfMonthlyPayments));

    var monthlyPaymentValue = monthlyPayment.toFixed(2);

    document.getElementById("result").innerHTML = monthlyPaymentValue + " MAD";
}

document.getElementById("loanAmount").addEventListener("input", updateValues);
document.getElementById("monthlyPayments").addEventListener("input", updateValues);
document.getElementById("loanAmount").addEventListener("input", calculateMonthlyPayment);
document.getElementById("monthlyPayments").addEventListener("input", calculateMonthlyPayment);

updateValues();
calculateMonthlyPayment();