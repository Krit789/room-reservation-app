package net.itkmitl.room.libs.phatsanphon.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTime {
    private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private final String rawDateTime;
    private Date dateTime;

    private static int timezone = 7;

    public DateTime(String rawDateTime) {
        this.rawDateTime = rawDateTime;
        this.format();
    }

    private String replaceText() {
        return rawDateTime.replace("T", " ");
    }

    private void format() {
        Date utilDate = null;
        try {
            String dateTime = this.replaceText();
            utilDate = sdf.parse(dateTime);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        this.setDateTime(utilDate);
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

    public int getHours() {
        return this.getDateTime().getHours();
    }

    public int getMinutes() {
        return this.getDateTime().getMinutes();
    }

    public int getSeconds() {
        return this.getDateTime().getSeconds();
    }

    public int getDayOfWeek() {
        return this.getDateTime().getDay();
    }

    public int getDate() {
        return this.getDateTime().getDate();
    }

    public int getMonth() {
        return this.getDateTime().getMonth() + 1;
    }

    public int getYear() {
        return this.getDateTime().getYear() + 1900;
    }

    @Override
    public String toString() {
        return String.format(
                "%d-%d-%d %d:%d:%d",
                this.getDate(), this.getMonth(), this.getYear(),
                this.getHours(), this.getMinutes(), this.getSeconds()
        );
    }
}
