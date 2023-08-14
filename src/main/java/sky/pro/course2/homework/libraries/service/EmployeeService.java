package sky.pro.course2.homework.libraries.service;

import sky.pro.course2.homework.libraries.model.Employee;


import java.util.List;
import java.util.Map;

public interface EmployeeService {
    Employee deleteEmployee(String name, String surname);

    Employee findByName(String name, String surname);

    Employee addEmployee(String name, String surname, int department, int salary);

    List<Employee> findAll();

    Map<String, Employee> autofill();
}