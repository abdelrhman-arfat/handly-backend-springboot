package com.project.handly.Mappers;

import com.project.handly.DTOs.Country.CountryDTO;
import com.project.handly.Entities.Country;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CountryMapper {
    Country toEntity(CountryDTO dto);
    CountryDTO toDTO(Country entity);
}
