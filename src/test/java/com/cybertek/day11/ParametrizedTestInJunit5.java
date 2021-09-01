package com.cybertek.day11;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class ParametrizedTestInJunit5 {

    @ParameterizedTest
    @ValueSource(ints = {3,7,8,4,1,9,6,8,3})
    public void testMultipleNumbers(int number) {
        System.out.println("number = "+number);

        Assertions.assertTrue(number > 5);
    }

    @ParameterizedTest
    @ValueSource(strings = {"john", "samantha", "maria", "marcelo"})
    public void testMultiplaNames(String name) {
        System.out.println("name = " + name);
    }



}
