package org.example.function;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class Demo3 {
    public static void main(String[] args) {
        Supplier<String> supplier1 = new Supplier<String>() {
            // 语法糖
            @Override
            public String get() {
                return "《hello，spring》";
            }
        };

        Supplier<String> supplier2 = () -> {
            return "《hello，spring》";
        };
        Consumer<String> consumer1 = s -> {
            System.out.println(s);
        };

        Consumer<String> consumer2 = new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println(s);
            }
        };

        consumer1.accept(supplier1.get());
        consumer2.accept(supplier2.get());

    }
}
