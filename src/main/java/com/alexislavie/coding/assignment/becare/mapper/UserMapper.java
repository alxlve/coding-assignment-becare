package com.alexislavie.coding.assignment.becare.mapper;

import com.alexislavie.coding.assignment.becare.dto.UserCreationDto;
import com.alexislavie.coding.assignment.becare.dto.UserDto;
import com.alexislavie.coding.assignment.becare.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "sessions", ignore = true)
    @Mapping(target = "version", ignore = true)
    User toUser(final UserCreationDto userCreationDto);

    UserDto toUserDto(final User user);
}
