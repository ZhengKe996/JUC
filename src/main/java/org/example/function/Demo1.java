package org.example.function;

import java.util.function.Function;

public class Demo1 {
    public static void main(String[] args) {

        Function<String, Integer> function1 = new Function<String, Integer>() {
            @Override
            public Integer apply(String str) {
                return str.length();
            }
        };

        // (参数)->{方法体}
        Function<String, Integer> function2 = (str) -> {
            return str.length();
        };
        System.out.println(function1.apply("a45645646"));
        System.out.println(function2.apply("a45645646"));
    }
}
