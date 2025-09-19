package com.project.handly.Mappers;

import com.project.handly.DTOs.Channel.ChannelDTO;
import com.project.handly.Entities.Channels;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ChannelMapper {
    Channels toEntity(ChannelDTO dto);
    ChannelDTO toDTO(Channels entity);
}
