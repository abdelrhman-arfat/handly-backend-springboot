package com.project.handly.Mappers;

import com.project.handly.DTOs.User.OauthDTO;
import com.project.handly.Entities.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OauthMapper {
    User toEntity(OauthDTO oauthDTO);
    OauthDTO toDTO(User user);
}
