package org.example.single;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public class LazyMan {
    private static boolean asdalskdalskdlasd = false;

    private LazyMan() {
        synchronized (LazyMan.class) {
            if (asdalskdalskdlasd == false) {
                asdalskdalskdlasd = true;
            } else {
                throw new RuntimeException("不要试图破坏我的单例模式！");
            }
        }
    }

    private volatile static LazyMan lazyMan;

    // DCL
    public static LazyMan getInstance() {
        if (lazyMan == null) {
            synchronized (LazyMan.class) {
                if (lazyMan == null) {
                    lazyMan = new LazyMan(); // 请你谈谈这个操作！它不是原子性的
                    // java创建一个对象
                    // 1、分配内存空间
                    // 2、执行构造方法，创建对象
                    // 3、将对象指向空间

                }
            }
        }
        return lazyMan;
    }

    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchFieldException {
        // 反射安全吗？ 官方推荐我们单例真的是 DCL懒汉式吗？
        // LazyMan lazyMan1 = LazyMan.getInstance();
        Constructor<LazyMan> declaredConstructor = LazyMan.class.getDeclaredConstructor(null);
        declaredConstructor.setAccessible(true);
        LazyMan lazyMan1 = declaredConstructor.newInstance();// 创建对象

        Field asdalskdalskdlasd = LazyMan.class.getDeclaredField("asdalskdalskdlasd");
        asdalskdalskdlasd.setAccessible(true);
        asdalskdalskdlasd.set(lazyMan1, false);
        LazyMan lazyMan2 = declaredConstructor.newInstance();// 创建对象


        System.out.println(lazyMan1.hashCode());
        System.out.println(lazyMan2.hashCode());
    }
}
