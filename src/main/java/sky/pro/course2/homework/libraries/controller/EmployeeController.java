package sky.pro.course2.homework.libraries.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sky.pro.course2.homework.libraries.model.Employee;
import sky.pro.course2.homework.libraries.service.EmployeeService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/add")
    public Employee addEmployee(@RequestParam("firstName") String firstName,
                                @RequestParam("lastName") String lastName,
                                @RequestParam("departmentID") int departmentID,
                                @RequestParam("salary") int salary) {
        return employeeService.addEmployee(firstName, lastName, departmentID, salary);
    }

    @GetMapping("/remove")
    public Employee deleteEmployee(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName) {
        return employeeService.deleteEmployee(firstName, lastName);
    }

    @GetMapping("/find")
    public Employee findByName(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName) {
        return employeeService.findByName(firstName, lastName);
    }

    @GetMapping("/printEmployeeList")
    public List<Employee> printEmployeeList() {
        return employeeService.findAll();
    }

    @GetMapping("/autofill")
    public Map<String, Employee> autofillEmployeeList() {
        return employeeService.autofill();
    }

}