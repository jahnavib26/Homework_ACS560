package com.testing;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.debug.Clock;

class clockTest {

    private Clock clock;

    @BeforeEach
    void setup() {
        // Initialize clock to a standard time (13:45:30) before each test
        clock = new Clock(13, 45, 30);
    }

    @Test
    void testGet24HourFormat() {
        // Test the 24-hour format with standard input
        assertEquals("13:45:30", clock.get24HourFormat());

        // Test edge case for midnight
        clock = new Clock(0, 0, 0);
        assertEquals("00:00:00", clock.get24HourFormat());

        // Test edge case for the end of day
        clock = new Clock(23, 59, 59);
        assertEquals("23:59:59", clock.get24HourFormat());
    }

    @Test
    void testGet12HourFormat() {
        // Test the 12-hour format for PM time
        assertEquals("1:45:30 PM", clock.get12HourFormat());

        // Test the 12-hour format for AM time
        clock = new Clock(3, 15, 10);
        assertEquals("3:15:10 AM", clock.get12HourFormat());

        // Test edge case of midnight (00:00:00 in 24-hour time)
        clock = new Clock(0, 0, 0);
        assertEquals("12:00:00 AM", clock.get12HourFormat());

        // Test edge case of noon (12:00:00 in 24-hour time)
        clock = new Clock(12, 0, 0);
        assertEquals("12:00:00 PM", clock.get12HourFormat());

        // Test edge case of 11 PM (23:00:00 in 24-hour time)
        clock = new Clock(23, 0, 0);
        assertEquals("11:00:00 PM", clock.get12HourFormat());
    }

    @Test
    void testAddHour() {
        // Test incrementing hours within the normal range
        clock.addHour();
        assertEquals("14:45:30", clock.get24HourFormat());

        // Test hour rollover after 23
        clock = new Clock(23, 0, 0);
        clock.addHour();
        assertEquals("00:00:00", clock.get24HourFormat());
    }

    @Test
    void testAddMinute() {
        // Test adding a minute within the normal range
        clock.addMinute();
        assertEquals("13:46:30", clock.get24HourFormat());

        // Test minute rollover after 59
        clock = new Clock(13, 59, 30);
        clock.addMinute();
        assertEquals("14:00:30", clock.get24HourFormat());

        // Test adding a minute at the end of day
        clock = new Clock(23, 59, 30);
        clock.addMinute();
        assertEquals("00:00:30", clock.get24HourFormat());
    }

    @Test
    void testAddSecond() {
        // Test adding a second within the normal range
        clock.addSecond();
        assertEquals("13:45:31", clock.get24HourFormat());

        // Test second rollover after 59
        clock = new Clock(13, 45, 59);
        clock.addSecond();
        assertEquals("13:46:00", clock.get24HourFormat());

        // Test adding a second at the end of day
        clock = new Clock(23, 59, 59);
        clock.addSecond();
        assertEquals("00:00:00", clock.get24HourFormat());
    }

    @Test
    void testInputValidation() {
        // Test validation with hours, minutes, and seconds out of range
        assertThrows(IllegalArgumentException.class, () -> new Clock(-1, 0, 0));
        assertThrows(IllegalArgumentException.class, () -> new Clock(0, -1, 0));
        assertThrows(IllegalArgumentException.class, () -> new Clock(0, 0, -1));
        assertThrows(IllegalArgumentException.class, () -> new Clock(25, 0, 0));
        assertThrows(IllegalArgumentException.class, () -> new Clock(0, 60, 0));
        assertThrows(IllegalArgumentException.class, () -> new Clock(0, 0, 60));
    }

    @Test
    void testGet12HourFormatSuffix() {
        // Test AM suffix
        clock = new Clock(5, 30, 0);
        assertEquals("5:30:00 AM", clock.get12HourFormat());

        // Test PM suffix
        clock = new Clock(17, 0, 0);
        assertEquals("5:00:00 PM", clock.get12HourFormat());
    }


    @Test
    void testEdgeCases() {
        // Test edge case of midnight (00:00:00 in 24-hour time)
        clock = new Clock(0, 0, 0);
        assertEquals("00:00:00", clock.get24HourFormat());
        assertEquals("12:00:00 AM", clock.get12HourFormat());

        // Test edge case of noon (12:00:00 in 24-hour time)
        clock = new Clock(12, 0, 0);
        assertEquals("12:00:00", clock.get24HourFormat());
        assertEquals("12:00:00 PM", clock.get12HourFormat());
    }
}
