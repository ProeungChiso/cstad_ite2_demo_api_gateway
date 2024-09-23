package co.istad.departmentservice.feature.department.dto;

public record UpdateDepartmentRequest(
        String name,
        String description
) {
}
