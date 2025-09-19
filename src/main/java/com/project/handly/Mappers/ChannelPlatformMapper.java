package com.project.handly.Mappers;

import com.project.handly.DTOs.ChannelPlatform.ChannelPlatformDTO;
import com.project.handly.Entities.ChannelPlatforms;
import com.project.handly.Entities.Channels;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ChannelPlatformMapper {

    ChannelPlatformDTO toDTO(Channels entity);
    ChannelPlatforms toEntity(ChannelPlatformDTO dto);

}
