package net.itkmitl.room.libs.phatsanphon.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.Date;

public class DateTime {
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
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
        java.util.Date utilDate = null;
        try {
            String dateTime = this.replaceText();
            utilDate = sdf.parse(dateTime);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        this.dateTime = new Date(utilDate.getTime());
    }

    public Date getDateTime() {
        return dateTime;
    }
}
