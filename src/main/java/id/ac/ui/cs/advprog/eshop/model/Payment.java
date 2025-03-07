package id.ac.ui.cs.advprog.eshop.model;

import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import lombok.Getter;
import java.util.Map;

@Getter
public class Payment {
    private String id;
    private Order order;
    private String method;
    private Map<String, String> paymentData;
    private String status;

    public Payment(String id, Order order, String method, Map<String, String> paymentData) {
        if (method == null || method.isEmpty()) {
            throw new IllegalArgumentException("Payment method cannot be null or empty");
        }

        this.id = id;
        this.order = order;
        this.method = method;
        this.status = PaymentStatus.WAITING.getValue();
        this.paymentData = paymentData;
    }

    public void setStatus(String status) {
        if (PaymentStatus.contains(status)) {
            this.status = status;
        }
    }
}