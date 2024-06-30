package org.aseguradora.entity.dto;

import java.math.BigDecimal;

public class PaymentRequest {

    private Long policyId;
    private double amount;
    private String currencyCode;
    private String description;

    public Long getPolicyId() {
        return policyId;
    }

    public void setPolicyId(Long policyId) {
        this.policyId = policyId;
    }

    public BigDecimal getAmount() {
        return BigDecimal.valueOf(amount);
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
