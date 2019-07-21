package com.alexislavie.coding.assignment.becare.helper;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

@Component
public class DateHelper {

    public ZonedDateTime toZonedDateTime(LocalDateTime localDateTime, ZoneId target) {
        return toZonedDateTime(localDateTime, ZoneOffset.UTC, target);
    }

    public ZonedDateTime toZonedDateTime(LocalDateTime localDateTime, ZoneId source, ZoneId target) {
        return localDateTime.atZone(source).withZoneSameInstant(target);
    }
}
