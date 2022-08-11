package com.example.headachediary.tools;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * Class abstract Tools
 * Contains several utilities methods
 *
 */
public abstract class Tools {


    /**
     * Method to get and format the selected date
     * @param day
     * @param hours
     * @param minutes
     * @return date and time formated
     */
    public static LocalDateTime getDateFormat(LocalDate day, int hours, int minutes) {
        LocalTime time = LocalTime.of(hours, minutes);

        return LocalDateTime.of(day, time);
    }



}
