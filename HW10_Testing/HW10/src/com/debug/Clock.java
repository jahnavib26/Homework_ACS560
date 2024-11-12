package com.debug;

/**
 * The Clock class represents time in both 24-hour (hh:mm:ss) and 12-hour (h:mm:ss AM/PM) formats.
 */
public class Clock {
   private int hours;
   private int minutes;
   private int seconds;

   /**
    * Constructs a Clock with the specified hours, minutes, and seconds.
    * Validates the time input to ensure it is within the allowed range.
    *
    * @param hours   The hour value (0-23).
    * @param minutes The minute value (0-59).
    * @param seconds The second value (0-59).
    * @throws IllegalArgumentException if any time component is out of range.
    */
   public Clock(int hours, int minutes, int seconds) {
      if (hours >= 0 && hours <= 23 && minutes >= 0 && minutes <= 59 && seconds >= 0 && seconds <= 59) {
         this.hours = hours;
         this.minutes = minutes;
         this.seconds = seconds;
      } else {
         throw new IllegalArgumentException("Invalid time values. Ensure hours (0-23), minutes (0-59), and seconds (0-59).");
      }
   }

   /**
    * Adds an hour to the current time, wrapping back to 0 after 23.
    */
   public void addHour() {
      if (hours == 23) {
         hours = 0;
      } else {
         hours++;
      }
   }

   /**
    * Adds a minute to the current time. If minutes reach 60, resets to 0 and increments the hour.
    */
   public void addMinute() {
      if (minutes == 59) {
         minutes = 0;
         addHour();
      } else {
         minutes++;
      }
   }

   /**
    * Adds a second to the current time. If seconds reach 60, resets to 0 and increments the minute.
    */
   public void addSecond() {
      if (seconds == 59) {
         seconds = 0;
         addMinute();
      } else {
         seconds++;
      }
   }

   /**
    * Returns the time in 24-hour format (hh:mm:ss).
    * @return A string representing the time in 24-hour format.
    */
   public String get24HourFormat() {
      return pad(hours) + ":" + pad(minutes) + ":" + pad(seconds);
   }

   /**
    * Returns the time suffix (AM/PM) for 12-hour format based on the current hour.
    * @return "AM" if before noon, "PM" otherwise.
    */
   private String getSuffix() {
      return (hours < 12) ? "AM" : "PM";
   }

   /**
    * Returns the time in 12-hour format (h:mm:ss AM/PM).
    * @return A string representing the time in 12-hour format.
    */
   public String get12HourFormat() {
      int displayHour = hours;
      if (displayHour == 0) {
         displayHour = 12;  // Display midnight as 12
      } else if (displayHour > 12) {
         displayHour -= 12; // Convert to 12-hour format for PM times
      }

      return displayHour + ":" + pad(minutes) + ":" + pad(seconds) + " " + getSuffix();
   }

   /**
    * Pads a single-digit number with a leading zero for consistent time formatting.
    * @param value The value to pad.
    * @return A string with the value as two digits.
    */
   private String pad(int value) {
      return (value < 10) ? "0" + value : String.valueOf(value);
   }
}
