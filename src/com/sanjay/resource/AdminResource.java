package com.sanjay.resource;

import com.sanjay.model.Customer;
import com.sanjay.model.IRoom;
import com.sanjay.service.CustomerService;
import com.sanjay.service.ReservationService;

import java.util.List;

public class AdminResource {

    public static void displayAllReservations() {
        ReservationService.getInstance().printAllReservation();
    }

    public static void addRoom(List<IRoom> rooms) {
        ReservationService.getInstance().addRoom(rooms);
    }

    public static List<Customer> getAllCustomer() {
        List<Customer> customerList = CustomerService.getInstance().getAllCustomers();
        ReservationService.getInstance().print(customerList);
        return customerList;
    }

    public static List<IRoom> getAllRooms() {
        List<IRoom> roomList = ReservationService.getInstance().getAllRooms();
        ReservationService.getInstance().print(roomList);
        return roomList;
    }
}
