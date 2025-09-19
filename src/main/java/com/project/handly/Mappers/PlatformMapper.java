package com.project.handly.Mappers;

import com.project.handly.DTOs.Platform.PlatformDTO;
import com.project.handly.Entities.Platforms;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PlatformMapper {
    Platforms toEntity(PlatformDTO dto);
    PlatformDTO toDTO(Platforms entity);
}
