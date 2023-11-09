package src.model;

public class Coupon {
    Integer amount;

    public Integer getAmount() {
        return amount;
    }

    public Coupon(Integer amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return String.valueOf(amount);
    }
}
