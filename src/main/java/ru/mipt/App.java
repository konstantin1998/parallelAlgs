package ru.mipt;

public class App {
    public static void main(String[] args) {
        Object o1 = new Object();
        Object o2 = new Object();
        Object o3 = new Object();
        o1 = o2;
        o2 = o3;
        System.out.println(o1.equals(o2));
    }
}
