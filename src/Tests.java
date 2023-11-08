package src;

import src.model.Coupon;
import src.model.Good;
import src.model.Result;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Method;
import java.util.*;

public class Tests {

    public static void main(String[] args) throws Exception {
        List<Method> methodList = Arrays.asList(Tests.class.getDeclaredMethods());
        methodList.sort(Comparator.comparing(Method::getName));
        for (Method method : methodList) {
            if (method.isAnnotationPresent(Test.class)) {
                method.invoke(null, null);
            }
        }
        System.out.println("Tests passed.");
    }

    private static void assertEquals(Object expect, Object actual) throws Exception {
        if (!Objects.equals(expect, actual)) {
            System.out.println("expect: " + expect);
            System.out.println("actual: " + actual);
            throw new Exception("not equals");
        }
    }

    private static void test(int[] goodPrices, int[] coupons, long expectSum, long expectCount) throws Exception {
        Result result = Main.process(goodPrices, coupons);
        result.print();
        Long sum = result.getSum();
        Long count = result.getCount();
        assertEquals(expectSum, sum);
        assertEquals(expectCount, count);
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

    private static final Coupon coupon1 = new Coupon(1);
    private static final Coupon coupon2 = new Coupon(2);
    private static final Coupon coupon3 = new Coupon(3);
    private static final Coupon coupon4 = new Coupon(4);

    @Test
    static void test7() throws Exception {
        Result result1 = new Result(Arrays.asList(
                new Good(10, Collections.singletonList(coupon1)),
                new Good(10, Collections.singletonList(coupon2))
        ), Collections.emptyList());

        Result result2 = new Result(Arrays.asList(
                new Good(10, Collections.singletonList(coupon4)),
                new Good(10, Collections.emptyList())
        ), Collections.emptyList());

        Result pick = Result.pick(result1, result2);
        assertEquals(result2, pick);
    }

    @Test
    static void test8() throws Exception {
        Result result1 = new Result(Arrays.asList(
                new Good(10, Arrays.asList(coupon1, coupon2)),
                new Good(10, Collections.emptyList())
        ), Collections.emptyList());

        Result result2 = new Result(Arrays.asList(
                new Good(10, Collections.singletonList(coupon3)),
                new Good(10, Collections.emptyList())
        ), Collections.emptyList());

        Result pick = Result.pick(result1, result2);
        assertEquals(result1, pick);
    }
}
