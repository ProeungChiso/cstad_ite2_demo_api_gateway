package co.istad.userservice.features.user.dto;

public record UserCreateRequest(
        String username,
        String email,
        String address,
        String phone
) {
}
