package com.example.shop.util;

import org.apache.commons.lang3.StringUtils;

import java.time.*;
import java.time.format.DateTimeFormatter;

/**
 * @author hyk
 * @date 2019/5/16
 * @Description
 */
public class DateUtil {

    public static final String FULL_TIME_PATTERN = "yyyyMMddHHmmss";

    public static final String FULL_DATE_PATTERN = "yyyyMMdd";

    public static final String FULL_TIME_SPLIT_PATTERN = "yyyy-MM-dd HH:mm:ss";

    public static final String CST_TIME_PATTERN = "EEE MMM dd HH:mm:ss zzz yyyy";

    public static final String FORMAT_TO_DAY = "yyyy-MM-dd";

    public static final int MIN_MONTH = 1;

    public static final int MAX_MONTH = 12;


    public static String formatInstant(Instant instant, String format) {
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
        return localDateTime.format(DateTimeFormatter.ofPattern(format));
    }

    public static LocalDateTime parseTime(Long timestamp) {
        if (timestamp == null) {
            return null;
        }
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(timestamp), ZoneId.systemDefault());
    }

    public static LocalDateTime parseTime(String time) {
        return parseTime(time, FULL_TIME_SPLIT_PATTERN);
    }


    public static LocalDateTime parseTime(String time, String format) {
        if (StringUtils.isEmpty(time)) {
            return null;
        }
        return LocalDateTime.parse(time, DateTimeFormatter.ofPattern(format));
    }

    public static LocalDate parseDate(String time, String format) {
        if (StringUtils.isEmpty(time)) {
            return null;
        }
        return LocalDate.parse(time, DateTimeFormatter.ofPattern(format));
    }

    public static LocalDate parseDate(String time) {
        if (StringUtils.isEmpty(time)) {
            return null;
        }
        return LocalDate.parse(time, DateTimeFormatter.ofPattern(FORMAT_TO_DAY));
    }

    public static Long parseLong(LocalDateTime dateTime) {
        if (dateTime == null) {
            return null;
        }
        return dateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }

    public static Long parseLong(LocalDate date) {
        if (date == null) {
            return null;
        }
        return date.atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }

    public static String parseString(LocalDateTime localDateTime) {
        return parseString(localDateTime, FULL_TIME_SPLIT_PATTERN);
    }

    public static String parseString(LocalDateTime localDateTime, String format) {
        return localDateTime.format(DateTimeFormatter.ofPattern(format));
    }

    public static String parseString(LocalDate localDate, String format) {
        return localDate.format(DateTimeFormatter.ofPattern(format));
    }

    public static String parseString(LocalDate localDate) {
        return parseString(localDate, FORMAT_TO_DAY);
    }

    public static LocalDateTime getStartTimeByDay(LocalDateTime localDateTime) {
        return LocalDateTime.of(localDateTime.toLocalDate(), LocalTime.MIN);
    }

    public static boolean judgeExistIntersection(LocalDate startDate1, LocalDate endDate1, LocalDate startDate2, LocalDate endDate2) {
        return judgeExistIntersection(startDate1.atStartOfDay(), endDate1.plusDays(1).atStartOfDay(), startDate2.atStartOfDay(), endDate2.plusDays(1).atStartOfDay());
    }

    public static boolean judgeExistIntersection(LocalDateTime startTime1, LocalDateTime endTime1, LocalDateTime startTime2, LocalDateTime endTime2) {
        return startTime1.isBefore(endTime2) && endTime1.isAfter(startTime2);
    }

}
