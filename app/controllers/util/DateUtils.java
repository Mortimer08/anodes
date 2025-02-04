package controllers.util;

import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;

import java.util.Date;

public class DateUtils {

    public static Date monthFirstDate(final Date date) {
        return LocalDate.fromDateFields(date).dayOfMonth().withMinimumValue().toDate();
    }

    public static Date monthLastDate(final Date date) {
        return LocalDate.fromDateFields(date).dayOfMonth().withMaximumValue().toDate();
    }

    public static String monthFirstDay(final Date date) {
        return LocalDate.fromDateFields(date).dayOfMonth().withMinimumValue()
                .toString(DateTimeFormat.forPattern("yyyy-MM-dd"));
    }

    public static String monthLastDay(final Date date) {
        return LocalDate.fromDateFields(date).dayOfMonth().withMaximumValue()
                .toString(DateTimeFormat.forPattern("yyyy-MM-dd"));
    }

    public static Date plusDays(Date date, int days) {
        return LocalDate.fromDateFields(date).plusDays(days).toDate();
    }

    public static Date yearFirstDate(final Date date) {
        return LocalDate.fromDateFields(date).monthOfYear().withMinimumValue().dayOfMonth().withMinimumValue().toDate();
    }

    public static Date yearLastDate(final Date date) {
        return LocalDate.fromDateFields(date).monthOfYear().withMaximumValue().dayOfMonth().withMaximumValue().toDate();
    }
}
