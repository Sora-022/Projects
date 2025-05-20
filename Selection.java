package Menu;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.InputMismatchException;

public class Selection {

    public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String CYAN = "\u001B[36m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String MAGENTA = "\u001B[35m";
    public static final String BOLD = "\u001B[1m";

    private static final int CONSOLE_WIDTH = 100;

    public static void main(String[] args) {
        displayEnhancedLoadingScreen();
        
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            displayWelcomeMessage();
            displayMenu();
            choice = getUserChoice(scanner);

            switch (choice) {
                case 1:
                    checkEligibility(scanner);
                    break;
                case 2:
                    System.out.println(GREEN + "You selected: Grade Calculator" + RESET);
                    GradeCalculator gradeCalculator = new GradeCalculator();
                    gradeCalculator.calculateAverage(scanner);
                    break;
                case 3:
                    performCalculations(scanner);
                    break;
                case 4:
                case 5:
                case 6:
                case 7:
                    System.out.println(CYAN + "This option is currently on standby." + RESET);
                    sleep(2000);
                    clearConsole();
                    break;
                case 8:
                    displayExitingAnimation();
                    System.exit(0);
                default:
                    System.out.println(RED + "Invalid choice. Please select a valid option." + RESET);
                    sleep(2000);
                    clearConsole();
            }
        } while (choice != 8);

        scanner.close();
    }

    private static void displayEnhancedLoadingScreen() {
        String[] frames = {"◰", "◳", "◲", "◱"};
        String[] colors = {RED, GREEN, YELLOW, BLUE, MAGENTA, CYAN};
        String logo = 
            "   ▄████████  ▄██████▄     ▄████████    ▄████████    \n" +
            "  ███    ███ ███    ███   ███    ███   ███    ███    \n" +
            "  ███    █▀  ███    ███   ███    ███   ███    ███    \n" +
            "  ███        ███    ███  ▄███▄▄▄▄██▀   ███    ███    \n" +
            "▀███████████ ███    ███ ▀▀███▀▀▀▀▀   ▀███████████    \n" +
            "         ███ ███    ███ ▀███████████   ███    ███    \n" +
            "   ▄█    ███ ███    ███   ███    ███   ███    ███    \n" +
            " ▄████████▀   ▀██████▀    ███    ███   ███    █▀     \n" +
            "                          ███    ███                 \n" +
            "  ▄████████ ▄██   ▄     ▄████████     ███     ▄████████   ▄▄▄▄███▄▄▄▄      ▄████████ \n" +
            " ███    ███ ███   ██▄  ███    ███ ▀█████████▄ ███    ███ ▄██▀▀▀███▀▀▀██▄   ███    ███ \n" +
            " ███    █▀  ███▄▄▄███  ███    █▀     ▀███▀▀██ ███    █▀  ███   ███   ███   ███    █▀  \n" +
            " ███        ▀▀▀▀▀▀███ ▄███▄▄▄         ███   ▀ ███        ███   ███   ███  ▄███▄▄▄     \n" +
            "▀███████████ ▄██   ███ ▀▀███▀▀▀         ███     ███        ███   ███   ███ ▀▀███▀▀▀     \n" +
            "         ███ ███   ███   ███    █▄      ███     ███    █▄  ███   ███   ███   ███    █▄  \n" +
            "   ▄█    ███ ███   ███   ███    ███     ███     ███    ███ ███   ███   ███   ███    ███ \n" +
            " ▄████████▀   ▀█████▀    ██████████    ▄████▀   ████████▀   ▀█   ███   █▀    ██████████ \n";

        String[] logoLines = logo.split("\n");
        int logoWidth = logoLines[0].length();
        int leftPadding = (CONSOLE_WIDTH - logoWidth) / 2;

        clearConsole();
        
        for (String line : logoLines) {
            System.out.println(BLUE + BOLD + " ".repeat(leftPadding) + line + RESET);
        }
        
        System.out.println("\n" + CYAN + BOLD + centerText("Welcome to SORA SYSTEMS", CONSOLE_WIDTH) + RESET);
        System.out.println(YELLOW + BOLD + centerText("Prepare for an extraordinary experience!", CONSOLE_WIDTH) + RESET);
        
        // Add extra space between logo and loading animation
        System.out.println("\n\n");

        for (int i = 0; i <= 100; i++) {
            StringBuilder progressBar = new StringBuilder("[");
            for (int j = 0; j < 50; j++) {
                if (j < (i / 2)) {
                    progressBar.append("█");
                } else if (j == (i / 2)) {
                    progressBar.append("▓");
                } else {
                    progressBar.append("░");
                }
            }
            progressBar.append("]");
            
            String currentColor = colors[i % colors.length];
            String spinnerFrame = frames[i % frames.length];
            
            String loadingText = currentColor + BOLD + "Initializing " + spinnerFrame + RESET + " " + 
                                 progressBar + " " + i + "%";
            
            System.out.print("\r" + centerText(loadingText, CONSOLE_WIDTH));
            
            if (i % 10 == 0) {
                String animatedText = getAnimatedText(i / 10);
                System.out.print("\n" + centerText(animatedText, CONSOLE_WIDTH));
                System.out.print("\033[1A"); // Move cursor up one line
            }
            
            sleep(50);
        }
        
        System.out.println("\n\n" + GREEN + BOLD + centerText("SORA SYSTEMS Activated!", CONSOLE_WIDTH) + RESET);
        displayCompletionAnimation(CONSOLE_WIDTH);
        clearConsole();
    }

