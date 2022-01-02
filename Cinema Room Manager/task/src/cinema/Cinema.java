package cinema;

import java.util.Scanner;

public class Cinema {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        start();
    }

    private static void start() {
        int[] seatConfiguration = getSeatConfiguration();
        int rows = seatConfiguration[0];
        int seatsPerRow = seatConfiguration[1];
        int totalSeats = rows * seatsPerRow;
        int[][] seats = getSeatArrangement(rows, seatsPerRow);
        int choice;
        int ticketsPurchased = 0;
        int currentIncome = 0;
        double totalIncome = getTotalIncome(rows, seatsPerRow);


        do {
            choice = showMenu();

            switch (choice) {
                case 1:
                    showSeats(seats);
                    break;
                case 2:
                    do {
                        double[] tickets = buyTicket(rows, seatsPerRow);

                        try {
                            updateSeatArrangement(seats, (int) tickets[1], (int) tickets[2], rows, seatsPerRow);

                            System.out.printf("Ticket price: $%.0f%n%n", tickets[0]);

                            ticketsPurchased++;
                            currentIncome += (int) tickets[0];
                            break;
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                    } while (true);
                    break;
                case 3:
                    double percentageTicketPurchased = calculatePercentage(ticketsPurchased, totalSeats);
                    System.out.printf("" +
                            "Number of purchased tickets: %d%n" +
                            "Percentage: %.2f%%%n" +
                            "Current income: $%d%n" +
                            "Total income: $%.0f%n",

                        ticketsPurchased,
                        percentageTicketPurchased,
                        currentIncome,
                        totalIncome
                    );
                case 0:
                    break;
            }
        } while (choice != 0);
    }

    private static double calculatePercentage(int ticketsPurchased, double totalSeats) {
        final int PERCENT = 100;

        if (totalSeats == 0) {
            return 0;
        }

        return (ticketsPurchased * PERCENT) / totalSeats;
    }

    private static void showSeats(int[][] seats) {
        System.out.println("\nCinema:");

        final int MAX_SEATS = seats[0].length;
        final int MAX_ROWS = seats.length;

        for (int seatNum = 0; seatNum <= MAX_SEATS; seatNum++) {
            if (seatNum == 0) {
                System.out.print("  ");
            }

            System.out.print(seatNum + 1 > MAX_SEATS ? "" : seatNum + 1 + " ");
        }

        System.out.println();

        for (int row = 0; row < MAX_ROWS; row++) {
            System.out.printf("%d ", row + 1);

            for (int seat = 0; seat < seats[row].length; seat++) {
                System.out.printf("%c ", seats[row][seat]);
            }

            System.out.println();
        }
    }

    private static int[] getSeatConfiguration() {
        System.out.println("Enter the number of rows:");
        int rows = scanner.nextInt();

        System.out.println("Enter the number of seats in each row:");
        int seatsPerRow = scanner.nextInt();

        return new int[] { rows, seatsPerRow };
    }

    private static int[][] getSeatArrangement(int rows, int seatsPerRow) {
        int[][] seats = new int[rows][seatsPerRow];

        for (int row = 0; row < rows; row++) {
            for (int seat = 0; seat < seatsPerRow; seat++) {
                seats[row][seat] = 'S';
            }
        }

        return seats;
    }

    private static double getTotalIncome(int rows, int seatsPerRow) {
        int totalNumberOfSeats = rows * seatsPerRow;
        double frontSeatPrice = 10;
        double backSeatPrice = 8;
        double frontSeatSize = 0;
        double backSeatSize = 0;

        final int SMALL_CINEMA_SEATS = 60;

        if (totalNumberOfSeats <= SMALL_CINEMA_SEATS) {
            frontSeatSize = totalNumberOfSeats;
        } else if (rows % 2 == 0) {
            frontSeatSize = totalNumberOfSeats / 2.0;
            backSeatSize = frontSeatSize;
        } else {
            double frontSeatRow = (rows - 1) / 2.0;
            double backSeatRow = frontSeatRow + 1;
            frontSeatSize = frontSeatRow * seatsPerRow;
            backSeatSize = backSeatRow * seatsPerRow;
        }

        return (frontSeatSize * frontSeatPrice) + (backSeatSize * backSeatPrice);
    }

    private static double[] buyTicket(int rows, int seatsPerRow) {

        System.out.println("Enter a row number:");
        int rowNum = scanner.nextInt();

        System.out.println("Enter a seat number in that row:");
        int seatNum = scanner.nextInt();

        int totalNumberOfSeats = rows * seatsPerRow;
        double frontSeatPrice = 10;
        double backSeatPrice = 8;
        double ticketPrice = 0;

        final int SMALL_CINEMA_SEATS = 60;

        if (totalNumberOfSeats <= SMALL_CINEMA_SEATS) {
            ticketPrice = frontSeatPrice;
        } else if (rows % 2 == 0) {

            if (rowNum <= (rows / 2)) {
                ticketPrice = frontSeatPrice;
            } else {
                ticketPrice = backSeatPrice;
            }
        } else {
            double frontSeatRow = (rows - 1) / 2.0;

            if (rowNum <= frontSeatRow) {
                ticketPrice = frontSeatPrice;
            } else {
                ticketPrice = backSeatPrice;
            }
        }

        return new double[] { ticketPrice, rowNum, seatNum };
    }

    private static void updateSeatArrangement(int[][] seats, int... seatConfig) throws Exception {
        int rowNum = seatConfig[0];
        int seatNum = seatConfig[1];
        int rows = seatConfig[2];
        int seatsPerRow = seatConfig[3];

        if (rowNum > rows || seatNum > seatsPerRow) {
            throw new Exception("Wrong input!");
        }

        if (seats[rowNum - 1][seatNum - 1] == 'B') {
            throw new Exception("That ticket has already been purchased!");
        }

        seats[rowNum - 1][seatNum - 1] = 'B';
    }

    private static int showMenu() {
        System.out.println("\n1. Show the seats\n2. Buy a ticket\n3. Statistics\n0. Exit");

        return scanner.nextInt();
    }
}
