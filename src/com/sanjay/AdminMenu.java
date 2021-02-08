package com.sanjay;

import com.sanjay.exceptions.InvalidDataException;
import com.sanjay.model.FreeRoom;
import com.sanjay.model.IRoom;
import com.sanjay.model.Room;
import com.sanjay.model.RoomType;
import com.sanjay.resource.AdminResource;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AdminMenu {
    public static void getAdminMenu() throws InvalidDataException {
        try {
            int option = 0;
            while (option != 5) {
                option = showMenu();
                switch (option) {
                    case 1:
                        AdminResource.getAllCustomer();
                        break;
                    case 2:
                        AdminResource.getAllRooms();
                        break;
                    case 3:
                        AdminResource.displayAllReservations();
                        break;
                    case 4:
                        List<IRoom> roomList = new ArrayList<>();
                        roomList = getRoomDetails();
                        AdminResource.addRoom(roomList);
                        break;
                    case 5:
                        MainMenu.getMainMenu();
                        break;
                    default:
                        System.out.println("Please enter valid option");
                }
            }
        } catch (Exception e) {
            System.out.println("Please enter valid option");
        }
    }

    public static List<IRoom> getRoomDetails() throws InvalidDataException {
        Scanner scanner = new Scanner(System.in);
        List<IRoom> roomList = new ArrayList<>();
        String choice = "Y";
        try {
            while (!choice.equalsIgnoreCase("N")) {
                IRoom room = null;
                System.out.println("Enter room number");
                String roomNo = scanner.next();
                System.out.println("Enter price per night");
                Double price = scanner.nextDouble();
                System.out.println("Enter room type SINGLE or DOUBLE");
                String roomType = scanner.next();
                if (price == 0) {
                    room = new FreeRoom(price);
                } else {
                    room = new Room();
                }
                room.setRoomNumber(roomNo);
                room.setPrice(price);
                if (!roomType.isEmpty()) {
                    room.setRoomType(RoomType.valueOf(roomType.toUpperCase()));
                }
                room.setFree(true);
                roomList.add(room);
                System.out.println("Would like to add another room Y/N ");
                choice = scanner.next();
            }
        } catch (Exception e) {
            throw new InvalidDataException("Please enter valid option");
        }
        return roomList;
    }

    public static int showMenu() throws InvalidDataException {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Welcome to Admin of Hotel Reservation");
            System.out.println("-------------------------------------");
            System.out.println("Please Select a number for the menu option");
            System.out.println("1. See all Customers");
            System.out.println("2. See all Rooms");
            System.out.println("3. See all Reservations");
            System.out.println("4. Add A Room");
            System.out.println("5. Back To Main Menu");
            System.out.println("-------------------------------------");
            return scanner.nextInt();
        } catch (Exception e) {
            throw new InvalidDataException("Please enter valid option");
        }
    }
}
