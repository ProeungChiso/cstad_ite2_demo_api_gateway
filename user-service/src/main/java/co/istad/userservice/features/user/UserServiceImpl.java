package co.istad.userservice.features.user;

import co.istad.userservice.domain.User;
import co.istad.userservice.features.user.dto.UserCreateRequest;
import co.istad.userservice.features.user.dto.UserResponse;
import co.istad.userservice.features.user.dto.UserUpdateRequest;
import co.istad.userservice.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.ErrorResponseException;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public void createUser(UserCreateRequest request) {
        User user = new User();

        if(userRepository.existsByUsername(request.username()) && userRepository.existsByEmail(request.email())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        }

        user.setUsername(request.username());
        user.setEmail(request.email());
        user.setAddress(request.address());
        user.setPhone(request.phone());

        userRepository.save(user);
    }

    @Override
    public Page<UserResponse> getAllUsers(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        List<User> users = userRepository.findAll();
        List<UserResponse> userResponses = users
                .stream()
                .map(userMapper::toUserResponse)
                .toList();

        return new PageImpl<>(userResponses, pageable, userResponses.size());
    }

    @Override
    public UserResponse getUser(String id) {

        User user = userRepository.findById(id).orElseThrow(
                ()-> new ErrorResponseException(HttpStatus.NOT_FOUND)
        );

        return userMapper.toUserResponse(user);
    }

    @Override
    public void deleteUser(String id) {

        if(!userRepository.existsById(id)) {
            throw new ErrorResponseException(HttpStatus.NOT_FOUND);
        }

        userRepository.deleteById(id);
    }

    @Override
    public void updateUser(String id, UserUpdateRequest request) {

        User user = userRepository.findById(id).orElseThrow(
                () -> new ErrorResponseException(HttpStatus.NOT_FOUND)
        );

        user.setAddress(request.address());
        user.setPhone(request.phone());
        userRepository.save(user);
    }
}
