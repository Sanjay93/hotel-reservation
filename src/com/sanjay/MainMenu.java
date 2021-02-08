package com.sanjay;

import com.sanjay.exceptions.InvalidDataException;
import com.sanjay.model.Customer;
import com.sanjay.model.IRoom;
import com.sanjay.resource.HotelResource;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class MainMenu {
    static Scanner scanner = new Scanner(System.in);

    public static void getMainMenu() throws InvalidDataException {
        try {
            int option = 0;
            while (option != 5) {
                option = showMenu();
                switch (option) {
                    case 1:
                        bookARoom();
                        break;
                    case 2:
                        HotelResource.findCustomerReservation(getCustomerEmail());
                        break;
                    case 3:
                        createAccount();
                        break;
                    case 4:
                        AdminMenu.getAdminMenu();
                        break;
                    case 5:
                        System.exit(0);
                    default:
                        System.out.println("Please enter valid option");
                        break;
                }
            }
        } catch (Exception e) {
            System.out.println("Please enter valid option");
        }
    }

    public static void bookARoom() throws InvalidDataException {
        Date[] dates = getDateCheckinCheckOut();
        List<IRoom> iRoomList = HotelResource.findRooms(dates[0], dates[1]);
        if (iRoomList == null || iRoomList.isEmpty() || iRoomList.size() == 0) {
            System.out.println("No Rooms Available, Please try again later");
            return;
        }
        try {
            System.out.println("Would you like to book a room ? Y/N");
            String choiceRoom = scanner.next();
            if (choiceRoom.equalsIgnoreCase("N")) {
                return;
            }
            System.out.println("Do you have an account with us ? Y/N");
            String choiceAccount = scanner.next();
            if (choiceAccount.equalsIgnoreCase("N")) {
                createAccount();
            }
            String email = getCustomerEmail();
            Customer customer = HotelResource.getCustomer(email);
            System.out.println("What room number would you like to reserve");
            String roomNumber = scanner.next();
            IRoom room = HotelResource.getRoom(roomNumber);
            HotelResource.bookARoom(customer.getEmail(), room, dates[0], dates[1]);
            System.out.println("Reservation completed successfully");
        } catch (Exception e) {
            throw new InvalidDataException("Please enter valid option for booking the room");
        }
    }

    public static Date[] getDateCheckinCheckOut() throws InvalidDataException {
        Date[] dateArr;
        SimpleDateFormat dateFormat = new SimpleDateFormat("mm/dd/yyyy");
        try {
            System.out.println("Enter checkin Date mm/dd/yyyy example 2/2/2021");
            String checkInStr = scanner.next();
            System.out.println("Enter checkout Date mm/dd/yyyy example 2/5/2021");
            String checkOutStr = scanner.next();
            Date checkIn = dateFormat.parse(checkInStr);
            Date checkOut = dateFormat.parse(checkOutStr);
            dateArr = new Date[]{checkIn, checkOut};
        } catch (Exception e) {
            throw new InvalidDataException("Please enter valid date");
        }
        return dateArr;
    }

    public static String getCustomerEmail() throws InvalidDataException {
        String customerEmail;
        try {
            System.out.println("Enter your email in this format: name@domain.com");
            customerEmail = scanner.next();
        } catch (Exception e) {
            throw new InvalidDataException("Please enter valid email");
        }
        return customerEmail;
    }


    public static void createAccount() throws InvalidDataException {
        try {
            System.out.println("Creating An Account, Please provide details");
            System.out.println("Enter first name");
            String firstName = scanner.next();
            System.out.println("Enter last name");
            String lastName = scanner.next();
            String email = getCustomerEmail();
            HotelResource.createCustomer(firstName, lastName, email);
            System.out.println("Account Created");
        } catch (Exception e) {
            throw new InvalidDataException("Please enter valid option!!");
        }
    }

    public static int showMenu() throws InvalidDataException {
        try {
            System.out.println("Welcome to Hotel Reservation System");
            System.out.println("-----------------------------------");
            System.out.println("Please Select a number for the menu option");
            System.out.println("1. Find and Reserve a Room");
            System.out.println("2. See my Reservations");
            System.out.println("3. Create an account");
            System.out.println("4. Admin");
            System.out.println("5. Exit");
            System.out.println("-----------------------------------");
            return scanner.nextInt();
        } catch (Exception e) {
            throw new InvalidDataException("Please enter valid option!!");
        }
    }
}
