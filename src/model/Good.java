package src.model;

import java.util.ArrayList;
import java.util.List;

public class Good {
    Integer price;
    private List<Coupon> couponList;

    public Integer getPrice() {
        return price;
    }

    public List<Coupon> getCouponList() {
        return couponList;
    }

    public void setCouponList(List<Coupon> couponList) {
        this.couponList = couponList;
    }

    public Good(Integer price) {
        this.price = price;
        this.couponList = new ArrayList<>();
    }

    public Good(Integer price, List<Coupon> couponList) {
        this.price = price;
        this.couponList = couponList;
    }

    @Override
    public String toString() {
        return String.format("[%d]-%s", price, couponList);
    }

}
