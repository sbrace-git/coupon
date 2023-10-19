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


        List<Good> goodList = Arrays.stream(goodPrices).boxed().sorted()
                .map(Good::new).collect(Collectors.toList());
        List<Coupon> unusedCouponsList = Arrays.stream(coupons).boxed().sorted(Comparator.reverseOrder())
                .map(Coupon::new).collect(Collectors.toList());

        for (Good good : goodList) {
            int couponAmountMax = 0;
            List<Coupon> couponListMax = new ArrayList<>();
            for (int outIndex = 0; outIndex < unusedCouponsList.size(); outIndex++) {
                int remaining = good.price;
                List<Coupon> couponList = new ArrayList<>();
                int couponAmountSum = 0;
                for (int inIndex = outIndex; inIndex < unusedCouponsList.size(); inIndex++) {
                    Coupon coupon = unusedCouponsList.get(inIndex);
                    if (coupon.amount <= remaining) {
                        remaining -= coupon.amount;
                        couponAmountSum += coupon.amount;
                        couponList.add(coupon);
                    }
                }
                if (couponAmountSum >= couponAmountMax) {
                    couponAmountMax = couponAmountSum;
                    couponListMax = couponList;
                }
            }
            good.couponList = couponListMax;
            couponListMax.forEach(unusedCouponsList::remove);
        }

        for (int i = 0; i < goodList.size(); i++) {
            Good good = goodList.get(i);
            for (Coupon coupon : good.couponList) {
                for (Coupon unusedCoupon : unusedCouponsList) {
                    if (unusedCoupon.amount <= coupon.remaining) {
                        coupon.remaining -= unusedCoupon.amount;
                        coupon.subCouponList.add(unusedCoupon);
                    }
                }
                if (coupon.remaining == 0 && coupon.subCouponList.size() != 1) {
                    coupon.subCouponList.forEach(unusedCouponsList::remove);
                    unusedCouponsList.add(coupon);
                    good.couponList.remove(coupon);
                    good.couponList.addAll(coupon.subCouponList);
                    i--;
                    break;
                }
            }
        }
        System.out.println("优惠组合 : " + goodList.stream().map(Good::toString).collect(Collectors.joining(", ")));
        IntSummaryStatistics intSummaryStatistics = goodList.stream().map(Good::getCouponList).flatMap(List::stream)
                .mapToInt(Coupon::getAmount).summaryStatistics();
        System.out.println("优惠金额 : " + intSummaryStatistics.getSum());
        System.out.println("使用优惠卷数量 : " + intSummaryStatistics.getCount());
        System.out.println("未使用的优惠卷 : " + unusedCouponsList);
    }

    static class Good {
        int price;
        List<Coupon> couponList;

        public List<Coupon> getCouponList() {
            return couponList;
        }

        Good(int price) {
            this.price = price;
            this.couponList = new ArrayList<>();
        }

        @Override
        public String toString() {
            return String.format("[%d]-%s", price, couponList);
        }
    }

    static class Coupon {
        int amount;
        int remaining;
        List<Coupon> subCouponList;

        public int getAmount() {
            return amount;
        }

        Coupon(int amount) {
            this.amount = amount;
            this.remaining = amount;
            this.subCouponList = new ArrayList<>();
        }

        @Override
        public String toString() {
            return String.valueOf(amount);
        }

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof Coupon) {
                return amount == ((Coupon) obj).amount;
            }
            return false;
        }

        @Override
        public int hashCode() {
            return amount;
        }
    }
}