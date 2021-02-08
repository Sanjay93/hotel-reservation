package com.sanjay.model;

public interface IRoom {
    public String getRoomNumber();

    public Double getRoomPrice();

    public RoomType getRoomType();

    public void setRoomNumber(String roomNumber);

    public boolean isFree();

    public void setPrice(Double price);

    public void setRoomType(RoomType roomType);

    public void setFree(boolean free);
}
