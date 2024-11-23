package com.gymbuddy.service.integration.utils;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class DateUtils
{
    public static LocalDateTime truncateToMinutes(Date date)
    {
        return date.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime()
                .withSecond(0)
                .withNano(0);
    }

    public static LocalDateTime truncateToMinutes(LocalDateTime dateTime)
    {
        return dateTime.withSecond(0).withNano(0);
    }
}
