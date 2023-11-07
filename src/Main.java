package src;

import src.model.Coupon;
import src.model.Good;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public IntSummaryStatistics process(int[] goodPrices, int[] coupons) {
        List<Good> goodList = Arrays.stream(goodPrices).sorted()
                .mapToObj(Good::new).collect(Collectors.toList());
        List<Coupon> unUsedCouponsList = Arrays.stream(coupons).boxed().sorted(Comparator.reverseOrder())
                .map(Coupon::new).collect(Collectors.toList());
        IntSummaryStatistics result = goodSetCouponList(goodList, unUsedCouponsList);

        List<Good> goodList2 = Arrays.stream(goodPrices).boxed().sorted(Comparator.reverseOrder())
                .map(Good::new).collect(Collectors.toList());
        List<Coupon> unUsedCouponsList2 = Arrays.stream(coupons).boxed().sorted(Comparator.reverseOrder())
                .map(Coupon::new).collect(Collectors.toList());
        IntSummaryStatistics result2 = goodSetCouponList(goodList2, unUsedCouponsList2);

        if (result2.getSum() >= result.getSum() && result2.getCount() >= result.getCount()) {
            goodList = goodList2;
            unUsedCouponsList = unUsedCouponsList2;
            result = result2;
        }
        System.out.println("优惠组合 : " + goodList.stream().map(Good::toString).collect(Collectors.joining(", ")));
        System.out.println("优惠金额 : " + result.getSum());
        System.out.println("使用优惠卷数量 : " + result.getCount());
        System.out.printf("未使用的优惠卷 : %s%n%n", unUsedCouponsList);
        return result;
    }

    private IntSummaryStatistics goodSetCouponList(List<Good> goodList, List<Coupon> unUsedCouponsList) {
        for (Good good : goodList) {
            int couponAmountMax = 0;
            for (int outIndex = 0; outIndex < unUsedCouponsList.size(); outIndex++) {
                int price = good.getPrice();
                List<Coupon> couponList = new ArrayList<>();
                int couponAmountSum = 0;
                for (int inIndex = outIndex; inIndex < unUsedCouponsList.size(); inIndex++) {
                    Coupon coupon = unUsedCouponsList.get(inIndex);
                    if (coupon.getAmount() <= price) {
                        price -= coupon.getAmount();
                        couponAmountSum += coupon.getAmount();
                        couponList.add(coupon);
                    }
                }
                if (couponAmountSum > couponAmountMax) {
                    couponAmountMax = couponAmountSum;
                    good.setCouponList(couponList);
                } else if (couponAmountSum == couponAmountMax && couponList.size() > good.getCouponList().size()) {
                    good.setCouponList(couponList);
                }
            }
            good.getCouponList().forEach(unUsedCouponsList::remove);
        }
        IntSummaryStatistics intSummaryStatistics = goodList.stream().map(Good::getCouponList).flatMap(List::stream)
                .mapToInt(Coupon::getAmount).summaryStatistics();
        return intSummaryStatistics;
    }
}
