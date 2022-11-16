package kr.ac.jj.survey.infrastructure.framework.core.support.sequence;

import org.apache.commons.lang3.StringUtils;

import kr.ac.jj.survey.infrastructure.framework.core.foundation.exception.BaseException;

public class BaseSequence {
    private final long minValue;
    private final long maxValue;
    private final boolean cycle;

    private final int maxValueLength;
    private long currentValue;

    public BaseSequence(long minValue, long maxValue) {
        this(minValue, maxValue, false);
    }

    public BaseSequence(long minValue, long maxValue, boolean cycle) {
        this.minValue = minValue;
        this.maxValue = maxValue;
        this.cycle = cycle;

        this.maxValueLength = Long.toString(this.maxValue).length();
        this.resetValue();
    }

    public void resetValue() {
        this.currentValue = minValue - 1;
    }

    public long getMinValue() {
        return this.minValue;
    }

    public long getMaxValue() {
        return this.maxValue;
    }

    public boolean isCycle() {
        return this.cycle;
    }

    public long getCurrentValue() {
        if (this.currentValue < this.minValue) {
            throw new BaseException("The sequence value is invalid.");
        }

        return this.currentValue;
    }

    public String getCurrentValue(char padChar) {
        return this.getCurrentValue(this.maxValueLength, padChar);
    }

    public String getCurrentValue(int size, char padChar) {
        return StringUtils.leftPad(Long.toString(this.getCurrentValue()), size, padChar);
    }

    public String getCurrentValue(String padStr) {
        return this.getCurrentValue(this.maxValueLength, padStr);
    }

    public String getCurrentValue(int size, String padStr) {
        return StringUtils.leftPad(Long.toString(this.getCurrentValue()), size, padStr);
    }

    public long getNextValue() {
        if (this.currentValue >= this.maxValue) {
            if (!this.cycle) {
                throw new BaseException("The sequence value exceeds the maximum value of " + this.maxValue + ".");
            }

            this.resetValue();
        }

        this.currentValue++;

        return this.currentValue;
    }

    public String getNextValue(char padChar) {
        return this.getNextValue(this.maxValueLength, padChar);
    }

    public String getNextValue(int size, char padChar) {
        return StringUtils.leftPad(Long.toString(this.getNextValue()), size, padChar);
    }

    public String getNextValue(String padStr) {
        return this.getNextValue(this.maxValueLength, padStr);
    }

    public String getNextValue(int size, String padStr) {
        return StringUtils.leftPad(Long.toString(this.getNextValue()), size, padStr);
    }
}
