package kr.ac.jj.survey.infrastructure.framework.core.support.progress;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class ServiceProgress implements Serializable {
    private static final long serialVersionUID = 9087772093134787951L;

    private long accessTime;

    private long startTime;
    private double totalValue;
    private double value;
    private String statusCode;
    private String statusText;
    private Boolean completed;

    public ServiceProgress() {
        this.accessTime = System.currentTimeMillis();
    }

    public ServiceProgress init() {
        this.accessTime = System.currentTimeMillis();

        this.startTime = System.currentTimeMillis();
        this.totalValue = 0;
        this.value = 0;
        this.statusCode = null;
        this.statusText = null;
        this.completed = null;

        return this;
    }

    public long getAccessTime() {
        this.accessTime = System.currentTimeMillis();

        return this.accessTime;
    }

    public long getStartTime() {
        this.accessTime = System.currentTimeMillis();

        return this.startTime;
    }

    public double getTotalValue() {
        this.accessTime = System.currentTimeMillis();

        return this.totalValue;
    }

    public void setTotalValue(double totalValue) {
        this.accessTime = System.currentTimeMillis();

        if (this.startTime != 0) {
            this.totalValue = totalValue;
        }
    }

    public void addTotalValue(double value) {
        this.accessTime = System.currentTimeMillis();

        if (this.startTime != 0) {
            this.totalValue += value;
        }
    }

    public double getValue() {
        this.accessTime = System.currentTimeMillis();

        return this.value;
    }

    public void setValue(double value) {
        this.accessTime = System.currentTimeMillis();

        if (this.startTime != 0) {
            this.value = value;
        }
    }

    public void addValue(double value) {
        this.accessTime = System.currentTimeMillis();

        if (this.startTime != 0) {
            this.value += value;
        }
    }

    public String getStatusCode() {
        this.accessTime = System.currentTimeMillis();

        return this.statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.accessTime = System.currentTimeMillis();

        if (this.startTime != 0) {
            this.statusCode = statusCode;
        }
    }

    public String getStatusText() {
        this.accessTime = System.currentTimeMillis();

        return this.statusText;
    }

    public void setStatusText(String statusText) {
        this.accessTime = System.currentTimeMillis();

        if (this.startTime != 0) {
            this.statusText = statusText;
        }
    }

    public void setStatus(String statusCode, String statusText) {
        this.accessTime = System.currentTimeMillis();

        if (this.startTime != 0) {
            this.statusCode = statusCode;
            this.statusText = statusText;
        }
    }

    public boolean isCompleted() {
        if (this.completed == null) {
            return false;
        }

        return this.completed.booleanValue();
    }

    public void setCompleted(boolean completed) {
        this.completed = Boolean.valueOf(completed);
    }

    public double getProgress() {
        this.accessTime = System.currentTimeMillis();

        return this.startTime == 0 || this.totalValue == 0 ? 0 : this.value / this.totalValue;
    }

    public long getElapsedTime() {
        this.accessTime = System.currentTimeMillis();

        return this.startTime == 0 ? 0 : System.currentTimeMillis() - this.startTime;
    }

    public boolean isValid() {
        return (this.totalValue == 0 || this.value <= this.totalValue)
                && System.currentTimeMillis() - this.accessTime <= 1000 * 60;
    }

    public Map<String, Object> toMap() {
        this.accessTime = System.currentTimeMillis();

        Map<String, Object> map = new HashMap<String, Object>();

        map.put("startTime", this.startTime);
        map.put("elapsedTime", this.getElapsedTime());
        map.put("totalValue", this.totalValue);
        map.put("value", this.value);
        map.put("progress", this.getProgress());

        if (this.statusCode != null) {
            map.put("statusCode", this.statusCode);
        }

        if (this.statusText != null) {
            map.put("statusText", this.statusText);
        }

        if (this.completed != null) {
            map.put("completed", this.completed);
        }

        return map;
    }
}
