package org.cloud.demo0baseframework.model.bean;

/**
 * @author d05660ddw
 * @version 1.0 2016/9/22
 */

public class Rating {
    private float max;
    private float average;
    private String starts;
    private float min;

    public float getMax() {
        return max;
    }

    public void setMax(float max) {
        this.max = max;
    }

    public float getAverage() {
        return average;
    }

    public void setAverage(float average) {
        this.average = average;
    }

    public String getStarts() {
        return starts;
    }

    public void setStarts(String starts) {
        this.starts = starts;
    }

    public float getMin() {
        return min;
    }

    public void setMin(float min) {
        this.min = min;
    }
}
