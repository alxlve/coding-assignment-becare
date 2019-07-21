package com.alexislavie.coding.assignment.becare.mapper;

import com.alexislavie.coding.assignment.becare.dto.SessionCreationDto;
import com.alexislavie.coding.assignment.becare.dto.SessionDto;
import com.alexislavie.coding.assignment.becare.entity.Session;
import com.alexislavie.coding.assignment.becare.entity.SessionState;
import com.alexislavie.coding.assignment.becare.entity.User;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class SessionMapperTest {

    private SessionMapper sessionMapper = Mappers.getMapper(SessionMapper.class);

    @Test
    void toSession() {
        Map<String, Double> givenMeasures = new HashMap<>();
        givenMeasures.put("heartRate", 80d);
        givenMeasures.put("someOtherMeasure", 70d);
        givenMeasures.put("anotherMeasure", 50d);

        SessionCreationDto givenSessionCreationDto = SessionCreationDto.builder()
                .email("email@address.com")
                .startDate(ZonedDateTime.parse("2019-07-09T09:47:46.000+00:00"))
                .endDate(ZonedDateTime.parse("2019-07-09T12:01:06.000+02:00"))
                .measures(givenMeasures)
                .build();

        User givenUser = User.builder()
                .email("email@address.com")
                .build();

        Session resultSession = sessionMapper.toSession(givenSessionCreationDto, givenUser);

        assertAll(
                () -> assertThat(resultSession).isNotNull(),
                () -> assertThat(resultSession.getId()).isEqualTo(null),
                () -> assertThat(resultSession.getUser()).isEqualTo(givenUser),
                () -> assertThat(resultSession.getStartDate()).isEqualTo(
                        givenSessionCreationDto.getStartDate().withZoneSameInstant(ZoneOffset.UTC).toLocalDateTime()
                ),
                () -> assertThat(resultSession.getEndDate()).isEqualTo(
                        givenSessionCreationDto.getEndDate().withZoneSameInstant(ZoneOffset.UTC).toLocalDateTime()
                ),
                () -> assertThat(resultSession.getMeasures()).isEqualTo(givenSessionCreationDto.getMeasures()),
                () -> assertThat(resultSession.getSessionState()).isEqualTo(null),
                () -> assertThat(resultSession.getVersion()).isEqualTo(0L)
        );
    }

    @Test
    void toSessionDto() {
        Map<String, Double> givenMeasures = new HashMap<>();
        givenMeasures.put("heartRate", 80d);
        givenMeasures.put("someOtherMeasure", 70d);
        givenMeasures.put("anotherMeasure", 50d);

        Session givenSession = Session.builder()
                .id(UUID.randomUUID())
                .startDate(LocalDateTime.parse("2019-07-09T09:47:46.000"))
                .endDate(LocalDateTime.parse("2019-07-09T10:01:06.000"))
                .measures(givenMeasures)
                .sessionState(SessionState.RECEIVED)
                .version(0L)
                .build();

        SessionDto resultSessionDto = sessionMapper.toSessionDto(givenSession);

        assertAll(
                () -> assertThat(resultSessionDto).isNotNull(),
                () -> assertThat(resultSessionDto.getId()).isEqualTo(givenSession.getId()),
                () -> assertThat(resultSessionDto.getStartDate()).isEqualTo(
                        givenSession.getStartDate().atZone(ZoneOffset.UTC)),
                () -> assertThat(resultSessionDto.getEndDate()).isEqualTo(
                        givenSession.getEndDate().atZone(ZoneOffset.UTC)),
                () -> assertThat(resultSessionDto.getMeasures()).isEqualTo(givenSession.getMeasures()),
                () -> assertThat(resultSessionDto.getSessionState()).isEqualTo(givenSession.getSessionState()),
                () -> assertThat(resultSessionDto.getVersion()).isEqualTo(givenSession.getVersion())
        );
    }
}
