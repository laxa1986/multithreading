package laxa.multithreading;

import laxa.multithreading.task.moneytransfer.model.Account;

import java.util.WeakHashMap;

/**
 * Author: Chekulaev Alexey
 * Date: 02.01.2015
 */
public class Test {
    static WeakHashMap map = new WeakHashMap();

    static Account account;

    public static void main(String[] args) {
        account = new Account(7777);

        map.put(account.getId(), "a");

        System.gc();
        System.out.println(map.size());

        System.gc();
        System.out.println(map.size());

        System.gc();
        System.out.println(map.size());
    }
}