package org.example.function;

import java.util.function.Predicate;

public class Demo2 {
    public static void main(String[] args) {
        Predicate<String> predicate1 = new Predicate<String>() {
            @Override
            public boolean test(String str) {
                return str.isEmpty();
            }
        };

        Predicate<String> predicate2 = str -> {
            return str.isEmpty();
        };

        System.out.println(predicate1.test("456"));
        System.out.println(predicate2.test(""));


    }
}
