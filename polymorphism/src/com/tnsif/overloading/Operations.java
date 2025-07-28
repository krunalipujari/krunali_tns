package com.tnsif.overloading;

public class Operations {

    
    public static boolean isPalindrome(long number) {
        long rev = 0;
        long original = number;

        while (number != 0) {
            rev = rev * 10 + number % 10;
            number /= 10;
        }

        return original == rev;
    }

    public static void main(String[] args) {
        long number = 32123;

        System.out.println("Given number is Palindrome: " + isPalindrome(number));
    }
}
