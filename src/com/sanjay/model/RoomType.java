package com.sanjay.model;

public enum RoomType {
    SINGLE(1), DOUBLE(2);

    private int number;

    RoomType(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }
}
