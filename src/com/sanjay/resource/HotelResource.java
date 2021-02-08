package com.sanjay.resource;

import com.sanjay.exceptions.InvalidDataException;
import com.sanjay.model.Customer;
import com.sanjay.model.IRoom;
import com.sanjay.model.Reservation;
import com.sanjay.service.CustomerService;
import com.sanjay.service.ReservationService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HotelResource {


    public static Reservation bookARoom(String email, IRoom room, Date checkIn, Date checkOut) throws InvalidDataException{
        Customer customer = getCustomer(email);
        return ReservationService.getInstance().reserveRoom(customer, room, checkIn, checkOut);
    }

    public static IRoom getRoom(String roomNumber) {
        IRoom room = null;
        room = ReservationService.getInstance().findRoom(roomNumber);
        ReservationService.getInstance().printData(room);
        return room;
    }

    public static void createCustomer(String firstName, String lastName, String email) {
        CustomerService.getInstance().addCustomer(firstName, lastName, email);
    }

    public static List<Reservation> findCustomerReservation(String customerEmail) throws InvalidDataException{
        List<Reservation> reservationList = new ArrayList<>();
        Customer customer = getCustomer(customerEmail);
        reservationList = ReservationService.getInstance().findCustomerReservation(customer);
        ReservationService.getInstance().print(reservationList);
        return reservationList;
    }

    public static List<IRoom> findRooms(Date checkIn, Date checkOut) {
        try {
            List<IRoom> roomList =  ReservationService.getInstance().findRooms(checkIn, checkOut);
            ReservationService.getInstance().print(roomList);
            return roomList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Customer getCustomer(String email) throws InvalidDataException {
        Customer customer = null;
        customer =  CustomerService.getInstance().getCustomer(email);
        ReservationService.getInstance().printData(customer);
        return customer;
    }

}
