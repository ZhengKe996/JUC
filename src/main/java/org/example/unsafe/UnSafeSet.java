package org.example.unsafe;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class UnSafeSet {
    public static void main(String[] args) {

//        Set<String> set = new HashSet<>();
//        Set<String> set = new CopyOnWriteArraySet();
        Set<String> set = Collections.synchronizedSet(new HashSet<>());
        for (int i = 1; i <= 30; i++) {
            new Thread(() -> {
                set.add(UUID.randomUUID().toString().substring(0, 3));
                System.out.println(set);
            }, String.valueOf(i)).start();
        }
    }
}
