package com.alexislavie.coding.assignment.becare.dto;

import com.alexislavie.coding.assignment.becare.entity.Audit;
import com.alexislavie.coding.assignment.becare.entity.SessionState;
import com.alexislavie.coding.assignment.becare.json.MeasuresDeserializer;
import com.alexislavie.coding.assignment.becare.json.MeasuresSerializer;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.*;
import java.time.ZonedDateTime;
import java.util.Map;
import java.util.UUID;

@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class SessionDto {

    private UUID id;

    @NotNull
    @Email
    @JsonProperty("user")
    @Size(max = 254)
    private String email;

    @NotNull
    @Past
    @JsonProperty("begin")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ")
    private ZonedDateTime startDate;

    @NotNull
    @Past
    @JsonProperty("end")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ")
    private ZonedDateTime endDate;

    @NotEmpty
    @JsonProperty("data")
    @JsonDeserialize(using = MeasuresDeserializer.class)
    @JsonSerialize(using = MeasuresSerializer.class)
    private Map<String, Double> measures;

    @NotNull
    @Enumerated(EnumType.STRING)
    private SessionState sessionState;

    @NotNull
    private Audit audit;

    private long version;
}
