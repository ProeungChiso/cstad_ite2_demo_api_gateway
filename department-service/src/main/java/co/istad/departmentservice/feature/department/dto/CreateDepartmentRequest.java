package co.istad.departmentservice.feature.department.dto;

public record CreateDepartmentRequest(
        String name,
        String description
) {
}
