package sky.pro.course2.homework.libraries.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sky.pro.course2.homework.libraries.model.Employee;
import sky.pro.course2.homework.libraries.service.DepartmentService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/departments")
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/minimal salary")
    public Employee findMinSalary(@RequestParam("departmentID") int departmentID) {
        return departmentService.findMinSalary(departmentID);
    }

    // поиск сотрудников  с максимальной зарплатой в отделе
    @GetMapping("/max-salary")
    public Employee findMaxSalary(@RequestParam("departmentID") int departmentID) {
        return departmentService.findMaxSalary(departmentID);
    }

    @GetMapping("/salary expenses")
    public long countSalaryExpenses() {
        return departmentService.countSalaryExpenses();
    }

    @GetMapping(value = "/salary expenses", params = "departmentID")
    public long countSalaryExpenses(@RequestParam("departmentID") int departmentID) {
        return departmentService.countSalaryExpenses(departmentID);
    }


    @GetMapping(value = "/all", params = "departmentID")
    public List<Employee> getAll(@RequestParam("departmentID") int departmentID) {
        return departmentService.getAll(departmentID);
    }


    @GetMapping("/all")
    public Map<Integer, List<Employee>> getAll() {
        return departmentService.getAll();
    }


    @GetMapping("/all below")
    public List<Employee> allBelow(@RequestParam("salary") int salary) {
        return departmentService.findAllBelow(salary);
    }

    // вывод сотрудников отдела с зарплатой ниже указанной
    @GetMapping(value = "/all below", params = "departmentID")
    public List<Employee> allBelow(@RequestParam("departmentID") int departmentID, @RequestParam("salary") int salary) {
        return departmentService.findAllBelow(departmentID, salary);
    }
}