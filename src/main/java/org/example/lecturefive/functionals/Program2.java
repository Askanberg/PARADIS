package org.example.lecturefive.functionals;

import java.util.Arrays;
import java.util.Comparator;

public class Program2 {

    static void repeatString1(String str, int count) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < count; i++) {
                    System.out.println(str + " ");
                }
            }
        }).start();
    }

    static void repeatString2(String str, int count) {
        new Thread(() -> {
            for (int i = 0; i < count; i++) {
                System.out.println(str + " ");
            }
        }).start();
    }
    public static void main(String[] args) {

        repeatString1("Hello", 3);
        repeatString2("World", 4);

    }


}
