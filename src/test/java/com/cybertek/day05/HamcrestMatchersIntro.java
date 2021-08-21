package com.cybertek.day05;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class HamcrestMatchersIntro {

    @Test
    public void test01() {
        assertThat(5+5, is(10));
        assertThat(5+5, is(equalTo(10)));

        //matchers has 2 overloaded version
        //first that accept actual value
        //second taht accept another matchers
        //below examples is method is accepting another matchers equal to make it readable

        assertThat(5+5 , not(9));
        assertThat(5+5 , is(not(9)));
        assertThat(5+5 , is(not(equalTo(9))));
        //number comparison
        //greaterThan()
        //greaterThanOrEqualTo()
        //lessThan()
        //lessThanOrEqualTo()

        assertThat(5+5 , is(greaterThan(9)));
    }
    @Test
    public void test02() {
        String text = "B22 is learning Hamcrest";

        assertThat(text , is("B22 is learning Hamcrest"));
        assertThat(text , equalTo("B22 is learning Hamcrest"));
        assertThat(text , is(equalTo("B22 is learning Hamcrest")));

        assertThat(text, startsWith("B22"));
        //startWith
        assertThat(text , startsWithIgnoringCase("b22"));
        //endsWith
        assertThat(text , endsWith("crest"));
        assertThat(text , endsWithIgnoringCase("hamcrest"));
        //check if text contains string 'learning'
        assertThat(text , containsString("learning"));

        //empty or blank String
        String str = " ";
        assertThat(str, blankString());
        assertThat(str.trim(), emptyString());
    }

    @Test
    public void test03() {
        List<Integer> listOfNumbers = Arrays.asList(2,4,78,9,23,10,55,32,95,73,1);

        //check size of the list
        assertThat(listOfNumbers, hasSize(11));
        //check is list hasItem 23
        assertThat(listOfNumbers , hasItem(23));
        //check if list hasNotItem 77
        assertThat(listOfNumbers , not(hasItem(77)));
        //check if list hasItems (1,2,10)
        assertThat(listOfNumbers , hasItems(1,2,10));
        //check if all numbers are > 0
        assertThat(listOfNumbers , everyItem(is(greaterThan(0))));
        assertThat(listOfNumbers , everyItem(greaterThan(0)));


    }




}
