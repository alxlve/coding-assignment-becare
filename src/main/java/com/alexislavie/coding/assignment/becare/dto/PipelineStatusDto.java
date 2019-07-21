package com.alexislavie.coding.assignment.becare.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.time.ZonedDateTime;

@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class PipelineStatusDto {

    @NotNull
    @Past
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ")
    private ZonedDateTime timestamp;

    @NotBlank
    private String message;
}
