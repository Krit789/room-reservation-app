package net.itkmitl.room.libs.peeranat.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class StringUtil {

	private static SimpleDateFormat formatFullDateTime;
	private static SimpleDateFormat formatSafeFullDateTime;
	private static SimpleDateFormat formatDate;
	private static SimpleDateFormat formatTime;

	public static String getFullDateTimeString(Date date) {
		if (formatFullDateTime == null) {
			formatFullDateTime = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		}
		return formatFullDateTime.format(date);
	}

	public static String getFullDateTimeString(long unixTime) {
		return getFullDateTimeString(new Date(unixTime * 1000));
	}

	public static String getDirectoryNameSafeFullDateTimeString(Date date) {
		if (formatSafeFullDateTime == null) {
			formatSafeFullDateTime = new SimpleDateFormat("dd-MM-yyyy_HH-mm-ss");
		}
		return formatSafeFullDateTime.format(date);
	}

	public static Date getDateTimeByDirectoryNameSafeString(String datetimeString) {
		try {
			String[] split = datetimeString.split("_");
			String[] date = split[0].split("-");
			String[] time = split[1].split("-");

			Calendar calendar = Calendar.getInstance();
			calendar.set(Calendar.MONTH, Integer.parseInt(date[0]));
			calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(date[1]));
			calendar.set(Calendar.YEAR, Integer.parseInt(date[2]));

			calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(time[0]));
			calendar.set(Calendar.MINUTE, Integer.parseInt(time[1]));
			calendar.set(Calendar.SECOND, Integer.parseInt(time[2]));

			return calendar.getTime();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String getDateString(Date date) {
		if (formatDate == null) {
			formatDate = new SimpleDateFormat("dd/MM/yyyy");
		}
		return formatDate.format(date);
	}

	public static String getDateString(long unixTime) {
		return getDateString(new Date(unixTime * 1000));
	}

	public static String getTimeString(Date date) {
		if (formatTime == null) {
			formatTime = new SimpleDateFormat("HH:mm:ss");
		}
		return formatTime.format(date);
	}

	public static String getTimeString(long unixTime) {
		return getTimeString(new Date(unixTime));
	}

	public static String getTimeAsStringInShort(int time) {
		// Time is in seconds
		StringBuilder sb = new StringBuilder();

		int days, hours, minutes, seconds;

		days = (int) Math.ceil(time / 86400);
		time = time - (days * 86400);

		hours = (int) Math.ceil(time / 3600);
		time = time - (hours * 3600);

		minutes = (int) Math.ceil(time / 60);
		time = time - (minutes * 60);

		seconds = time;

		if (days > 0) {
			sb.append(days);
			if(days == 1){
				sb.append(" day");
			}
			else sb.append(" days");
		}
		else if (hours > 0) {
			sb.append(hours);
			sb.append('.');
			sb.append(minutes);
			if(hours == 1){
				sb.append(" hour");
			}
			else sb.append(" hours");
		}
		else if (minutes > 0) {
			sb.append(minutes);
			sb.append('.');
			sb.append((int) (((float) seconds / 60) * 10));
			if(minutes == 1){
				sb.append(" min");
			}
			else sb.append(" mins");
		}

		else if (seconds > 0) {
			sb.append(seconds);
			if(seconds == 1){
				sb.append(" sec");
			}
			else sb.append(" secs");
		}
		if (sb.length() == 0)
			return "0 sec";
		return sb.toString();
	}

	public static String getTimeAsString(int time) {
		StringBuilder sb = new StringBuilder();

		int days, hours, minutes, seconds;

		days = (int) Math.ceil(time / 86400);
		time = time - (days * 86400);

		hours = (int) Math.ceil(time / 3600);
		time = time - (hours * 3600);

		minutes = (int) Math.ceil(time / 60);
		time = time - (minutes * 60);

		seconds = time;

		if (days > 0) {

			if (days == 1) {
				sb.append(days);
				sb.append(" day ");
			} else {
				sb.append(days);
				sb.append(" days ");
			}
		}

		if (hours > 0) {

			if (hours == 1) {
				sb.append(hours);
				sb.append(" hour ");
			} else {
				sb.append(hours);
				sb.append(" hours ");
			}
		}

		if (minutes > 0) {
			if (minutes == 1) {
				sb.append(minutes);
				sb.append(" minute ");
			} else {
				sb.append(minutes);
				sb.append(" minutes ");
			}
		}

		if (seconds > 0) {
			if (minutes > 0 || hours > 0) {
				sb.append("and ");
			}

			if (seconds == 1) {
				sb.append(seconds);
				sb.append(" second");
			} else {
				sb.append(seconds);
				sb.append(" seconds");
			}
		}
		if (sb.length() == 0)
			return "0 Sec";
		return sb.toString();
	}

	public static String getTimeAsStringThaiInShort(int time) {
		// Time is in seconds
		StringBuilder sb = new StringBuilder();

		int days, hours, minutes, seconds;

		days = (int) Math.ceil(time / 86400);
		time = time - (days * 86400);

		hours = (int) Math.ceil(time / 3600);
		time = time - (hours * 3600);

		minutes = (int) Math.ceil(time / 60);
		time = time - (minutes * 60);

		seconds = time;

		if (days > 0) {
			sb.append(days);
			sb.append(" วัน");
		}
		else if (hours > 0) {
			sb.append(hours);
			sb.append('.');
			sb.append(minutes);
			sb.append(" ชั่วโมง");
		}
		else if (minutes > 0) {
			sb.append(minutes);
			sb.append('.');
			sb.append((int) (((float) seconds / 60) * 10));
			sb.append(" นาที");
		}

		else if (seconds > 0) {
			sb.append(seconds);
			sb.append(" วินาที");
		}
		if (sb.length() == 0)
			return "0 วินาที";
		return sb.toString();
	}

	public static String getTimeAsStringThai(int time) {
		StringBuilder sb = new StringBuilder();

		int days, hours, minutes, seconds;

		days = (int) Math.ceil(time / 86400);
		time = time - (days * 86400);

		hours = (int) Math.ceil(time / 3600);
		time = time - (hours * 3600);

		minutes = (int) Math.ceil(time / 60);
		time = time - (minutes * 60);

		seconds = time;

		if (days > 0) {
			sb.append(days);
			sb.append(" วัน ");
		}

		if (hours > 0) {
			sb.append(hours);
			sb.append(" ชั่วโมง ");
		}

		if (minutes > 0) {
			sb.append(minutes);
			sb.append(" นาที ");
		}

		if (seconds > 0) {
			sb.append(seconds);
			sb.append(" วินาที");
		}
		if (sb.length() == 0)
			return "0 วินาที";
		return sb.toString();
	}

}
