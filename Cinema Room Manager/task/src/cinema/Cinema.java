package cinema;

import java.util.Scanner;

public class Cinema {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the number of rows:");
        int rows = scanner.nextInt();

        System.out.println("Enter the number of seats in each row:");
        int seatsPerRow = scanner.nextInt();

        scanner.close();

        calculateTotalIncome(rows, seatsPerRow);
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
            int numberOfRowsInFront = rows / 2;
            int numberOfRowsInBack = rows - numberOfRowsInFront;
            totalIncome = (numberOfRowsInFront * seatsPerRow * priceFrontSeats)
                    + (numberOfRowsInBack * seatsPerRow * priceBackSeats);
        }

        System.out.println("Total income:");
        System.out.println("$" + totalIncome);
    }

    private static void displaySeats(int rows, int seatsPerRow) {
        System.out.println("Cinema:");
        String rowNumber = " ";
        for (int i = 0; i < rows + 1; i++) {
            StringBuilder line = new StringBuilder();
            line.append(rowNumber);
            rowNumber = String.valueOf(i + 1);
            for (int j = 0; j < seatsPerRow; j++) {
                line.append(" ");
                if (i == 0) {
                    line.append(j + 1);
                } else {
                    line.append("S");
                }
            }
            System.out.println(line);
        }
    }
}