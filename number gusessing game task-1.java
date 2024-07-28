import java.util.Random;
import java.util.Scanner;

public class NumberGuessingGame {
    public static void main(String[] args) {
        Random random = new Random();
        Scanner scanner = new Scanner(System.in);

        int numberToGuess = random.nextInt(100) + 1; // Generates a random number between 1 and 100
        int numberOfAttempts = 0;
        int maxAttempts = 10;
        boolean hasGuessedCorrectly = false;

        System.out.println("Welcome to the Number Guessing Game!");
        System.out.println("Try to guess the number between 1 and 100.");

        while (numberOfAttempts < maxAttempts && !hasGuessedCorrectly) {
            System.out.print("Enter your guess: ");
            int userGuess = scanner.nextInt();
            numberOfAttempts++;

            if (userGuess < numberToGuess) {
                System.out.println("Too low!");
            } else if (userGuess > numberToGuess) {
                System.out.println("Too high!");
            } else {
                System.out.println("Correct! You've guessed the number in " + numberOfAttempts + " attempts.");
                hasGuessedCorrectly = true;
            }

            if (numberOfAttempts == maxAttempts && !hasGuessedCorrectly) {
                System.out.println("Sorry, you've reached the maximum number of attempts. The number was " + numberToGuess + ".");
            }
        }

        scanner.close();
    }
}
