package clock;

import java.util.Scanner;

public class ClockTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Prompt the user for hours, minutes, and seconds
        System.out.println("Enter initial hours (0-23): ");
        int hours = scanner.nextInt();

        System.out.println("Enter initial minutes (0-59): ");
        int minutes = scanner.nextInt();

        System.out.println("Enter initial seconds (0-59): ");
        int seconds = scanner.nextInt();

        // Initialize Clock object
        try {
            Clock clock = new Clock(hours, minutes, seconds);

            // Display initial time
            System.out.println("24-hour format: " + clock.get24HourFormat());
            System.out.println("12-hour format: " + clock.get12HourFormat());

//            // Testing addSecond
//            System.out.println("\nAdding a second...");
//            clock.addSecond();
//            System.out.println("24-hour format: " + clock.get24HourFormat());
//            System.out.println("12-hour format: " + clock.get12HourFormat());
//
//            // Testing addMinute
//            System.out.println("\nAdding a minute...");
//            clock.addMinute();
//            System.out.println("24-hour format: " + clock.get24HourFormat());
//            System.out.println("12-hour format: " + clock.get12HourFormat());
//
//            // Testing addHour
//            System.out.println("\nAdding an hour...");
//            clock.addHour();
//            System.out.println("24-hour format: " + clock.get24HourFormat());
//            System.out.println("12-hour format: " + clock.get12HourFormat());

        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }

        scanner.close();
    }
}
