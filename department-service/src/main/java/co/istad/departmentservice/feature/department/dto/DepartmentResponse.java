package co.istad.departmentservice.feature.department.dto;

public record DepartmentResponse(
        String uuid,
        String name,
        String description,
        String code
) {
}
