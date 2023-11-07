package src;

import src.model.Coupon;

import java.util.List;

public class Result {

    private Long sum;
    private Long count;
    private List<Coupon> unUsedCouponList;

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

    public List<Coupon> getUnUsedCouponList() {
        return unUsedCouponList;
    }

    public void setUnUsedCouponList(List<Coupon> unUsedCouponList) {
        this.unUsedCouponList = unUsedCouponList;
    }

    public Result(Long sum, Long count, List<Coupon> unUsedCouponList) {
        this.sum = sum;
        this.count = count;
        this.unUsedCouponList = unUsedCouponList;
    }
}
