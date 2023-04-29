package net.itkmitl.room.libs.phatsanphon.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTime {
    private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private String rawDateTime;
    private Date dateTime;
    private static int timezone = 7;

    public DateTime(String rawDateTime) {
        this.rawDateTime = rawDateTime;
        this.format();
    }

    public DateTime(Date date) {
        this.dateTime = date;
    }

    public DateTime(int year, int month, int date) {
        this(year, month, date, 0, 0, 0);
    }

    public DateTime(int year, int month, int date, int hours, int minutes, int seconds) {
        this.setYear(year);
        this.setMonth(month);
        this.setDate(date);
        this.setHours(hours);
        this.setMinutes(minutes);
        this.setSeconds(seconds);
    }

    public DateTime() {
    }

    private String replaceText() {
        int countColon = rawDateTime.length() - rawDateTime.replace(":", "").length();
        if (countColon != 2){
            rawDateTime += ":00";
        }
        return rawDateTime.replace("T", " ");
    }

    private void format() {
        try {
            String dateTime = this.replaceText();
            Date finalDateTime = sdf.parse(dateTime);
            this.setDateTime(finalDateTime);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    private Date getDateTime() {
        return dateTime;
    }

    private void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public static int getTimezone() {
        return DateTime.timezone;
    }

    public static void setTimezone(int timezone) {
        DateTime.timezone = timezone;
    }

    /*
     * Return DateTime in milliseconds
     */
    public long getTime() {
        return this.getDateTime().getTime();
    }

    public void setTime(long time) {
        this.getDateTime().setTime(time);
    }

    public int getHours() {
        return this.getDateTime().getHours();
    }

    public void setHours(int hours) {
        this.getDateTime().setHours(hours);
    }

    public int getMinutes() {
        return this.getDateTime().getMinutes();
    }

    public void setMinutes(int minutes) {
        this.getDateTime().setMinutes(minutes);
    }

    public int getSeconds() {
        return this.getDateTime().getSeconds();
    }

    public void setSeconds(int seconds) {
        this.getDateTime().setSeconds(seconds);
    }

    public int getDayOfWeek() {
        return this.getDateTime().getDay();
    }

    public int getDate() {
        return this.getDateTime().getDate();
    }

    public void setDate(int date) {
        this.getDateTime().setDate(date);
    }

    public int getMonth() {
        return this.getDateTime().getMonth() + 1;
    }

    public void setMonth(int month) {
        this.getDateTime().setMonth(month - 1);
    }

    public int getYear() {
        return this.getDateTime().getYear() + 1900;
    }

    public void setYear(int year) {
        this.getDateTime().setYear(year - 1900);
    }

    /*
     * Return String into database format
     */
    @Override
    public String toString() {
        return String.format(
                "%d-%02d-%02d %02d:%02d:%02d",
                this.getYear(), this.getMonth(), this.getDate(),
                this.getHours(), this.getMinutes(), this.getSeconds()
        );
    }

    public int compare(DateTime dt) {
        return this.getDateTime().compareTo(dt.getDateTime());
    }
}
