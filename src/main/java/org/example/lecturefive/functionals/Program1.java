package org.example.lecturefive.functionals;

import java.util.Arrays;
import java.util.Comparator;

public class Program1 {
    public static void main(String[] args) {

        String[] words = new String[]{"anna", "bo", "cecilia", "dan", "erika"};

        //Anonymous class implementation of the Comparator interface.
        Comparator<String> comparator1 = new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                if (o1.length() < o2.length()) {
                    return -1;
                } else if (o1.length() > o2.length()) {
                    return 1;
                } else {
                    return 0;
                }
            }
        };

        // Lambda expression implementation of the Comparator interface.
        Comparator<String> comparator2 = (String o1, String o2) -> {
            if (o1.length() < o2.length()) {
                return -1;
            } else if (o1.length() > o2.length()) {
                return 1;
            } else {
                return 0;
            }
        };

        // Lambda expression with type inference.
        Comparator<String> comparator3 = (o1, o2) -> {
            if (o1.length() < o2.length()) {
                return -1;
            } else if (o1.length() > o2.length()) {
                return 1;
            } else {
                return 0;
            }
        };

        Arrays.sort(words, comparator1);
        for (int i = 0; i < words.length; i++) {
            System.out.println(words[i] + " ");
        }

        Arrays.sort(words, (o1, o2) ->
                Integer.compare(o1.length(), o2.length())
        );
        for (int i = 0; i < words.length; i++) {
            System.out.println(words[i] + " ");
        }

    }


}
