package com.project.handly.DTOs.Country;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class CountryDTO {
    @NotNull(message = "the arabic name mustn't be null")
    @Size(min=2, max=20 ,message =  "the arabic name must be bigger than 2 letters and less than 20 letter")
    private String nameAr;


    @NotNull(message = "the english name mustn't be null")
    @Size(min = 2, max = 20 , message =  "the english name must be bigger than 2 letters and less than 20 letter")
    private String nameEn;
}
