package co.istad.userservice.features.user;

import co.istad.userservice.features.user.dto.UserCreateRequest;
import co.istad.userservice.features.user.dto.UserResponse;
import co.istad.userservice.features.user.dto.UserUpdateRequest;
import org.springframework.data.domain.Page;

public interface UserService {

    void createUser(UserCreateRequest request);
    Page<UserResponse> getAllUsers(int page, int size);
    UserResponse getUser(String id);
    void deleteUser(String id);
    void updateUser(String id, UserUpdateRequest request);

}
