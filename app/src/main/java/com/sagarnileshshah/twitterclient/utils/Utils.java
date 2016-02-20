package com.sagarnileshshah.twitterclient.utils;

import org.joda.time.DateTime;
import org.joda.time.Interval;
import org.joda.time.Period;

import java.util.Date;

/**
 * Created by sshah on 2/18/16.
 */
public class Utils {


    public static String getFormattedRelativeTimestamp(Date date) {
        DateTime historicDateTime = new DateTime(date);
        DateTime now = new DateTime();

        Interval interval = new Interval(historicDateTime, now);

        Period period = interval.toPeriod();

        String elapsed;

        if (period.getYears() > 0) {
            elapsed = String.valueOf(period.getYears()) + "Y";
        } else if (period.getMonths() > 0) {
            elapsed = String.valueOf(period.getMonths()) + "M";
        } else if (period.getWeeks() > 0) {
            elapsed = String.valueOf(period.getWeeks()) + "W";
        } else if (period.getDays() > 0) {
            elapsed = String.valueOf(period.getDays()) + "d";
        } else if (period.getMinutes() > 0) {
            elapsed = String.valueOf(period.getMinutes()) + "m";
        } else {
            elapsed = String.valueOf(period.getSeconds()) + "s";
        }

        return elapsed;
    }
}
