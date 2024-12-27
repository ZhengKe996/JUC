package org.example.single;

// 饿汉式
public class Hungry {
    private byte[] data = new byte[10 * 1024 * 1024];

    private Hungry() {
    }

    private final static Hungry HUNGRY = new Hungry();

    public static Hungry getInstace() {
        return HUNGRY;
    }
}