    private static String centerText(String text, int width) {
        if (text.length() >= width) {
            return text;
        }
        int padding = (width - text.length()) / 2;
        return " ".repeat(Math.max(0, padding)) + text;
    }

    private static String getAnimatedText(int phase) {
        String[] phrases = {
            "Powering up core systems",
            "Calibrating quantum processors",
            "Synchronizing star charts",
            "Activating holographic interfaces",
            "Initializing AI assistants"
        };
        return YELLOW + BOLD + phrases[phase % phrases.length] + "..." + RESET;
    }

    private static void displayCompletionAnimation(int consoleWidth) {
        String[] frames = {
            "[ ● ○ ○ ○ ]",
            "[ ○ ● ○ ○ ]",
            "[ ○ ○ ● ○ ]",
            "[ ○ ○ ○ ● ]",
            "[ ○ ○ ● ○ ]",
            "[ ○ ● ○ ○ ]"
        };

        for (int i = 0; i < 30; i++) {
            String animatedFrame = GREEN + BOLD + frames[i % frames.length] + " System Online!" + RESET;
            System.out.print("\r" + centerText(animatedFrame, consoleWidth));
            sleep(100);
        }
        System.out.println("\n");
    }

    private static void clearConsole() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    private static void sleep(long milliseconds) {
        try {
            TimeUnit.MILLISECONDS.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void displayWelcomeMessage() {
        System.out.println(CYAN + BOLD);
        System.out.println("==================================");
        System.out.println("    WELCOME TO SORA SYSTEMS       ");
        System.out.println("==================================" + RESET);
    }

    private static void displayMenu() {
        System.out.println(CYAN + "\n========== MENU ==========" + RESET);
        System.out.println(GREEN + "1. Check if eligible to vote");
        System.out.println("2. Grade Calculator");
        System.out.println("3. Convert & Get Area");
        System.out.println("4. Basic Calculator  with 2");
        System.out.println("5. Group number and member");
        System.out.println("6. Standby Option 4");
        System.out.println("7. Standby Option 5");
        System.out.println(RED + "8. Exit" + RESET);
        System.out.println(CYAN + "==========================" + RESET);
    }

    private static int getUserChoice(Scanner scanner) {
        while (true) {
            try {
                System.out.print(YELLOW + "Enter your choice (1-8): " + RESET);
                int choice = scanner.nextInt();
                if (choice < 1 || choice > 8) {
                    throw new InputMismatchException();
                }
                return choice;
            } catch (InputMismatchException e) {
                System.out.println(RED + "Invalid input. Please enter a number between 1 and 8." + RESET);
                scanner.nextLine(); // Clear the invalid input
            }
        }
    }

    private static void checkEligibility(Scanner scanner) {
        String subChoice;

        do {
            System.out.print("Enter your age: ");
            int age = getValidIntInput(scanner);

            if (age >= 18) {
                System.out.println(GREEN + "You are eligible to vote." + RESET);
                if (age >= 65) {
                    System.out.println(GREEN + "You are eligible for a senior citizen discount." + RESET);
                } else {
                    System.out.println(YELLOW + "You are not eligible for a senior citizen discount." + RESET);
                }
            } else {
                System.out.println(RED + "You are not eligible to vote." + RESET);
            }

            subChoice = getSubMenuChoice(scanner);
        } while (subChoice.equalsIgnoreCase("y"));
        clearConsole();
    }

    private static void performCalculations(Scanner scanner) {
        boolean returnToMainMenu = false;
        do {
            System.out.println(CYAN + "\n===== Calculations Submenu =====" + RESET);
            System.out.println(GREEN + "1. Convert millimeter to centimeter to meter");
            System.out.println("2. Identify if a number is odd or even");
            System.out.println("3. Multiplication");
            System.out.println("4. Exchange values of two inputs");
            System.out.println("5. Calculate area and circumference of a circle");
            System.out.println(RED + "6. Return to Main Menu" + RESET);
            System.out.println(CYAN + "================================" + RESET);

            int subMenuChoice = getValidIntInput(scanner, 1, 6);

            switch (subMenuChoice) {
                case 1:
                    convertLength(scanner);
                    break;
                case 2:
                    identifyOddEven(scanner);
                    break;
                case 3:
                    multiply(scanner);
                    break;
                case 4:
                    exchangeValues(scanner);
                    break;
                case 5:
                    calculateCircle(scanner);
                    break;
                case 6:
                    returnToMainMenu = true;
                    break;
            }
        } while (!returnToMainMenu);
        clearConsole();
    }

    private static void convertLength(Scanner scanner) {
        System.out.print("Enter length in millimeters: ");
        double mm = getValidDoubleInput(scanner);
        double cm = mm / 10;
        double m = mm / 1000;
        System.out.printf("%.2f mm = %.2f cm = %.2f m%n", mm, cm, m);
    }

    private static void identifyOddEven(Scanner scanner) {
        System.out.print("Enter a number: ");
        int num = getValidIntInput(scanner);
        System.out.println(num % 2 == 0 ? "Even" : "Odd");
    }

    private static void multiply(Scanner scanner) {
        System.out.print("Enter first number: ");
        double num1 = getValidDoubleInput(scanner);
        System.out.print("Enter second number: ");
        double num2 = getValidDoubleInput(scanner);
        System.out.printf("%.2f * %.2f = %.2f%n", num1, num2, num1 * num2);
    }

    private static void exchangeValues(Scanner scanner) {
        System.out.print("Enter first value: ");
        int a = getValidIntInput(scanner);
        System.out.print("Enter second value: ");
        int b = getValidIntInput(scanner);
        System.out.printf("Before: a = %d, b = %d%n", a, b);
        int temp = a;
        a = b;
        b = temp;
        System.out.printf("After: a = %d, b = %d%n", a, b);
    }

    private static void calculateCircle(Scanner scanner) {
        System.out.print("Enter radius of the circle: ");
        double radius = getValidDoubleInput(scanner);
        double area = Math.PI * radius * radius;
        double circumference = 2 * Math.PI * radius;
        System.out.printf("Area: %.2f, Circumference: %.2f%n", area, circumference);
    }

    public static void displayExitingAnimation() {
        clearConsole();
        String[] frames = {"◐", "◓", "◑", "◒"};
        String[] colors = {RED, YELLOW, GREEN, CYAN, BLUE, MAGENTA};

        System.out.println(CYAN + BOLD + centerText("Preparing to exit SORA SYSTEMS", CONSOLE_WIDTH) + RESET);
        
        // Add extra space before the animation
        System.out.println("\n\n");

        for (int i = 100; i >= 0; i--) {
            StringBuilder progressBar = new StringBuilder("[");
            for (int j = 0; j < 50; j++) {
                if (j < (i / 2)) {
                    progressBar.append("█");
                } else if (j == (i / 2)) {
                    progressBar.append("▓");
                } else {
                    progressBar.append("░");
                }
            }
            progressBar.append("]");
            
            String currentColor = colors[i % colors.length];
            String spinnerFrame = frames[i % frames.length];
            
            String loadingText = currentColor + BOLD + "Shutting down " + spinnerFrame + RESET + " " + 
                                 progressBar + " " + i + "%";
            
            System.out.print("\r" + centerText(loadingText, CONSOLE_WIDTH));
            
            if (i % 20 == 0) {
                String animatedText = getExitingAnimatedText(i / 20);
                System.out.print("\n" + centerText(animatedText, CONSOLE_WIDTH));
                System.out.print("\033[1A"); // Move cursor up one line
            }
            
            sleep(50);
        }
        
        // Add extra space after the animation
        System.out.println("\n\n" + RED + BOLD + centerText("SORA SYSTEMS Offline", CONSOLE_WIDTH) + RESET);
        sleep(2000);
        
        // Auto-close console (Note: This may not work on all systems)
        try {
            new ProcessBuilder("cmd", "/c", "exit").inheritIO().start().waitFor();
        } catch (Exception e) {
            System.exit(0);
        }
    }

    private static String getExitingAnimatedText(int phase) {
        String[] phrases = {
            "Saving user data",
            "Closing active processes",
            "Deactivating subsystems",
            "Powering downcore",
            "Finalizing shutdown sequence"
        };
        return YELLOW + BOLD + phrases[phase % phrases.length] + "..." + RESET;
    }

    private static String getSubMenuChoice(Scanner scanner) {
        while (true) {
            System.out.println(YELLOW + "\nWhat would you like to do next?");
            System.out.println("1. Check another age");
            System.out.println("2. Return to main menu");
            System.out.println("3. Exit program" + RESET);
            System.out.print("Enter your choice (1-3): ");
            
            try {
                int choice = scanner.nextInt();
                switch (choice) {
                    case 1:
                        return "y";
                    case 2:
                        return "n";
                    case 3:
                        displayExitingAnimation();
                        System.exit(0);
                    default:
                        throw new InputMismatchException();
                }
            } catch (InputMismatchException e) {
                System.out.println(RED + "Invalid choice. Please enter 1, 2, or 3." + RESET);
                scanner.nextLine(); // Clear the invalid input
            }
        }
    }

    private static int getValidIntInput(Scanner scanner) {
        while (true) {
            try {
                return scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println(RED + "Invalid input. Please enter a valid integer." + RESET);
                scanner.nextLine(); // Clear the invalid input
            }
        }
    }

    private static int getValidIntInput(Scanner scanner, int min, int max) {
        while (true) {
            try {
                int input = scanner.nextInt();
                if (input < min || input > max) {
                    throw new InputMismatchException();
                }
                return input;
            } catch (InputMismatchException e) {
                System.out.println(RED + "Invalid input. Please enter a number between " + min + " and " + max + "." + RESET);
                scanner.nextLine(); // Clear the invalid input
            }
        }
    }

    private static double getValidDoubleInput(Scanner scanner) {
        while (true) {
            try {
                return scanner.nextDouble();
            } catch (InputMismatchException e) {
                System.out.println(RED + "Invalid input. Please enter a valid number." + RESET);
                scanner.nextLine(); // Clear the invalid input
            }
        }
    }
}

class GradeCalculator {
    public void calculateAverage(Scanner scanner) {
        String subChoice;

        do {
            System.out.print("How many subjects do you have? ");
            int numberOfSubjects = getValidPositiveIntInput(scanner);

            double total = 0;

            for (int i = 1; i <= numberOfSubjects; i++) {
                System.out.print("Enter grade for subject " + i + ": ");
                double grade = getValidGradeInput(scanner);
                total += grade;
            }

            double average = total / numberOfSubjects;
            System.out.println(Selection.GREEN + "Your average grade is: " + String.format("%.2f", average) + Selection.RESET);
            
            if (average >= 75) {
                System.out.println("\u001B[32mResult: Passed! Congratulations!\u001B[0m");
            } else if (average < 50) {
                System.out.println("\u001B[31mResult: Failed. Please try harder next time.\u001B[0m");
            } else {
                System.out.println("\u001B[33mResult: Retake. You need to improve.\u001B[0m");
            }

            subChoice = getSubMenuChoice(scanner);
        } while (subChoice.equalsIgnoreCase("y"));
    }

    private int getValidPositiveIntInput(Scanner scanner) {
        while (true) {
            try {
                int input = scanner.nextInt();
                if (input <= 0) {
                    throw new InputMismatchException();
                }
                return input;
            } catch (InputMismatchException e) {
                System.out.println(Selection.RED + "Invalid input. Please enter a positive integer." + Selection.RESET);
                scanner.nextLine(); // Clear the invalid input
            }
        }
    }

    private double getValidGradeInput(Scanner scanner) {
        while (true) {
            try {
                double input = scanner.nextDouble();
                if (input < 0 || input > 100) {
                    throw new InputMismatchException();
                }
                return input;
            } catch (InputMismatchException e) {
                System.out.println(Selection.RED + "Invalid grade. Please enter a value between 0 and 100." + Selection.RESET);
                scanner.nextLine(); // Clear the invalid input
            }
        }
    }

    private String getSubMenuChoice(Scanner scanner) {
        while (true) {
            System.out.println(Selection.YELLOW + "\nWhat would you like to do next?");
            System.out.println("1. Input grades again");
            System.out.println("2. Return to main menu");
            System.out.println("3. Exit program" + Selection.RESET);
            System.out.print("Enter your choice (1-3): ");
            
            try {
                int choice = scanner.nextInt();
                switch (choice) {
                    case 1:
                        return "y";
                    case 2:
                        return "n";
                    case 3:
                        Selection.displayExitingAnimation();
                        System.exit(0);
                    default:
                        throw new InputMismatchException();
                }
            } catch (InputMismatchException e) {
                System.out.println(Selection.RED + "Invalid choice. Please enter 1, 2, or 3." + Selection.RESET);
                scanner.nextLine(); // Clear the invalid input
            }
        }
    }
}

