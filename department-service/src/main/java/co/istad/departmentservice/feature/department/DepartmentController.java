package co.istad.departmentservice.feature.department;

import co.istad.departmentservice.feature.department.dto.CreateDepartmentRequest;
import co.istad.departmentservice.feature.department.dto.DepartmentResponse;
import co.istad.departmentservice.feature.department.dto.UpdateDepartmentRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/departments")
@RequiredArgsConstructor
public class DepartmentController {

    private final DepartmentService departmentService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    void createDepartment(@RequestBody CreateDepartmentRequest createDepartmentRequest) {
        departmentService.createDepartment(createDepartmentRequest);
    }

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    Page<DepartmentResponse> getAllDepartments(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return departmentService.getDepartments(page, size);
    }

    @GetMapping("{uuid}")
    @ResponseStatus(HttpStatus.OK)
    DepartmentResponse getDepartment(@PathVariable String uuid) {
        return departmentService.getDepartmentByUuid(uuid);
    }

    @PutMapping("update/{uuid}")
    @ResponseStatus(HttpStatus.OK)
    void updateDepartment(@PathVariable String uuid, @RequestBody UpdateDepartmentRequest updateDepartmentRequest) {
        departmentService.updateDepartmentByUuid(uuid, updateDepartmentRequest);
    }

    @DeleteMapping("delete/{uuid}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteDepartment(@PathVariable String uuid) {
        departmentService.deleteDepartment(uuid);
    }
}
