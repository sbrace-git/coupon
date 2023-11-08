package src.model;

import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.stream.Collectors;

public class Result {

    private Long sum;
    private Long count;
    private List<Good> goodList;
    private List<Coupon> unUsedCouponList;

    public Result(List<Good> goodList, List<Coupon> unUsedCouponList) {
        this.goodList = goodList;
        this.unUsedCouponList = unUsedCouponList;
        init();
    }

    private void init() {
        IntSummaryStatistics intSummaryStatistics = goodList.stream().map(Good::getCouponList).flatMap(List::stream)
                .mapToInt(Coupon::getAmount).summaryStatistics();
        sum = intSummaryStatistics.getSum();
        count = intSummaryStatistics.getCount();
    }

    public void print() {
        System.out.printf("优惠组合 : %s%n", goodList.stream().map(Good::toString).collect(Collectors.joining(",\n", "\n", "")));
        System.out.printf("优惠金额 : %d%n", sum);
        System.out.printf("使用优惠券数量 : %d%n", count);
        System.out.printf("未使用的优惠券 : %s%n%n", unUsedCouponList);
    }

    public Long getSum() {
        return sum;
    }

    public Long getCount() {
        return count;
    }

}
