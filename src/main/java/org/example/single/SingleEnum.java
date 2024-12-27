package org.example.single;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public enum SingleEnum {

    INSTANCE;

    public SingleEnum getInstance() {
        return INSTANCE;
    }

}
// 至少在做一个普通的JVM时候，jdk源码没有被修改的时候，枚举就是安全的！

class Demo {
    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Constructor<SingleEnum> declaredConstructor = SingleEnum.class.getDeclaredConstructor(String.class, int.class);
        declaredConstructor.setAccessible(true);

        // throw new IllegalArgumentException("Cannot reflectively create enum objects");
        SingleEnum singleEnum1 = declaredConstructor.newInstance();
        SingleEnum singleEnum2 = declaredConstructor.newInstance();
        System.out.println(singleEnum1.hashCode());
        System.out.println(singleEnum2.hashCode());

        // 这里面没有无参构造！ JVM 才是王道！
        // "main" java.lang.NoSuchMethodException: com.coding.single.SingleEnum.<init>()
    }
}