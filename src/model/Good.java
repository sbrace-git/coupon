package src.model;

import java.util.ArrayList;
import java.util.List;

public class Good {
    int price;
    private List<Coupon> couponList;
    private List<Coupon> alternativeCouponList;

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public List<Coupon> getCouponList() {
        return couponList;
    }

    public void setCouponList(List<Coupon> couponList) {
        this.couponList = couponList;
    }

    public List<Coupon> getAlternativeCouponList() {
        return alternativeCouponList;
    }

    public void setAlternativeCouponList(List<Coupon> alternativeCouponList) {
        this.alternativeCouponList = alternativeCouponList;
    }

    public Good(int price) {
        this.price = price;
        this.couponList = new ArrayList<>();
        this.alternativeCouponList = new ArrayList<>();
    }

    @Override
    public String toString() {
        return String.format("[%d]-%s", price, couponList);
    }

}
