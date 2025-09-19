package com.project.handly.Mappers;

<<<<<<< HEAD
import com.project.handly.DTOs.User.UserDTO;
import com.project.handly.Entities.User;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface UserMapper {
=======
import com.project.handly.DTOs.UserDTO;
import com.project.handly.Entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
>>>>>>> 19f644cf08a16ec006c41ec5432fae67c3da07fb
    UserDTO toDTO(User user);
    User toEntity(UserDTO userDTO);
}
