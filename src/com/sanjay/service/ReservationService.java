package com.sanjay.service;

import com.sanjay.model.Customer;
import com.sanjay.model.IRoom;
import com.sanjay.model.Reservation;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class ReservationService {
    private static ReservationService instance = null ;

    private ReservationService() {

    }

    public static ReservationService getInstance() {
        if (instance == null) {
            synchronized (ReservationService.class) {
                instance = new ReservationService();
            }
        }
        return instance;
    }

    private static ConcurrentHashMap<Customer, List<Reservation>> customerReservationMap = new ConcurrentHashMap<>();
    private static ConcurrentHashMap<String, IRoom> roomMap = new ConcurrentHashMap<>();

    public  void addRoom(List<IRoom> roomList) {
        try {
            for (IRoom room : roomList) {
                roomMap.put(room.getRoomNumber(), room);
            }
        } catch (ConcurrentModificationException e) {
            System.err.println("Exception occurred at adding the room " + e.getMessage());
        }
    }

    public  List<IRoom> findRooms(Date checkIn, Date checkOut) {
        if (roomMap.isEmpty()) {
            return null;
        }
        List<IRoom> roomFindList = new ArrayList<>();
        try {
            for (Map.Entry<String, IRoom> roomEntry : roomMap.entrySet()) {
                IRoom room = roomEntry.getValue();
                roomFindList.add(room);
            }
            for (Map.Entry<Customer, List<Reservation>> entrySet : customerReservationMap.entrySet()) {
                List<Reservation> reservationsList = entrySet.getValue();
                for (Reservation reservation : reservationsList) {
                    IRoom room = reservation.getRoom();
                    if (roomFindList.contains(room) && (reservation.getCheckInDate().compareTo(checkIn) == 0 || reservation.getCheckOutDate().compareTo(checkOut) == 0)) {
                        roomFindList.remove(room);
                    }
                }
            }
        } catch (Exception e) {
            System.err.println("Exception occurred at finding the rooms " + e.getMessage());
        }
        return roomFindList;
    }

    public  IRoom findRoom(String roomNumber) {
        if (roomMap.isEmpty()) {
            return null;
        }
        return roomMap.getOrDefault(roomNumber, null);
    }

    public  List<IRoom> getAllRooms() {
        List<IRoom> roomList = new ArrayList<>();
        for (Map.Entry<String, IRoom> roomEntry : roomMap.entrySet()) {
            IRoom room = roomEntry.getValue();
            roomList.add(room);
        }
        return roomList;
    }

    public  List<Reservation> printAllReservation() {
        List<Reservation> reservationList = new ArrayList<>();
        for (Map.Entry<Customer, List<Reservation>> entrySet : customerReservationMap.entrySet()) {
            List<Reservation> value = entrySet.getValue();
            reservationList.addAll(value);
        }
        print(reservationList);
        return reservationList;
    }

    public  Reservation reserveRoom(Customer customer, IRoom room, Date checkIn, Date checkOut) {
        Reservation reservation = new Reservation();
        try {

            List<Reservation> reservationList = new ArrayList<>();
            if (customerReservationMap.containsKey(customer)) {
                reservationList = customerReservationMap.get(customer);
            }
            reservation.setCheckInDate(checkIn);
            reservation.setCheckOutDate(checkOut);
            reservation.setCustomer(customer);
            room.setFree(false);
            reservation.setRoom(room);
            reservationList.add(reservation);
            customerReservationMap.put(customer, reservationList);
        } catch (Exception e) {
            System.err.println("Exception occurred at reserve the rooms " + e.getMessage());
        }
        return reservation;
    }

    public  List<Reservation> findCustomerReservation(Customer customer) {
        List<Reservation> reservationList = customerReservationMap.get(customer);
        return reservationList;
    }

    public  <T> void print(List<T> listData) {
        if (listData == null || listData.size() == 0 || listData.isEmpty()) {
            System.out.println("No data found");
            return;
        }
        for (T e : listData) {
            System.out.println(e);
        }
    }

    public  <T> void printData(T data) {
        if (data == null) {
            System.out.println("No data found" );
            return;
        }
        System.out.println(data.toString());
    }
}
