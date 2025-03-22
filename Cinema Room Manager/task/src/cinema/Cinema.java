package cinema;

import java.util.Scanner;

public class Cinema {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the number of rows:");
        int rows = scanner.nextInt();

        System.out.println("Enter the number of seats in each row:");
        int seatsPerRow = scanner.nextInt();

        int selectedRow = 0;
        int selectedSeat = 0;

        displaySeats(rows, seatsPerRow, selectedRow, selectedSeat);

        System.out.println();
        System.out.println("Enter a row number:");
        selectedRow = scanner.nextInt();

        System.out.println("Enter a seat number in that row:");
        selectedSeat = scanner.nextInt();

        scanner.close();

        displaySeats(rows, seatsPerRow, selectedRow, selectedSeat);
    }

    private static void calculateTotalIncome(int rows, int seatsPerRow) {
        int totalIncome = 0;
        int numberOfSeatsThreshold = 60;
        int priceFrontSeats = 10;
        int priceBackSeats = 8;
        int numberOfSeats = rows * seatsPerRow;

        if (numberOfSeats <= numberOfSeatsThreshold) {
            totalIncome = numberOfSeats * priceFrontSeats;
        } else {
            int numberOfRowsFrontHalf = rows / 2;
            int numberOfRowsBackHalf = rows - numberOfRowsFrontHalf;
            totalIncome = (numberOfRowsFrontHalf * seatsPerRow * priceFrontSeats)
                    + (numberOfRowsBackHalf * seatsPerRow * priceBackSeats);
        }

        System.out.printf("Total income:\n$%d", totalIncome);
    }

    private static void displaySeats(int rows, int seatsPerRow, int selectedRow, int selectedSeat) {
        if (selectedRow != 0 && selectedSeat != 0) {
            int ticketPrice = 8;
            if (rows * seatsPerRow <= 60 || selectedRow <= rows / 2) {
                ticketPrice = 10;
            }
            System.out.printf("\nTicket price: $%d\n\n", ticketPrice);
        }

        System.out.println("Cinema:");
        String rowNumber = " ";
        String ticketSymbol = "";
        for (int i = 0; i < rows + 1; i++) {
            StringBuilder line = new StringBuilder();
            line.append(rowNumber);
            rowNumber = String.valueOf(i + 1);
            for (int j = 0; j < seatsPerRow; j++) {
                ticketSymbol = "S";
                line.append(" ");
                if (i == 0) {
                    line.append(j + 1);
                } else {
                    if (i == selectedRow && j + 1 == selectedSeat) {
                        ticketSymbol = "B";
                    }
                    line.append(ticketSymbol);
                }
            }
            System.out.println(line);
        }
    }
}