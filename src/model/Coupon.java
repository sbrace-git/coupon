package src.model;

public class Coupon {
    int amount;

    public int getAmount() {
        return amount;
    }

    public Coupon(int amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return String.valueOf(amount);
    }
}
