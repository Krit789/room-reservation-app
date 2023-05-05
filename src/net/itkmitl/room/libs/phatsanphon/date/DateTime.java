package net.itkmitl.room.libs.phatsanphon.date;

import java.util.Calendar;
import java.util.Date;

public class DateTime {

	private Calendar calendar;
	private Date dateTime;
	private static int timezone = 7;

	public DateTime(long unixTime) {
		this(new Date(unixTime));
	}

	public DateTime(Date date) {
		this.dateTime = date;
		calendar.setTime(dateTime);
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
		this.calendar = Calendar.getInstance();
	}

	public DateTime() {
	}

	private Calendar getDateTime() {
		return calendar;
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
		return this.getDateTime().getTimeInMillis();
	}

	public void setTime(long time) {
		this.getDateTime().setTimeInMillis(time);
	}

	public int getHours() {
		return calendar.get(Calendar.HOUR);
	}

	public void setHours(int hours) {
		this.getDateTime().set(Calendar.MONTH, hours);
	}

	public int getMinutes() {
		return calendar.get(Calendar.MINUTE);
	}

	public void setMinutes(int minutes) {
		this.getDateTime().set(Calendar.MINUTE, minutes);
	}

	public int getSeconds() {
		return calendar.get(Calendar.SECOND);
	}

	public void setSeconds(int seconds) {
		this.getDateTime().set(Calendar.SECOND, seconds);
	}

	public int getDayOfWeek() {
		return calendar.get(Calendar.DAY_OF_WEEK);
	}

	public int getDate() {
		return calendar.get(Calendar.DATE);
	}

	public void setDate(int date) {
		this.getDateTime().set(Calendar.DATE, date);
	}

	public int getMonth() {
		return calendar.get(Calendar.MONTH);
	}

	public void setMonth(int month) {
		calendar.set(Calendar.MONTH, month - 1);
	}

	public int getYear() {
		return calendar.get(Calendar.YEAR);
	}

	public void setYear(int year) {
		calendar.set(Calendar.YEAR, year - 1900);
	}
	
	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}

	/*
	 * Return String into database format
	 */
	@Override
	public String toString() {
		return String.format("%d-%02d-%02d %02d:%02d:%02d", this.getYear(), this.getMonth(), this.getDate(),
				this.getHours(), this.getMinutes(), this.getSeconds());
	}

	public int compare(DateTime dt) {
		return this.getDateTime().compareTo(dt.getDateTime());
	}
}
