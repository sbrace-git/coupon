package src;

import java.util.IntSummaryStatistics;

public class Test {
    private static final Main main = new Main();

    public static void main(String[] args) throws Exception {
        test1();
        test2();
        test3();
        test4();
        test5();
        test6();
    }

    static void test(int[] goodPrices, int[] coupons, long expectSum, long expectCount) throws Exception {
        IntSummaryStatistics intSummaryStatistics = main.process(goodPrices, coupons);
        long sum = intSummaryStatistics.getSum();
        long count = intSummaryStatistics.getCount();
        if (sum != expectSum || expectCount != count) {
            System.out.printf("expectSum = %d, sum = %d, expectCount = %d, count = %d%n", expectSum, sum, expectCount, count);
            throw new Exception("error");
        }
    }

    static void test1() throws Exception {
        int[] goodPrices = {1, 10, 9, 20, 5, 10};
        int[] coupons = {9, 8, 2, 3, 8, 12, 19};
        long expectSum = 49;
        long expectCount = 6;
        test(goodPrices, coupons, expectSum, expectCount);
    }

    static void test2() throws Exception {
        int[] goodPrices = {3, 4};
        int[] coupons = {3, 1, 2, 1, 1, 1};
        long expectSum = 7;
        long expectCount = 5;
        test(goodPrices, coupons, expectSum, expectCount);
    }

    static void test3() throws Exception {
        int[] goodPrices = {3, 4};
        int[] coupons = {3, 2, 2};
        long expectSum = 7;
        long expectCount = 3;
        test(goodPrices, coupons, expectSum, expectCount);
    }

    static void test4() throws Exception {
        int[] goodPrices = {4};
        int[] coupons = {3, 2, 2};
        long expectSum = 4;
        long expectCount = 2;
        test(goodPrices, coupons, expectSum, expectCount);
    }

    static void test5() throws Exception {
        int[] goodPrices = {5, 8};
        int[] coupons = {1, 2, 3, 4, 5};
        long expectSum = 13;
        long expectCount = 4;
        test(goodPrices, coupons, expectSum, expectCount);
    }

    static void test6() throws Exception {
        int[] goodPrices = {5, 7};
        int[] coupons = {2, 3, 4, 5};
        long expectSum = 11;
        long expectCount = 3;
        test(goodPrices, coupons, expectSum, expectCount);
    }
}
