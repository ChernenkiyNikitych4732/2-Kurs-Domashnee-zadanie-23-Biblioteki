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
    public Employee addEmployee(@RequestParam("name") String name, @RequestParam("surname") String surname, @RequestParam("departmentID")
    int departmentID, @RequestParam("salary") int salary) {
        return employeeService.addEmployee(name, surname, departmentID, salary);
    }

    @GetMapping("/remove")
    public Employee deleteEmployee(@RequestParam("name") String name, @RequestParam("surname") String surname) {
        return employeeService.deleteEmployee(name, surname);
    }

    @GetMapping("/find")
    public Employee findByName(@RequestParam("name") String name, @RequestParam("surname") String surname) {
        return employeeService.findByName(name, surname);
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