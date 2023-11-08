package src.model;

import java.util.ArrayList;
import java.util.List;

public class Good {
    int price;
    private List<Coupon> couponList;

    public int getPrice() {
        return price;
    }

    public List<Coupon> getCouponList() {
        return couponList;
    }

    public void setCouponList(List<Coupon> couponList) {
        this.couponList = couponList;
    }


    public Good(int price) {
        this.price = price;
        this.couponList = new ArrayList<>();
    }

    public Good(int price, List<Coupon> couponList) {
        this.price = price;
        this.couponList = couponList;
    }

    @Override
    public String toString() {
        return String.format("[%d]-%s", price, couponList);
    }

}
