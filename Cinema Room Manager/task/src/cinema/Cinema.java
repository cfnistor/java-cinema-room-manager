package cinema;

import java.util.Scanner;

public class Cinema {

    private static final int NUMBER_OF_SEATS_THRESHOLD = 60;
    private static final int PRICE_FRONT_SEATS = 10;
    private static final int PRICE_BACK_SEATS = 8;
    
    private static int countPurchasedTickets = 0;
    
    private static int currentIncome = 0;
    
    private static int totalNumberOfAvailableTickets = 0;
    
    private static int totalIncome = 0;

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        // Read the cinema size
        System.out.println("Enter the number of rows:");
        int rows = scanner.nextInt();

        System.out.println("Enter the number of seats in each row:");
        int seatsPerRow = scanner.nextInt();

        calculateTotalIncome(rows, seatsPerRow);

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
            System.out.println("3. Statistics");
            System.out.println("0. Exit");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    displaySeats(seats);
                    break;
                case 2:
                    buyTicket(scanner, seats);
                    break;
                case 3:
                    getStatistics();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Invalid option, please try again.");
            }
        }
    }

    private static void getStatistics() {
        double percentageSold = ((double)countPurchasedTickets / totalNumberOfAvailableTickets) * 100;
        System.out.println("Number of purchased tickets: " + countPurchasedTickets);
        System.out.printf("Percentage: %.2f%%\n", percentageSold);
        System.out.println("Current income: $" + currentIncome);
        System.out.println("Total income: $" + totalIncome);
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
        int row, seat;
        while (true) {
            System.out.println("Enter a row number:");
            row = scanner.nextInt() - 1;

            System.out.println("Enter a seat number in that row:");
            seat = scanner.nextInt() - 1;

            if (row < 0 || row >= seats.length || seat < 0 || seat >= seats[0].length) {
                System.out.println("Wrong input!");
                continue;
            }

            if (seats[row][seat] == 'B') {
                System.out.println("That ticket has already been purchased!");
            } else {
                break;
            }
        }

        // Calculate and display ticket price
        int price = getTicketPrice(seats.length, seats[0].length, row);
        System.out.println("Ticket price: $" + price);
        currentIncome += price;
        countPurchasedTickets++;

        // Mark seat as booked
        seats[row][seat] = 'B';
    }

    private static int getTicketPrice(int rows, int seatsPerRow, int selectedRow) {

        if (rows * seatsPerRow <= NUMBER_OF_SEATS_THRESHOLD || selectedRow < rows / 2) {
            return PRICE_FRONT_SEATS;
        } else {
            return PRICE_BACK_SEATS;
        }
    }

    private static void calculateTotalIncome(int rows, int seatsPerRow) {

        totalNumberOfAvailableTickets = rows * seatsPerRow;

        if (totalNumberOfAvailableTickets <= NUMBER_OF_SEATS_THRESHOLD) {
            totalIncome = totalNumberOfAvailableTickets * PRICE_FRONT_SEATS;
        } else {
            int numberOfRowsFrontHalf = rows / 2;
            int numberOfRowsBackHalf = rows - numberOfRowsFrontHalf;
            totalIncome = (numberOfRowsFrontHalf * seatsPerRow * PRICE_FRONT_SEATS)
                    + (numberOfRowsBackHalf * seatsPerRow * PRICE_BACK_SEATS);
        }
    }

    public static int getCountPurchasedTickets() {
        return countPurchasedTickets;
    }

    public static void setCountPurchasedTickets(int countPurchasedTickets) {
        Cinema.countPurchasedTickets = countPurchasedTickets;
    }

    public static int getCurrentIncome() {
        return currentIncome;
    }

    public static void setCurrentIncome(int currentIncome) {
        Cinema.currentIncome = currentIncome;
    }

    public static int getTotalNumberOfAvailableTickets() {
        return totalNumberOfAvailableTickets;
    }

    public static void setTotalNumberOfAvailableTickets(int totalNumberOfAvailableTickets) {
        Cinema.totalNumberOfAvailableTickets = totalNumberOfAvailableTickets;
    }

    public static int getTotalIncome() {
        return totalIncome;
    }

    public static void setTotalIncome(int totalIncome) {
        Cinema.totalIncome = totalIncome;
    }
}