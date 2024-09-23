package co.istad.departmentservice.feature.department;

import co.istad.departmentservice.feature.department.dto.CreateDepartmentRequest;
import co.istad.departmentservice.feature.department.dto.DepartmentResponse;
import co.istad.departmentservice.feature.department.dto.UpdateDepartmentRequest;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface DepartmentService {
    void createDepartment(CreateDepartmentRequest createDepartmentRequest);
    Page<DepartmentResponse> getDepartments(int page, int size);
    DepartmentResponse getDepartmentByUuid(String uuid);
    void updateDepartmentByUuid(String uuid, UpdateDepartmentRequest updateDepartmentRequest);
    void deleteDepartment(String uuid);
}
