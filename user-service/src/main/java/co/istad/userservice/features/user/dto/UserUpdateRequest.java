package co.istad.userservice.features.user.dto;

public record UserUpdateRequest(
        String address,
        String phone
) {
}
