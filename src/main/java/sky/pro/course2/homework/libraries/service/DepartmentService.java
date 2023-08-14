package sky.pro.course2.homework.libraries.service;

import org.springframework.stereotype.Service;
import sky.pro.course2.homework.libraries.exceptions.EmployeeNotFoundException;
import sky.pro.course2.homework.libraries.model.Employee;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class DepartmentService {
    private final EmployeeService employeeService;

    public DepartmentService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    public Employee findMaxSalary(int department) {
        Employee employeeWithMaxSalary = employeeService.findAll().stream()
                .filter(employee -> employee.getDepartment() == department)
                .max(Comparator.comparingInt(Employee::getSalary))
                .orElseThrow(EmployeeNotFoundException::new);
        System.out.printf("Максимальная зарплата в %s отделе у %s %s\n",
                employeeWithMaxSalary.getDepartment(),
                employeeWithMaxSalary.getName(),
                employeeWithMaxSalary.getSurname());
        return employeeWithMaxSalary;
    }

    public Employee findMinSalary(int department) {
        Employee employeeWithMinSalary = employeeService.findAll().stream()
                .filter(employee -> employee.getDepartment() == department)
                .min(Comparator.comparingInt(Employee::getSalary))
                .orElseThrow(EmployeeNotFoundException::new);
        System.out.printf("Минимальная зарплата в %s отделе у %s %s\n",
                employeeWithMinSalary.getDepartment(),
                employeeWithMinSalary.getName(),
                employeeWithMinSalary.getSurname());
        return employeeWithMinSalary;
    }

    public long countSalaryExpenses() {
        return employeeService.findAll().stream().mapToLong(Employee::getSalary).sum();
    }

    public long countSalaryExpenses(int department) {
        long sum = employeeService.findAll().stream()
                .filter(employee -> employee.getDepartment() == department).mapToInt(Employee::getSalary).sum();
        System.out.printf("Сумма расходов на зарплату в отделе №%s равна %s\n", department, sum);
        return sum;
    }

    public List<Employee> getAll(int department) {
        return employeeService.findAll().stream()
                .filter(employee -> employee.getDepartment() == department)
                .collect(Collectors.toList());
    }

    public Map<Integer, List<Employee>> getAll() {
        return employeeService.findAll().stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));
    }

    public List<Employee> findAllBelow(int salary){
        return employeeService.findAll().stream()
                .filter(employee -> employee.getSalary() < salary)
                .collect(Collectors.toList());
    }

    public List<Employee> findAllBelow(int department, int salary){
        return employeeService.findAll().stream()
                .filter(employee -> employee.getDepartment() == department)
                .filter(employee -> employee.getSalary() < salary)
                .collect(Collectors.toList());
    }
}