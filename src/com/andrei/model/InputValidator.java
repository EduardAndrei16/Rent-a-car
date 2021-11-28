package com.andrei.model;

public class InputValidator {

        public static boolean ValidateStringLength(String input) {
        if (input.length() > 200) {
            System.out.println("Invalid input, maximum length of 200 characters!");
            return false;
        } else {
            return true;
        }
    }

        public static boolean ValidateStringType(String input){
            if(input.matches("Economy") || input.matches("Suv" )|| input.matches("Standard") ){
                return true;
            }
            else{
                System.out.println("The car type should be Standard, Economy or Suv !");
                return false;
            }
        }

        public static boolean CheckPriceInput(String type,Float Price){
        if(Price<=0){
            System.out.println("The price has to be a real positive value ");
            return false;
        }
        if(type.matches("Economy")) {
            if (Price > 18) {
                System.out.println("The price is too big for the Economy type ");
                return false;
            }
        }
        if(type.matches("Standard")) {
            if (Price > 30) {
                System.out.println("The price is too big for the Standard type");
                return false;
            }
        }
            if (type.matches("Suv")) {
                if(Price<33){
                    System.out.println("Price is too low for Suv type");
                    return false;
                }
            }
            return true;
        }

        public static boolean ColorInputCheck(String input){
        if (input.length() > 50) {
            System.out.println("Invalid input, maximum length of 50 characters!");
            return false;
        } else {
            return true;
        }

    }
        }




