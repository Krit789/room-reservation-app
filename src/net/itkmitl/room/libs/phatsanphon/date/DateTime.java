package net.itkmitl.room.libs.phatsanphon.date;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;

public class DateTime {
    private static int timezone = 7;
    private Date dateTime;

    public DateTime(long unixTime) {
        this(new Date(unixTime));
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

    public static int getTimezone() {
        return DateTime.timezone;
    }

    public static void setTimezone(int timezone) {
        DateTime.timezone = timezone;
    }

    public LocalDate toLocalDate() {
        LocalDate date = dateTime.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return date;
    }

    public LocalTime toLocalTime() {
        LocalTime date = dateTime.toInstant().atZone(ZoneId.systemDefault()).toLocalTime();
        return date;
    }

    private Date getDateTime() {
        return dateTime;
    }

    private void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
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

    public DateTime addMillis(long millis) {
        DateTime myTime = new DateTime(this.getTime());
        myTime.setTime(myTime.getTime() + (1800L * 100L));
        return myTime;
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
