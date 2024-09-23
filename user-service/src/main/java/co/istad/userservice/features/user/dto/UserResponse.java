package co.istad.userservice.features.user.dto;

public record UserResponse(
        String id,
        String username,
        String address,
        String phone
) {
}
