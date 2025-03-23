package cinema;

import java.util.Scanner;

public class Cinema {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        // Read the cinema size
        System.out.println("Enter the number of rows:");
        int rows = scanner.nextInt();

        System.out.println("Enter the number of seats in each row:");
        int seatsPerRow = scanner.nextInt();

        // Initialize seating arrangement
        char[][] seats = new char[rows][seatsPerRow];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < seatsPerRow; j++) {
                seats[i][j] = 'S';
            }
        }

        // Display menu loop
        while(true) {
            System.out.println("\n1. Show the seats");
            System.out.println("2. Buy a ticket");
            System.out.println("0. Exit");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    displaySeats(seats);
                    break;
                case 2:
                    buyTicket(scanner, seats);
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Invalid option, please try again.");
            }
        }
    }

    private static void displaySeats(char[][] seats) {
        System.out.println("\nCinema:");
        System.out.print("  ");
        for (int j = 1; j <= seats[0].length; j++) {
            System.out.print(j + " ");
        }
        System.out.println();

        for (int i = 0; i < seats.length; i++) {
            System.out.print((i + 1) + " ");
            for (int j = 0; j < seats[i].length; j++) {
                System.out.print(seats[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static void buyTicket(Scanner scanner, char[][] seats) {
        System.out.println("Enter a row number:");
        int row = scanner.nextInt() - 1;

        System.out.println("Enter a seat number in that row:");
        int seat = scanner.nextInt() - 1;

        // Calculate and display ticket price
        int price = getTicketPrice(seats.length, seats[0].length, row);
        System.out.println("Ticket price: $" + price);

        // Mark seat as booked
        seats[row][seat] = 'B';
    }

    private static int getTicketPrice(int rows, int seatsPerRow, int selectedRow) {
        int numberOfSeatsThreshold = 60;
        int frontRowPrice = 10;
        int backRowPrice = 8;

        if (rows * seatsPerRow <= numberOfSeatsThreshold || selectedRow < rows / 2) {
            return frontRowPrice;
        } else {
            return backRowPrice;
        }
    }
}