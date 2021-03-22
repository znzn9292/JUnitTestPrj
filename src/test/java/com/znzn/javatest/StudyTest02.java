package com.znzn.javatest;


import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.RegisterExtension;

//@ExtendWith(FindSlowTestExtension.class)
class StudyTest02 {

    @RegisterExtension
    static FindSlowTestExtension findSlowTestExtension = new FindSlowTestExtension(1000L);

    @DisplayName("Order 01")
    @Test
    void create_order_02() {
        System.out.println("order 01");
    }

    @DisplayName("Order 02")
    @Test
    void create_order_01() throws InterruptedException {
        Thread.sleep(1005L);
        System.out.println("order 02");
    }

    @DisplayName("Order 03")
    @Test
    void create_order_03() {
        System.out.println("order 03");
    }
}