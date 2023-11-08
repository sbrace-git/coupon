package src;

import src.model.Coupon;
import src.model.Good;
import src.model.Result;

import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        int[] goodPrices = new int[]{5, 7};
        int[] coupons = new int[]{2, 3, 4, 5};

        process(goodPrices, coupons);
    }

    public static Result process(int[] goodPrices, int[] coupons) {
        List<Good> goodList = Arrays.stream(goodPrices).sorted()
                .mapToObj(Good::new).collect(Collectors.toList());
        List<Good> goodListReverse = Arrays.stream(goodPrices).boxed().sorted(Comparator.reverseOrder())
                .map(Good::new).collect(Collectors.toList());
        List<Coupon> unUsedCouponsList = Arrays.stream(coupons).boxed().sorted(Comparator.reverseOrder())
                .map(Coupon::new).collect(Collectors.toList());

        Result result1 = getResult(goodList, new ArrayList<>(unUsedCouponsList));
        Result result2 = getResult(goodListReverse, unUsedCouponsList);

        Result finalResult = result1;
        if (result2.getSum() >= result1.getSum() && result2.getCount() >= result1.getCount()) {
            finalResult = result2;
        }

        finalResult.print();
        return finalResult;
    }

    private static Result getResult(List<Good> goodList, List<Coupon> unUsedCouponsList) {
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
        return new Result(goodList, unUsedCouponsList);
    }
}
