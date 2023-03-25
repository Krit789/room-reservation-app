package net.itkmitl.room.libs.phatsanphon.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.Date;

public class DateTime {
    private String rawDateTime;
    private Date dateTime;

    public DateTime(String rawDateTime) {
        this.rawDateTime = rawDateTime;
        this.format();
    }

    private String replaceText() {
        return rawDateTime.replace("T", " ");
    }

    private void format() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        java.util.Date utilDate = null;
        try {
            String dateTime = this.replaceText();
            utilDate = format.parse(dateTime);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        this.dateTime = new Date(utilDate.getTime());
    }

    public Date getDateTime() {
        return dateTime;
    }
}
