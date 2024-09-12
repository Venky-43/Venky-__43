import java.util.Scanner;

abstract class Pizza {
    String name;
    double basePrice;
    boolean extraCheese;
    boolean extraToppings;

    Pizza(String name, double basePrice) {
        this.name = name;
        this.basePrice = basePrice;
        this.extraCheese = false;
        this.extraToppings = false;
    }

    public abstract void addExtraCheese();

    public abstract void addExtraToppings();

    public double calculatePrice() {
        double totalPrice = basePrice;
        if (extraCheese) {
            totalPrice += 50; // Extra Cheese price
        }
        if (extraToppings) {
            totalPrice += 70; // Extra Toppings price
        }
        return totalPrice;
    }

    public String toString() {
        return name + " Pizza - Base Price: " + basePrice;
    }
}

class VegPizza extends Pizza {
    VegPizza() {
        super("Veg", 200); // Base price for Veg Pizza
    }

    @Override
    public void addExtraCheese() {
        this.extraCheese = true;
    }

    @Override
    public void addExtraToppings() {
        this.extraToppings = true;
    }
}

class NonVegPizza extends Pizza {
    NonVegPizza() {
        super("Non-Veg", 300); // Base price for Non-Veg Pizza
    }

    @Override
    public void addExtraCheese() {
        this.extraCheese = true;
    }

    @Override
    public void addExtraToppings() {
        this.extraToppings = true;
    }
}

class DeluxVegPizza extends Pizza {
    DeluxVegPizza() {
        super("Delux Veg", 400); // Base price for Delux Veg Pizza with extras
        this.extraCheese = true; // Already loaded with extra cheese
        this.extraToppings = true; // Already loaded with extra toppings
    }

    @Override
    public void addExtraCheese() {
        // No need to add extra cheese for Delux Pizza
    }

    @Override
    public void addExtraToppings() {
        // No need to add extra toppings for Delux Pizza
    }
}

class DeluxNonVegPizza extends Pizza {
    DeluxNonVegPizza() {
        super("Delux Non-Veg", 500); // Base price for Delux Non-Veg Pizza with extras
        this.extraCheese = true;
        this.extraToppings = true;
    }

    @Override
    public void addExtraCheese() {
        // No need to add extra cheese for Delux Pizza
    }

    @Override
    public void addExtraToppings() {
        // No need to add extra toppings for Delux Pizza
    }
}

public class PizzaBillGenerator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Pizza selectedPizza = null;
        boolean takeAway = false;
        double packagingCharges = 20; // Fixed packaging charge

        System.out.println("Welcome to Pizzamania!");
        System.out.println("Please select your pizza type:");
        System.out.println("1. Veg Pizza\n2. Non-Veg Pizza\n3. Delux Veg Pizza\n4. Delux Non-Veg Pizza");

        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                selectedPizza = new VegPizza();
                break;
            case 2:
                selectedPizza = new NonVegPizza();
                break;
            case 3:
                selectedPizza = new DeluxVegPizza();
                break;
            case 4:
                selectedPizza = new DeluxNonVegPizza();
                break;
            default:
                System.out.println("Invalid choice! Please select a valid pizza.");
                System.exit(0);
        }

        if (!(selectedPizza instanceof DeluxVegPizza || selectedPizza instanceof DeluxNonVegPizza)) {
            System.out.println("Do you want to add extra cheese? (yes/no)");
            String cheeseChoice = scanner.next();
            if (cheeseChoice.equalsIgnoreCase("yes")) {
                selectedPizza.addExtraCheese();
            }

            System.out.println("Do you want to add extra toppings? (yes/no)");
            String toppingsChoice = scanner.next();
            if (toppingsChoice.equalsIgnoreCase("yes")) {
                selectedPizza.addExtraToppings();
            }
        }

        System.out.println("Do you want to opt for take away? (yes/no)");
        String takeAwayChoice = scanner.next();
        if (takeAwayChoice.equalsIgnoreCase("yes")) {
            takeAway = true;
        }

        double totalBill = selectedPizza.calculatePrice();
        if (takeAway) {
            totalBill += packagingCharges;
        }

        System.out.println("\nYour Order Summary:");
        System.out.println(selectedPizza);
        System.out.println("Extra Cheese: " + (selectedPizza.extraCheese ? "Yes" : "No"));
        System.out.println("Extra Toppings: " + (selectedPizza.extraToppings ? "Yes" : "No"));
        System.out.println("Take Away: " + (takeAway ? "Yes (Packaging charges applied)" : "No"));
        System.out.println("Total Bill: " + totalBill + " INR");

        scanner.close();
    }
}
