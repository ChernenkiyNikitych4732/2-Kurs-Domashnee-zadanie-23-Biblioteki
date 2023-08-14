package sky.pro.course2.homework.libraries;

import javax.lang.model.element.Name;
import java.util.Objects;

public class Employee {
    private String name;
    private String surname;
    private int department;
    private int salary;

    public Employee(String firstName, String lastName, int department, int salary) {
        this.name = firstName;
        this.surname = lastName;
        this.department = department;
        this.salary = salary;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, surname);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Employee employee = (Employee) obj;
        return Objects.equals(name, employee.surname) && Objects.equals(surname, employee.surname);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "Name='" + name + '\'' +
                ",Surname='" + surname + '\'' +
                '}';
    }

    public String getFirstName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public int getDepartment() {
        return department;
    }

    public int getSalary() {
        return salary;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setDepartment(int department) {
        this.department = department;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public Object getName() {
        return name = name;
    }
}
