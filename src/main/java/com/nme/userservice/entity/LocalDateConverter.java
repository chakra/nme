package com.nme.userservice.entity;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.Date;

/**
 * Created by chakrabhandari on 7/01/2017.
 */
@Converter(autoApply = false)
public class LocalDateConverter implements AttributeConverter<LocalDate, Date> {

    @Override
    public Date convertToDatabaseColumn(LocalDate localDate) {
        return Date.from(localDate.atStartOfDay(ZoneId.of("UTC")).toInstant());
    }

    @Override
    public LocalDate convertToEntityAttribute(Date date) {
        Instant instant = date.toInstant();
        return LocalDate.from(instant);
    }
}
