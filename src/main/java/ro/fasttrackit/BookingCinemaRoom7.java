package ro.fasttrackit;

import java.text.DecimalFormat;
import java.util.Random;
import java.util.Scanner;

public class BookingCinemaRoom7 {
    public static void main(String[] args) {
        boolean[][] room7 = new boolean[14][19];
        int option;
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("Displaying the menu:");
            System.out.println("1. Display current state of the cinema room");
            System.out.println("2. Reserve seats");
            System.out.println("3. Cancel seats reservation");
            System.out.println("4. Display occupancy rate of the cinema room");
            System.out.println("5. Randomize seating allocation of the cinema room");
            System.out.println("6. Exit app");
            System.out.println("Choose an option:");
            option = scanner.nextInt();
            int counterForReservedSeats;
            switch (option) {
                case 1 -> {
                    System.out.println("Displaying the current state of the cinema room: ");
                    displayCurrentStateOfTheCinemaRoom(room7);
                }
                case 2 -> {
                    System.out.println("Reserving seats: ");
                    reserveSeats(room7, scanner);
                }
                case 3 -> {
                    System.out.println("Cancelling seats reservation: ");
                    cancelSeatsReservation(room7, scanner);
                }
                case 4 -> {
                    System.out.println("Displaying occupancy rate of the cinema room: ");
                    displayOccupancyRateOfTheCinemaRoom(room7);
                }
                case 5 -> {
                    System.out.println("Randomizing seatting allocation of the cinema room: ");
                    randomizeSeatingAllocationOfTheCinemaRoom(room7);
                }
                case 6 -> System.out.println("Exiting app...");
                default -> System.out.println("Invalid option (please choose another one between 1 and 6)");
            }
        } while (option != 6);
        System.out.println("Bye bye!");
    }

    public static void displayCurrentStateOfTheCinemaRoom(boolean[][] room7) {
        for (int i = 0; i < room7.length; i++) {
            for (int j = 0; j < room7[i].length; j++) {
                if (room7[i][j]) {
                    System.out.print("[X]");
                } else {
                    System.out.print("[0]");
                }
            }
            System.out.println();
        }
    }

    public static void reserveSeats(boolean[][] room7, Scanner scanner) {
        System.out.println("How many tickets do you want to reserve?");
        int numberOfTickets = scanner.nextInt();
        for (int i = 1; i <= numberOfTickets; i++) {
            System.out.println("What row and seat would you like to reserve for the ticket: " + i + "?");
            System.out.println("Say the row, then the seat number: ");
            int row = scanner.nextInt() - 1;
            int seat = scanner.nextInt() - 1;
            boolean isOccupied = room7[row][seat];
            if (!isOccupied) {
                room7[row][seat] = true;
                System.out.println("Ticket is yours, the chosen row " + (row + 1) + " and seat " + (seat + 1) + " is reserved");
            } else {
                System.out.println("Sorry, the chosen row and seat it is already reserved!");
                i--;
            }
        }
    }

    public static void cancelSeatsReservation(boolean[][] room7, Scanner scanner) {
        int counterForReservedSeats = 0;
        for (int i = 0; i < room7.length; i++) {
            for (int j = 0; j < room7[i].length; j++) {
                if (room7[i][j]) {
                    counterForReservedSeats++;
                }
            }
        }
        if (counterForReservedSeats == 0) {
            System.out.println("The maximum number of tickets you can cancel the reservation for is " + counterForReservedSeats);
        } else {
            System.out.println("The maximum number of tickets you can cancel the reservation for is " + counterForReservedSeats);
            System.out.println("How many tickets do you want to cancel the reservation for?");
            int numberOfTickets = scanner.nextInt();
            if (numberOfTickets <= counterForReservedSeats) {
                for (int i = 1; i <= numberOfTickets; i++) {
                    System.out.println("What row and seat would you like to cancel the reservation for the ticket: " + i + "?");
                    System.out.println("Say the row, then the seat number for cancellation of the reservation: ");
                    int row = scanner.nextInt() - 1;
                    int seat = scanner.nextInt() - 1;
                    boolean isOccupied = room7[row][seat];
                    if (isOccupied) {
                        room7[row][seat] = false;
                        System.out.println("The ticket reservation for the row " + (row + 1) + " and seat "
                                + (seat + 1) + " is cancelled");
                    } else {
                        System.out.println("Sorry! The current row and seat it is not reserved so that it can be cancelled");
                        i--;
                    }
                }
            } else {
                System.out.println("Sorry! You want to cancel the reservation for " + numberOfTickets +
                        " ticket/tickets, but the maximum number of tickets you can cancel for, is " + counterForReservedSeats);
            }
        }
    }

    public static void displayOccupancyRateOfTheCinemaRoom(boolean[][] room7) {
        double totalSeats = 14 * 19;
        double occupiedSeats = 0;
        for (int i = 0; i < room7.length; i++) {
            for (int j = 0; j < room7[i].length; j++) {
                boolean isOccupied = room7[i][j];
                if (isOccupied) {
                    occupiedSeats++;
                }
            }
        }
        double occupancyRate = occupiedSeats / totalSeats * 100;
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        String formattedResult = decimalFormat.format(occupancyRate);
        System.out.println("Occupancy rate of the cinema room: " + formattedResult + "%");
    }

    public static void randomizeSeatingAllocationOfTheCinemaRoom(boolean[][] room7) {
        for (int i = 0; i < room7.length; i++) {
            for (int j = 0; j < room7[i].length; j++) {
                Random randomSeatsAllocation = new Random();
                boolean isOccupiedOrNot = randomSeatsAllocation.nextBoolean();
                room7[i][j] = isOccupiedOrNot;
            }
        }
        System.out.println("Randomizing seating allocation done!");
    }
}