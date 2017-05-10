package com.ms.n26.com.ms.n26.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by mshetkar on 5/9/2017.
 */
public class Statistics {
    @JsonProperty("sum")
    private double sum;

    @JsonProperty("avg")
    private double avg;

    @JsonProperty("max")
    private double max;

    @JsonProperty("min")
    private double min;

    @JsonProperty("count")
    private double count;


    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }

    public double getAvg() {
        return avg;
    }

    public void setAvg(double avg) {
        this.avg = avg;
    }

    public double getMax() {
        return max;
    }

    public void setMax(double max) {
        this.max = max;
    }

    public double getMin() {
        return min;
    }

    public void setMin(double min) {
        this.min = min;
    }

    public double getCount() {
        return count;
    }

    public void setCount(double count) {
        this.count = count;
    }
}
