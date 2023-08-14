public class Employee {
    private String name;

    private String surname;

    public Employee(String name, String surname, int department, int salary) {
        this.name = name;
        this.surname = surname;
    }
    public void setName(String name) {
        this.name = name;
    }

    public void setLastName(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    @Override
    public String toString() {
        return name + " " + surname;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}