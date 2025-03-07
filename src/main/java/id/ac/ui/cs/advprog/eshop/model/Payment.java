package id.ac.ui.cs.advprog.eshop.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Builder
@Getter
public class Payment {
    private String id;
    private String method;
    private String status;
    private Map<String, String> paymentData;
    private Order order;

    public Payment(String id, Order order, String method, Map<String, String> paymentData) {
        // Implementation will be added later
    }

    public void setStatus(String status) {
        // Implementation will be added later
    }
}