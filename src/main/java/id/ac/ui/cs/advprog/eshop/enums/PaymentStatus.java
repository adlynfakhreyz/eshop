package id.ac.ui.cs.advprog.eshop.enums;

public enum PaymentStatus {
    WAITING,
    SUCCESS,
    REJECTED;

    public String getValue() {
        return this.name();
    }

    public static boolean contains(String status) {
        for (PaymentStatus paymentStatus : PaymentStatus.values()) {
            if (paymentStatus.getValue().equals(status)) {
                return true;
            }
        }
        return false;
    }
}