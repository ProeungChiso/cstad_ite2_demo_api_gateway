package co.istad.departmentservice.feature.department;

import co.istad.departmentservice.domain.Department;
import co.istad.departmentservice.feature.department.dto.CreateDepartmentRequest;
import co.istad.departmentservice.feature.department.dto.DepartmentResponse;
import co.istad.departmentservice.feature.department.dto.UpdateDepartmentRequest;
import co.istad.departmentservice.mapper.DepartmentMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    public final DepartmentRepository departmentRepository;
    public final DepartmentMapper departmentMapper;

    @Override
    public void createDepartment(CreateDepartmentRequest createDepartmentRequest) {

        if(departmentRepository.existsByName(createDepartmentRequest.name())){
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Department already exists");
        }

        Department department = new Department();

        department.setUuid(UUID.randomUUID().toString());
        department.setName(createDepartmentRequest.name());
        department.setDescription(createDepartmentRequest.description());
        department.setCode("DEPT_"+UUID.randomUUID());

        departmentRepository.save(department);
    }

    @Override
    public Page<DepartmentResponse> getDepartments(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        List<Department> departments = departmentRepository.findAll();
        List<DepartmentResponse> departmentResponses = departments
                .stream()
                .map(departmentMapper::toDepartmentResponse)
                .toList();

        return new PageImpl<>(departmentResponses, pageable, departments.size());
    }

    @Override
    public DepartmentResponse getDepartmentByUuid(String uuid) {
        Optional<Department> department = departmentRepository.findByUuid(uuid);

        if (department.isPresent()) {
            return departmentMapper.toDepartmentResponse(department.get());
        }else{
            throw new EntityNotFoundException(
                    "Department not found for UUID: " + uuid
            );
        }
    }

    @Override
    public void updateDepartmentByUuid(String uuid, UpdateDepartmentRequest updateDepartmentRequest) {
        Department department = departmentRepository.findByUuid(uuid).orElseThrow(
                ()-> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Department not found for UUID: " + uuid
                )
        );

        department.setName(updateDepartmentRequest.name());
        department.setDescription(updateDepartmentRequest.description());
        departmentRepository.save(department);
    }

    @Override
    public void deleteDepartment(String uuid) {
        Department department = departmentRepository.findByUuid(uuid).orElseThrow(
                ()-> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Department not found for UUID: " + uuid
                )
        );
        departmentRepository.delete(department);
    }
}
