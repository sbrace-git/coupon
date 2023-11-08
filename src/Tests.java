package src;

import src.model.Result;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Tests {

    public static void main(String[] args) throws Exception {
        List<Method> methodList = Arrays.asList(Tests.class.getDeclaredMethods());
        methodList.sort(Comparator.comparing(Method::getName));
        for (Method method : methodList) {
            if (method.isAnnotationPresent(Test.class)) {
                method.invoke(null, null);
            }
        }
    }

    private static void test(int[] goodPrices, int[] coupons, long expectSum, long expectCount) throws Exception {
        Result result = Main.process(goodPrices, coupons);
        Long sum = result.getSum();
        Long count = result.getCount();
        if (sum != expectSum || expectCount != count) {
            System.out.printf("expectSum = %d, sum = %d, expectCount = %d, count = %d%n", expectSum, sum, expectCount, count);
            throw new Exception("error");
        }
    }

    @Retention(RetentionPolicy.RUNTIME)
    private @interface Test {
    }

    @Test
    static void test1() throws Exception {
        int[] goodPrices = {1, 10, 9, 20, 5, 10};
        int[] coupons = {9, 8, 2, 3, 8, 12, 19};
        long expectSum = 49;
        long expectCount = 6;
        test(goodPrices, coupons, expectSum, expectCount);
    }

    @Test
    static void test2() throws Exception {
        int[] goodPrices = {3, 4};
        int[] coupons = {3, 1, 2, 1, 1, 1};
        long expectSum = 7;
        long expectCount = 5;
        test(goodPrices, coupons, expectSum, expectCount);
    }

    @Test
    static void test3() throws Exception {
        int[] goodPrices = {3, 4};
        int[] coupons = {3, 2, 2};
        long expectSum = 7;
        long expectCount = 3;
        test(goodPrices, coupons, expectSum, expectCount);
    }

    @Test
    static void test4() throws Exception {
        int[] goodPrices = {4};
        int[] coupons = {3, 2, 2};
        long expectSum = 4;
        long expectCount = 2;
        test(goodPrices, coupons, expectSum, expectCount);
    }

    @Test
    static void test5() throws Exception {
        int[] goodPrices = {5, 8};
        int[] coupons = {1, 2, 3, 4, 5};
        long expectSum = 13;
        long expectCount = 4;
        test(goodPrices, coupons, expectSum, expectCount);
    }

    @Test
    static void test6() throws Exception {
        int[] goodPrices = {5, 7};
        int[] coupons = {2, 3, 4, 5};
        long expectSum = 11;
        long expectCount = 3;
        test(goodPrices, coupons, expectSum, expectCount);
    }
}
