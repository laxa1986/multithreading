package laxa.multithreading;

import java.util.WeakHashMap;

/**
 * Author: Chekulaev Alexey
 * Date: 02.01.2015
 */
public class Test {
    static WeakHashMap map = new WeakHashMap();

    public static void main(String[] args) {
        map.put(1, "a");

        System.gc();
        System.out.println(map.size());

        System.gc();
        System.out.println(map.size());

        System.gc();
        System.out.println(map.size());
    }
}