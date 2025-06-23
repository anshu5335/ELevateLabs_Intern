import java.util.Scanner;

public class Calculator {

    public static double add(double a, double b) {
        return a + b;
    }

    public static double subtract(double a, double b) {
        return a - b;
    }

    public static double multiply(double a, double b) {
        return a * b;
    }

    public static double divide(double a, double b) {
        if (b == 0) {
            System.out.println("Error: Cannot divide by zero.");
            return Double.NaN;
        }
        return a / b;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        System.out.println("-------------------------------------");
        System.out.println("     Java Console Calculator         ");
        System.out.println("-------------------------------------");

        while (running) {
            System.out.println("\nSelect an operation:");
            System.out.println("1. Addition (+)");
            System.out.println("2. Subtraction (-)");
            System.out.println("3. Multiplication (*)");
            System.out.println("4. Division (/)");
            System.out.println("5. Exit");
            System.out.print("Enter your choice (1-5): ");

            int choice = -1;
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
            } else {
                System.out.println("Invalid input! Please enter a number between 1 and 5.");
                scanner.next();
                // Loop will naturally reiterate
            }

            if (choice == 5) {
                running = false;
                System.out.println("Exiting calculator. Goodbye!");
            } else if (choice >= 1 && choice <= 4) {
                double num1 = 0;
                double num2 = 0;
                boolean validInput = true;

                System.out.print("Enter the first number: ");
                if (scanner.hasNextDouble()) {
                    num1 = scanner.nextDouble();
                } else {
                    System.out.println("Invalid input for the first number. Please enter a numeric value.");
                    scanner.next();
                    validInput = false;
                }

                if (validInput) {
                    System.out.print("Enter the second number: ");
                    if (scanner.hasNextDouble()) {
                        num2 = scanner.nextDouble();
                    } else {
                        System.out.println("Invalid input for the second number. Please enter a numeric value.");
                        scanner.next();
                        validInput = false;
                    }
                }

                if (validInput) {
                    double result;

                    switch (choice) {
                        case 1:
                            result = add(num1, num2);
                            System.out.println("Result: " + num1 + " + " + num2 + " = " + result);
                            break;
                        case 2:
                            result = subtract(num1, num2);
                            System.out.println("Result: " + num1 + " - " + num2 + " = " + result);
                            break;
                        case 3:
                            result = multiply(num1, num2);
                            System.out.println("Result: " + num1 + " * " + num2 + " = " + result);
                            break;
                        case 4:
                            result = divide(num1, num2);
                            if (!Double.isNaN(result)) {
                                System.out.println("Result: " + num1 + " / " + num2 + " = " + result);
                            }
                            break;
                    }
                }
            } else {
                System.out.println("Invalid choice. Please pick a number from 1 to 5.");
            }
        }
        scanner.close();
    }
}
