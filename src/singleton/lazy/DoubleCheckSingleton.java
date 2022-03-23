package singleton.lazy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class DoubleCheckSingleton {
    private static volatile DoubleCheckSingleton instanse;

    public static DoubleCheckSingleton getInstanse() {
        if (instanse == null) {
            synchronized (DoubleCheckSingleton.class) {
                if (instanse == null) {
                    instanse = new DoubleCheckSingleton();
                }
            }
        }
        int[] a = {1, 2, 3};
        System.out.println(Arrays.stream(a).average().orElse(0));
        return instanse;

    }

    public static void main(String[] args) {
        int[] a = {1, 2, 3};
        System.out.println(Arrays.stream(a).average().orElse(0));
        System.out.println(IntStream.of(1, 2, 3).average().orElse(0));
        System.out.println(Stream.of(1, 2, 3).mapToInt(Integer::valueOf).average().orElse(0));

        Predicate<String> containsZero = s -> s.contains("0");
        System.out.println(containsZero.test("a0"));
        System.out.println(containsZero.test("b1"));

        Supplier<String> supplier = () -> "0";
        System.out.println(supplier.get());

        Consumer<String> consumer = (s) -> s += "!!";
        consumer.accept("");

        Function<String, Integer> f = String::length;
        System.out.println(f.apply("blabla"));

        try {
            System.out.println("as");
        } catch (StackOverflowError error) {
            System.out.println(error.getMessage());
        }
    }
}
