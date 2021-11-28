package com.andrei.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class InputValidatorTest {

 InputValidator test = new InputValidator();

    @BeforeEach
    public void setup(){


        System.out.println("Running a test...");
    }

    @Test
    void ShouldBeLess() {
        String input = "BMW";
        assertTrue(test.ValidateStringLength(input));
        System.out.println("assertTrue...");
        input = "saaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaafsafasfasfasfasfasfasfasfasfaaaaaaaaaaaaaaaaaaaafsafasfasfasfasfsafasdadssadadsasdfsafasfafasfasfasfasfadsafsafasfasfafasfasfasfasfasfdasdasdasda";
        assertFalse(test.ValidateStringLength(input));
        System.out.println("assertFalse...");

    }


    @Test
    void validateStringType() {
        ArrayList<String> goodInputs = new ArrayList<>();
        goodInputs.add("Economy");
        goodInputs.add("Suv");
        goodInputs.add("Standard");
        for (int n = 0; n <= 2; n++) {
            assertTrue(test.ValidateStringType(goodInputs.get(n)));
            System.out.println("Testing assertTrue for String Type...");
        }
        ArrayList<String> badInputs = new ArrayList<>();
        badInputs.add("Ecnomy");
        badInputs.add("svu");
        badInputs.add("Strandar");
        for (int n = 0; n <= 2; n++) {
            assertFalse(test.ValidateStringType(badInputs.get(n)));
            System.out.println("Testing assertFalse for String Type...");


        }
    }
    @Test
    void checkPriceInput() {
        for (int n=1; n<=50; n++) {
            float rand = new Random().nextInt(1000);
            System.out.println(rand);
            if (rand > 0 && rand <= 18) {
                assertTrue(test.CheckPriceInput("Economy", rand));
            } else {
                assertFalse(test.CheckPriceInput("Economy", rand));
            }
            System.out.println("Test case nr " +n+ "...");
        }

        for (int n=51; n<=101; n++) {
            float rand = new Random().nextInt(1000);
            System.out.println(rand);
            if (rand >= 33) {
                assertTrue(test.CheckPriceInput("Suv", rand));
            } else {
                assertFalse(test.CheckPriceInput("Suv", rand));
            }
            System.out.println("Test case nr " +n+ "...");
        }

        for (int n=102; n<=152; n++) {
            float rand = new Random().nextInt(1000);
            System.out.println(rand);
            if (rand <= 30) {
                assertTrue(test.CheckPriceInput("Standard", rand));
            } else {
                assertFalse(test.CheckPriceInput("Standard", rand));
            }
            System.out.println("Test case nr " +n+ "...");
        }

    }

    @Test
    void colorInputCheck() {
        String input = "Black";
        assertTrue(test.ColorInputCheck(input));
        System.out.println("assertTrue...");
        input = "saaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaafafasfasfasfasfadsafsafasfasfafasfasfasfasfasfdasdasdasda";
        assertFalse(test.ColorInputCheck(input));
        System.out.println("assertFalse...");

    }
}