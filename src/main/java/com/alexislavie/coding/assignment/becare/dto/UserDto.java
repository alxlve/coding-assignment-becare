package com.alexislavie.coding.assignment.becare.dto;

import com.alexislavie.coding.assignment.becare.entity.Audit;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.UUID;

@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class UserDto {

    private UUID id;

    @NotNull
    @Email
    @Size(max = 254)
    private String email;

    @NotBlank
    @Size(max = 64)
    private String firstName;

    @NotBlank
    @Size(max = 64)
    private String lastName;

    @NotNull
    private Audit audit;

    private long version;
}
