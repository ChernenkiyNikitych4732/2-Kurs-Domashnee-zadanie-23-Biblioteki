package sky.pro.course2.homework.libraries.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import sky.pro.course2.homework.libraries.exceptions.EmployeeAlreadyAddedException;
import sky.pro.course2.homework.libraries.exceptions.EmployeeInvalidName;
import sky.pro.course2.homework.libraries.exceptions.EmployeeNotFoundException;
import sky.pro.course2.homework.libraries.exceptions.EmployeeStorageIsFullException;
import sky.pro.course2.homework.libraries.model.Employee;


import java.util.*;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private static final int maxEmployeesNumber = 10;
    private final Map<String, Employee> employees = new HashMap<>();

    private String makeKey(String firstName, String lastName) {
        return firstName + lastName;
    }

    // проверка корректности ввода имени нового сотрудника
    private static boolean checkData(Employee newEmployee) {
        return StringUtils.isAlpha(newEmployee.getFirstName()) && StringUtils.isAlpha(newEmployee.getSurname());
    }

    // приведение имени нового сотрудника к стандартному виду
    private static void correctData(Employee newEmployee) {
        newEmployee.setName(StringUtils.capitalize(newEmployee.getFirstName().toLowerCase()));
        newEmployee.setSurname(StringUtils.capitalize(newEmployee.getSurname().toLowerCase()));
        System.out.printf("Имя сотрудника %s %s приведено к стандартному виду\n", newEmployee.getFirstName(), newEmployee.getSurname());
    }


    public Employee addEmployee(String name, String surname, int department, int salary) {
        Employee employeeToAdd = new Employee(name, surname, department, salary);
        if (employees.size() >= maxEmployeesNumber) {
            throw new EmployeeStorageIsFullException();     // если штат заполнен
        }
        if (checkData(employeeToAdd)) { // проверка правильности ввода имени нового сотрудника
            System.out.println("Имя сотрудника задано корректно");
            correctData(employeeToAdd);
        } else
            throw new EmployeeInvalidName("Имя сотрудника задано неверно");
        if (employees.containsValue(employeeToAdd)) {
            throw new EmployeeAlreadyAddedException("Сотрудник " + name + " " + surname + " уже добавлен");
        }
        String key = makeKey(name, surname);
        employees.put(key, employeeToAdd);
        System.out.printf("Сотрудник '%s %s' добавлен\n", employeeToAdd.getFirstName(), employeeToAdd.getSurname());
        return employeeToAdd;
    }


    public Employee deleteEmployee(String firstName, String lastName) {
        Employee employeeToDelete = findByName(firstName, lastName);
        if (!employees.containsValue(employeeToDelete)) {
            throw new EmployeeNotFoundException("Сотрудник " + firstName + " " + lastName + " не найден");
        }
        employees.remove(makeKey(firstName, lastName));
        System.out.printf("Сотрудник '%s %s' удалён\n", firstName, lastName);
        return employeeToDelete;
    }


    public Employee findByName(String firstName, String lastName) {
        String employeeToFind = makeKey(firstName, lastName);
        if (employees.containsKey(employeeToFind)) {
            System.out.printf("Найден сотрудник: '%s %s' \n", firstName, lastName);
            return employees.get(employeeToFind);
        }
        throw new EmployeeNotFoundException("Сотрудник " + firstName + " " + lastName + " не найден");
    }


    public List<Employee> findAll() {
        return new ArrayList<>(employees.values());
    }

    public  Map<String, Employee> autofill () {
        addEmployee("Иван", "Виноградов",     1  ,      320000);
        addEmployee("Вадим", "Егоров",        1      ,  320000);
        addEmployee("Макар", "Филатов",       1     ,   320000);
        addEmployee("Александр", "Нечаев",    2 ,       320000);
        addEmployee("Артем", "Логинов",       2     ,   320000);
        addEmployee("Василиса", "Морозова",   4 ,       550000 );
        addEmployee("Мария", "Романова",      3    ,    550000);
        addEmployee("Стефания", "Сорокина",   3 ,       550000);
        addEmployee("Алиса", "Лебедева",      5    ,    550000 );
        addEmployee("Екатерина", "Герасимова",5,        550000);
        return employees;
    }
}