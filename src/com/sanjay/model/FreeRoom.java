package com.sanjay.model;

public class FreeRoom extends Room {

    public FreeRoom(Double price) {
        super.setPrice(new Double(0));
    }

    @Override
    public String toString() {
        return "FreeRoom{" +
                "roomNumber='" + super.getRoomNumber() + '\'' +
                ", price=" + super.getRoomPrice() +
                ", roomType=" + super.getRoomType() +
                ", isFree=" + super.isFree() +
                '}';
    }
}
