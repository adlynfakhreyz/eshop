package id.ac.ui.cs.advprog.eshop.model;

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
        // Implementation will be added later
    }

    public void setStatus(String status) {
        // Implementation will be added later
    }
}