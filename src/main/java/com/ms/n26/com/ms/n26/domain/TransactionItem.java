package com.ms.n26.com.ms.n26.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by mshetkar on 5/9/2017.
 */
public class TransactionItem {
    @JsonProperty("amount")
    private Double amount;

    @JsonProperty("timestamp")
    private Long timestamp;

    public Double getAmount() { return amount;}

    public void setAmount(Double amount) { this.amount = amount;}

    public Long getTimestamp() { return timestamp;}

    public void setTimestamp(Long timestamp) { this.timestamp = timestamp;}

}
