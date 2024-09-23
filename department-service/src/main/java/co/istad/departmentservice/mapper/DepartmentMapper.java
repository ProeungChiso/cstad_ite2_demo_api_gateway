package co.istad.departmentservice.mapper;

import co.istad.departmentservice.domain.Department;
import co.istad.departmentservice.feature.department.dto.DepartmentResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DepartmentMapper {
    DepartmentResponse toDepartmentResponse(Department department);
}
