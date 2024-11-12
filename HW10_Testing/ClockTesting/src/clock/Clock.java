package clock;

/**
 * The Clock class representing the time in 
 * both 12 hour h:mm:ss AM/PM and 
 * 24 hour hh:mm:ss formats.
 * 
 * @author mcpar
 *
 */
public class Clock {
    
    private int hours;
    private int minutes;
    private int seconds;
    

    /**
     * Constructor
     * 
     * @param hours - the initial hours
     * @param minutes - the initial minutes
     * @param seconds - the initial seconds
     */
    public Clock(int hours, int minutes, int seconds) {
        if (hours < 0 || hours > 23 || minutes < 0 || minutes > 59 || seconds < 0 || seconds > 59) { 
            throw new IllegalArgumentException("Invalid time values. Ensure hours (0-23), minutes (0-59), and seconds (0-59).");
        }
        
        this.hours = hours;
        this.minutes = minutes;
        this.seconds = seconds;
    }
    
    /**
     * Add an hour to the clock.
     */
    public void addHour() {
        if (hours == 23) {
            hours = 0;
        } else {
            hours++;
        }
    }
    
    /**
     * Add a minute to the clock.
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
     * Add a second to the clock.
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
     * Get the time in 24 hour format: hh:mm:ss
     * @return - the 24 hour format time
     */
    public String get24HourFormat() {
        return pad(hours) + ":" + pad(minutes) + ":" + pad(seconds);
    }

    /**
     * Get the suffix
     * @return - the suffix
     */
    private String getSuffix() {
        return (hours < 12) ? "AM" : "PM";
    }

    /**
     * Get the 12 hour format: h:mm:ss AM/PM
     * @return - the 12 hour format
     */
    public String get12HourFormat() {
        int displayHours = hours;

        if (displayHours == 0) {
            displayHours = 12; // Midnight in 12-hour format
        } else if (displayHours > 12) {
            displayHours -= 12; // PM times in 12-hour format
        }

        return displayHours + ":" + pad(minutes) + ":" + pad(seconds) + " " + getSuffix();
    }

    /**
     * Pads a value as a two-digit representation.
     * e.g. 9 is "09"
     * @param value - the value to pad
     * @return - the value as two-digits.
     */
    private String pad(int value) {
        return (value < 10) ? "0" + value : String.valueOf(value);
    }
    
}
