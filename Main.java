package org.example;

import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        int maxNumber = 100; // You can change this to set the range of numbers
        int randomNumber = random.nextInt(maxNumber) + 1; // Random number between 1 and maxNumber
        int maxTries = 10;
        boolean hasGuessedCorrectly = false;

        System.out.println("Welcome to the Number Guessing Game!");
        System.out.println("Try to guess the number between 1 and " + maxNumber);

        for (int tryCount = 1; tryCount <= maxTries; tryCount++) {
            System.out.println("Attempt " + tryCount + ": Enter your guess:");
            int userGuess = scanner.nextInt();

            if (userGuess == randomNumber) {
                hasGuessedCorrectly = true;
                break;
            } else if (userGuess < randomNumber) {
                System.out.println("Too low! Try again.");
            } else {
                System.out.println("Too high! Try again.");
            }
        }

        if (hasGuessedCorrectly) {
            System.out.println("Congratulations! You got it :)");
        } else {
            System.out.println("Sorry, you're out of tries. The correct number was " + randomNumber);
        }

        scanner.close();
    }
}