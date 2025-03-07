package id.ac.ui.cs.advprog.eshop.enums;

public enum PaymentMethod {
    VOUCHER,
    CASH_ON_DELIVERY;

    public String getValue() {
        return this.name();
    }

    public static boolean contains(String method) {
        for (PaymentMethod paymentMethod : PaymentMethod.values()) {
            if (paymentMethod.getValue().equals(method)) {
                return true;
            }
        }
        return false;
    }
}