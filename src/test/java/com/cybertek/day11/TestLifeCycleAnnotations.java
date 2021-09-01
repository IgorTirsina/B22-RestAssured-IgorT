package com.cybertek.day11;

import org.junit.jupiter.api.*;

public class TestLifeCycleAnnotations {

    @BeforeAll
    public static void init() {
        System.out.println("Before all is running!");
    }

    @AfterAll
    public static void close() {
        System.out.println("After all is running");
    }

    @BeforeEach
    public void initEach() {
        System.out.println("\tBefore each is running");
    }

    @AfterEach
    public void closeEach() {
        System.out.println("\tAfter each is running");
    }

    @Test
    public void test1() {
        System.out.println("test 1 running");
    }

    @Disabled
    @Test
    public void test2() {
        System.out.println("test 2 running");
    }
}
