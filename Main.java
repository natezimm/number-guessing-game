package com.nathan;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        //Initialize scanner
        Scanner scanner = new Scanner(System.in);

        //Pick random number to guess
        int randomNumber = 7578;

        //Get each digit out of random number
        int firstDigit = randomNumber / 1000;
        int secondDigit = (randomNumber % 1000) / 100;
        int thirdDigit = (randomNumber % 100) / 10;
        int fourthDigit = (randomNumber % 10) / 1;

        //Put digits into array
        int[] digits = new int[4];
        digits[0] = firstDigit;
        digits[1] = secondDigit;
        digits[2] = thirdDigit;
        digits[3] = fourthDigit;

        //Make copy of digits array
        int[] digitsCopy = new int[4];
        for(int i = 0; i < digits.length; i++){
            digitsCopy[i] = digits[i];
        }

        //Initialize variables for guessing game
        int n = digits.length;
        int userGuess;
        int numberOfGuesses = 0;

        //sCounter for correct digit in correct place
        //pCounter for a digit existing but at different index
        int sCounter = 0;
        int pCounter = 0;

        System.out.println("I'm thinking of a 4 digit number. Please try to guess my number!");

        while(true){
            do{
                System.out.println("Your guess? ");
                userGuess = scanner.nextInt();
                if(userGuess < 1000 || userGuess > 9999){
                    System.out.println("Please enter 4 digit number. From 1000-9999.");
                }
            } while (userGuess < 1000 || userGuess > 9999);

            //Reset digits copy for each guess
            for(int i = 0; i < n; i++){
                digitsCopy[i] = digits[i];
            }

            //Count guesses and reset counters
            numberOfGuesses++;
            sCounter = 0;
            pCounter = 0;

            //Let user know it is correct and how many guesses it took
            if(userGuess == randomNumber){
                System.out.println("You got it right in " + numberOfGuesses + " guesses!");
                break;
            }

            //Put user guess digits into array
            int[] userGuessDigits = new int[4];
            userGuessDigits[0] = userGuess / 1000;
            userGuessDigits[1] = (userGuess % 1000) / 100;
            userGuessDigits[2] = (userGuess % 100) / 10;
            userGuessDigits[3] = (userGuess % 10) / 1;

            //Check for correct digit at correct index
            for(int i = 0; i < n; i++){
                if(digits[i] == userGuessDigits[i]){
                    sCounter++;
                    digitsCopy[i] = 0;
                    userGuessDigits[i] = 0;
                }
            }

            //Print out number of correct digits at correct index
            for(int i = 0; i < sCounter; i++){
                System.out.print("S ");
            }

            //Check for digits existing at different index
            for(int i = 0; i < n; i++){
                for(int j = 0; j < n; j++){
                    if((digitsCopy[i] == userGuessDigits[j]) && (i != j) && digitsCopy[i] != 0){
                        pCounter++;
                        userGuessDigits[j] = 0;
                        break;
                    }
                }
            }

            //Print out number of digits existing at different index
            for(int i = 0; i < pCounter; i++){
                System.out.print("P ");
            }

            if(sCounter != 0 || pCounter != 0){
                System.out.println();
            }

            //Print to user if no digits match
            if(sCounter == 0 && pCounter == 0){
                System.out.print("F");
                System.out.println();
            }
        }
    }
}
