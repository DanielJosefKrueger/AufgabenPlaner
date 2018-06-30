package de.darktech;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Util {



    /**
     * add days to date in java
     * @param date
     * @param days
     * @return
     */
    public static Date addDays(Date date, int days) {
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(date);
        cal.add(Calendar.DATE, days);

        return cal.getTime();
    }
}
