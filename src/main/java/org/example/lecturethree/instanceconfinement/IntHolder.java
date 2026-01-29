package org.example.lecturethree.instanceconfinement;

// Instance Confinement Pattern
public class IntHolder {
    private int value;

    public IntHolder(int initialValue) {
        this.value = initialValue;
    }

    int getValue() {
        return value;
    }

    void setValue(int value) {
        this.value = value;
    }
}
