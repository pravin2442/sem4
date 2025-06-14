// Create the result div and add it to the HTML
let result = document.createElement('div');
result.id = 'result';
document.getElementById('wrapper').appendChild(result);

// Event listener
document.addEventListener("DOMContentLoaded", function() {
    let button = document.querySelector("button");
    button.addEventListener("click", calculate);
});

function calculate() {
    let input = document.getElementById('two').value.trim();
    
    // Basic validation
    if (!input) {
        alert('Please enter a calculation');
        return;
    }

    // Check if input has at least 3 characters
    if (input.length < 3) {
        alert('Please enter a valid calculation (e.g., 5+3)');
        return;
    }

    let a = Number(input[0]);
    let operator = input[1];
    let c = Number(input[2]);
    let result;

    // Validate numbers
    if (isNaN(a) || isNaN(c)) {
        alert('Please enter numbers in the first and third positions');
        return;
    }

    switch(operator) {
        case '+':
            result = a + c;
            break;
        case '-':
            result = a - c;
            break;
        case '*':
            result = a * c;
            break;
        case '/':
            if (c === 0) {
                alert('Cannot divide by zero');
                return;
            }
            result = a / c;
            break;
        case '%':
            result = a % c;
            break;
        default:
            alert('Please use a valid operator: +, -, *, /, %');
            return;
    }

    let resultText = `${a} ${operator} ${c} = ${result}`;
    document.getElementById('result').innerHTML = resultText;
}