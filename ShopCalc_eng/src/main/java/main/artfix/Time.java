package main.artfix;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Time {
    public static String nowTime;
    public static String hourTime;
    public static String ymdTime;

    public static void now() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime currentDateTime = LocalDateTime.now();
        nowTime = currentDateTime.format(formatter);
    }

    public static void hour() {
        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("HH");
        LocalDateTime currentDateTime1 = LocalDateTime.now();
        hourTime = currentDateTime1.format(formatter1);
    }

    public static void ymd() {
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime currentDateTime2 = LocalDateTime.now();
        ymdTime = currentDateTime2.format(formatter2);
    }
}
