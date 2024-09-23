package co.istad.userservice.mapper;

import co.istad.userservice.domain.User;
import co.istad.userservice.features.user.dto.UserResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserResponse toUserResponse(User user);
}
