package com.smartjobs.test.users.domain.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@Builder
public class PhoneRequest {

    @NotNull(message = "es obligatorio")
    @NotEmpty(message = "no debe ser vacío")
    @Size(min =1, max =12, message = "la cantidad de caracteres de estar entre 1 y 12")
    private String number;
    @NotNull(message = "es obligatorio")
    @NotEmpty(message = "no debe ser vacío")
    @Size(min =1, max =5, message = "la cantidad de caracteres de estar entre 1 y 5")
    private String cityCode;
    @NotNull(message = "es obligatorio")
    @NotEmpty(message = "no debe ser vacío")
    @Size(min =1, max =5, message = "la cantidad de caracteres de estar entre 1 y 5")
    private String countryCode;
}
