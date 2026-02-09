package org.example.lecturefive.functionals;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

public class Program4 {
    static double execDoubleBiFunction(BiFunction <Double, Double, Double> function, double a, double b) {
        return function.apply(a, b);
    }

    static String execStringSupplier(Supplier<String> supplier) {
        return supplier.get();
    }

    static int execIntBiFunction(BiFunction<String, String, Integer> function, String a, String b) {
        return function.apply(a, b);
    }

    static int execIntFunction(Function<String, Integer> function, String str) {
        return function.apply(str);
    }

    public static void main(String[] args) {

        double result1 = execDoubleBiFunction((x,y) -> Math.pow(x,y), 2.0, 3.0);
        System.out.println(result1);

        double result2 = execDoubleBiFunction(Math::pow, 2.0, 3.0);
        System.out.println(result2);

        String result3 = execStringSupplier(() -> {return new String(); });
        System.out.println(result3);

        String result4 = execStringSupplier(String::new);
        System.out.println(result4);

        int result5 = execIntBiFunction((x, y) -> x.compareToIgnoreCase(y), "Anna", "ANNA");
        System.out.println(result5);

        int result6 = execIntBiFunction(String::compareToIgnoreCase, "Anna", "ANNA");
        System.out.println(result6);

        int result7 = execIntFunction("Anna"::compareToIgnoreCase, "ANNA");
        System.out.println(result7);

    }
}
