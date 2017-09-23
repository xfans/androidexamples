package com.examples.demo.utils;

/**
 * Created by xfans on 17-9-24.
 */

public class GroupXY {
    float x;
    float y;

    public GroupXY(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return (int) x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return (int) y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
