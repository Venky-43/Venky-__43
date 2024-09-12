function generateBill() {
    // Get form values
    const pizzaType = document.getElementById('pizzaType').value;
    const extraCheese = document.getElementById('extraCheese').checked;
    const extraToppings = document.getElementById('extraToppings').checked;
    const takeAway = document.getElementById('takeAway').checked;

    // Prices
    const prices = {
        veg: 200,
        nonveg: 300,
        deluxVeg: 400,
        deluxNonveg: 500
    };

    // Extra charges
    const extraCheesePrice = 50;
    const extraToppingsPrice = 70;
    const packagingCharges = 20;

    // Base price
    let basePrice = prices[pizzaType];
    let totalPrice = basePrice;

    // Add extra charges if not delux pizza
    if (pizzaType === 'veg' || pizzaType === 'nonveg') {
        if (extraCheese) {
            totalPrice += extraCheesePrice;
        }
        if (extraToppings) {
            totalPrice += extraToppingsPrice;
        }
    }

    // Add packaging charges if take away
    if (takeAway) {
        totalPrice += packagingCharges;
    }

    // Create summary
    const summary = `
        Pizza Type: ${capitalizeFirstLetter(pizzaType.replace(/([A-Z])/g, ' $1').trim())}<br>
        Extra Cheese: ${extraCheese ? 'Yes' : 'No'}<br>
        Extra Toppings: ${extraToppings ? 'Yes' : 'No'}<br>
        Take Away: ${takeAway ? 'Yes (Packaging charges applied)' : 'No'}<br>
        <strong>Total Bill: ${totalPrice} INR</strong>
    `;

    // Display summary
    document.getElementById('summary').innerHTML = summary;
}

// Utility function to capitalize the first letter of each word
function capitalizeFirstLetter(string) {
    return string.charAt(0).toUpperCase() + string.slice(1);
}
