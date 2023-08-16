package com.urise.webapp;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MainStream {
    public static void main(String[] args) {
        int[] array = new int[]{1, 9, 3, 5, 5, 3, 8, 3, 4, 6};
        System.out.println(minValue(array));
        List<Integer> integers = Arrays.stream(array).boxed()
                .collect(Collectors.toList());
        System.out.println(oddEven(integers));
    }

    private static int minValue(int[] values) {
        return Arrays.stream(values)
                .distinct()
                .sorted()
                .reduce(0, (x, y) -> 10 * x + y);
    }

    private static List<Integer> oddEven(List<Integer> integers) {
        int delimiter = integers.stream().mapToInt(Integer::intValue).sum() % 2;
        System.out.println(delimiter);
        return integers.stream().filter(n -> n % 2 != delimiter).collect(Collectors.toList());
    }
}
