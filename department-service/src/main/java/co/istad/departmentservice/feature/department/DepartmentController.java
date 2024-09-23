package co.istad.departmentservice.feature.department;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/departments")
public class DepartmentController {

    @GetMapping
    String getAllDepartments() {
        return "Department Service";
    }
}
