package com.news.web.converter;

/**
 * @author Paul Lee
 */
public class Exercise implements Cloneable {

    private int[] arr;

    public Exercise() {

        arr = new int[10];

        for (int i = 0; i < 10; i++) {
            arr[i] = i;
        }
    }

    public void set(int index, int num) {
        arr[index] = num;
    }

    public int get(int index) {
        return arr[index];
    }

    @Override
    protected Exercise clone() throws CloneNotSupportedException {
        return (Exercise) super.clone();
    }
}
