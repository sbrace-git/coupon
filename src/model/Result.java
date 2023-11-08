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
        System.out.println("优惠组合 : " + goodList.stream().map(Good::toString).collect(Collectors.joining(", ")));
        System.out.println("优惠金额 : " + sum);
        System.out.println("使用优惠卷数量 : " + count);
        System.out.printf("未使用的优惠卷 : %s%n%n", unUsedCouponList);
    }

    public Long getSum() {
        return sum;
    }

    public void setSum(Long sum) {
        this.sum = sum;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public List<Good> getGoodList() {
        return goodList;
    }

    public void setGoodList(List<Good> goodList) {
        this.goodList = goodList;
    }

    public List<Coupon> getUnUsedCouponList() {
        return unUsedCouponList;
    }

    public void setUnUsedCouponList(List<Coupon> unUsedCouponList) {
        this.unUsedCouponList = unUsedCouponList;
    }

}
