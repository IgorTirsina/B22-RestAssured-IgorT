package com.cybertek.homework;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class NadirInterview {

    /**
     * methid, parm String, return String,
     * check if param has more then 3 char
     * if yes, return anything else after the 3rd char
     * else, return ghe param
     */

    public static String moreThenThree(String param) {
        if (param.length() <= 3) return param;
        else return param.substring(3);
    }

    public static void main(String[] args) {
        System.out.println("moreThenThree(\"hello!\") = " + moreThenThree("hello!"));
        System.out.println("moreThenThree(\"hi\") = " + moreThenThree("hi"));

        List<String> myList = new ArrayList<>(Arrays.asList(" Hello ", " world ", " !!! "));
        List<String> nullString = new ArrayList<>();
        System.out.println("trimmedList(nullString) = " + trimmedList(nullString));
        System.out.println("myList = " + myList);
        System.out.println("trimmedList(myList) = " + trimmedList(myList));
        System.out.println("barberer(myList) = " + barberer(myList));
    }

    /**
     * accept List<String>, return new List<Strig>
     * trim all the Strings in the list
     * return a new list
     * if the List is null/empty, return null
     */

    public static List<String> trimmedList(List<String> list) {

        if(list == null || list.isEmpty()) return null;
        else {
            for(int i = 0; i < list.size(); i++) {
                list.set(i, list.get(i).trim());
            }
            return list;
        }
    }

    public static List<String> barberer(List<String> gottaShave){
        if(gottaShave.size() == 0) return null;
        return gottaShave.stream().map(String::trim).collect(Collectors.toList() );
    }


}
