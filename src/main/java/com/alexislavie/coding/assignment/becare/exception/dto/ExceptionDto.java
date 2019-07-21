package com.alexislavie.coding.assignment.becare.exception.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class ExceptionDto {

    @JsonIgnore
    private HttpStatus status;

    private String message;

    @JsonProperty
    public int getStatus() {
        return status.value();
    }
}
