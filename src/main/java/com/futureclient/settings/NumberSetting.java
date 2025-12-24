package com.futureclient.settings;

/**
 * Number setting with min/max and step.
 */
public class NumberSetting extends Setting {
    private double value;
    private final double min;
    private final double max;
    private final double step;

    public NumberSetting(String name, double defaultValue, double min, double max, double step) {
        super(name);
        this.value = defaultValue;
        this.min = min;
        this.max = max;
        this.step = step;
    }

    public double getValue() { return value; }
    public void setValue(double v) { value = Math.max(min, Math.min(max, v)); }
    public double getMin() { return min; }
    public double getMax() { return max; }
    public double getStep() { return step; }
}