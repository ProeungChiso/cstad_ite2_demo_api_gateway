package co.istad.departmentservice.feature.department;

import co.istad.departmentservice.domain.Department;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
    Optional<Department> findByUuid(String uuid);
    Boolean existsByName(String name);
}
