package com.mindhub.cinema.utils.apiUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtils {


    public static String dateTimeFormatter(LocalDateTime date) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return date.format(format);
    }

    public static String timeFormatter(LocalDateTime date){
        DateTimeFormatter format = DateTimeFormatter.ofPattern("HH:mm");
        return date.format(format);
    }
}
