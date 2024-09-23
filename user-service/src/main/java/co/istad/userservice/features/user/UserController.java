package co.istad.userservice.features.user;

import co.istad.userservice.features.user.dto.UserCreateRequest;
import co.istad.userservice.features.user.dto.UserResponse;
import co.istad.userservice.features.user.dto.UserUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    void createUser(@RequestBody UserCreateRequest request) {
        userService.createUser(request);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/all")
    Page<UserResponse> getAllUsers(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        return userService.getAllUsers(page, size);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("{id}")
    UserResponse getUser(@PathVariable String id) {
        return userService.getUser(id);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/delete/{id}")
    void deleteUser(@PathVariable String id) {
        userService.deleteUser(id);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/update/{id}")
    void updateUser(@PathVariable String id, @RequestBody UserUpdateRequest request) {
        userService.updateUser(id, request);
    }

}
