import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        int[] goodPrices = {1, 10, 9, 20, 5, 10};
        int[] coupons = {9, 8, 2, 3, 8, 12, 19};

//        int[] goodPrices = {3, 4};
//        int[] coupons = {3, 1, 2, 1, 1, 1};

//        int[] goodPrices = {3, 4};
//        int[] coupons = {3, 2, 2};

//        int[] goodPrices = {3, 3, 3, 10};
//        int[] coupons = {3, 3, 3, 5};

//        int[] goodPrices = {4};
//        int[] coupons = {3, 2, 2};

        List<Good> goodList = Arrays.stream(goodPrices).sorted()
                .mapToObj(Good::new).collect(Collectors.toList());
        List<Coupon> unUsedCouponsList = Arrays.stream(coupons).boxed().sorted(Comparator.reverseOrder())
                .map(Coupon::new).collect(Collectors.toList());

        for (Good good : goodList) {
            int couponAmountMax = 0;
            for (int outIndex = 0; outIndex < unUsedCouponsList.size(); outIndex++) {
                int price = good.price;
                List<Coupon> couponList = new ArrayList<>();
                int couponAmountSum = 0;
                for (int inIndex = outIndex; inIndex < unUsedCouponsList.size(); inIndex++) {
                    Coupon coupon = unUsedCouponsList.get(inIndex);
                    if (coupon.amount <= price) {
                        price -= coupon.amount;
                        couponAmountSum += coupon.amount;
                        couponList.add(coupon);
                    }
                }
                if (couponAmountSum >= couponAmountMax) {
                    couponAmountMax = couponAmountSum;
                    good.couponList = couponList;
                }
            }
            good.couponList.forEach(unUsedCouponsList::remove);
        }

        System.out.println("优惠组合 : " + goodList.stream().map(Good::toString).collect(Collectors.joining(", ")));
        IntSummaryStatistics intSummaryStatistics = goodList.stream().map(Good::getCouponList).flatMap(List::stream)
                .mapToInt(Coupon::getAmount).summaryStatistics();
        System.out.println("优惠金额 : " + intSummaryStatistics.getSum());
        System.out.println("使用优惠卷数量 : " + intSummaryStatistics.getCount());
        System.out.println("未使用的优惠卷 : " + unUsedCouponsList);
    }

    static class Good {
        int price;
        List<Coupon> couponList = new ArrayList<>();

        public List<Coupon> getCouponList() {
            return couponList;
        }

        Good(int price) {
            this.price = price;
        }

        @Override
        public String toString() {
            return String.format("[%d]-%s", price, couponList);
        }
    }

    static class Coupon {
        int amount;

        public int getAmount() {
            return amount;
        }

        Coupon(int amount) {
            this.amount = amount;
        }

        @Override
        public String toString() {
            return String.valueOf(amount);
        }
    }
}
