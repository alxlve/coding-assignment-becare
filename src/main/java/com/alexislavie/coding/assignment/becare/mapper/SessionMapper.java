package com.alexislavie.coding.assignment.becare.mapper;

import com.alexislavie.coding.assignment.becare.dto.SessionCreationDto;
import com.alexislavie.coding.assignment.becare.dto.SessionDto;
import com.alexislavie.coding.assignment.becare.entity.Session;
import com.alexislavie.coding.assignment.becare.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

@Mapper(componentModel = "spring")
public interface SessionMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", source = "user")
    @Mapping(target = "startDate", source = "sessionCreationDto.startDate", qualifiedByName = "toLocalDateTimeUTC")
    @Mapping(target = "endDate", source = "sessionCreationDto.endDate", qualifiedByName = "toLocalDateTimeUTC")
    @Mapping(target = "measures", source = "sessionCreationDto.measures")
    @Mapping(target = "sessionState", ignore = true)
    Session toSession(final SessionCreationDto sessionCreationDto, final User user);


    @Mapping(target = "email", source = "user.email")
    @Mapping(target = "startDate", source = "startDate", qualifiedByName = "toZonedDateTimeUTC")
    @Mapping(target = "endDate", source = "endDate", qualifiedByName = "toZonedDateTimeUTC")
    SessionDto toSessionDto(final Session session);

    @Named("toLocalDateTimeUTC")
    default LocalDateTime toLocalDateTimeUTC(final ZonedDateTime zonedDateTime) {
        return zonedDateTime.withZoneSameInstant(ZoneOffset.UTC).toLocalDateTime();
    }

    @Named("toZonedDateTimeUTC")
    default ZonedDateTime toZonedDateTimeUTC(final LocalDateTime localDateTime) {
        return localDateTime.atZone(ZoneOffset.UTC);
    }
}
