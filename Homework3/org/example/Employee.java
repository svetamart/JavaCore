package org.example;

import java.util.Comparator;

public class Employee {

    private String name;
    private String lastName;
    private String position;
    private int age;
    private String phone;
    private int salary;


    public Employee(String name, String lastName, String position, int age) {
        this.name = name;
        this.lastName = lastName;
        this.position = position;
        this.age = age;
    }

    protected Employee(String name, String lastName, int age) {
        this.name = name;
        this.lastName = lastName;
        this.age = age;
    }

    public void showInfo() {
        System.out.printf("\nName: %s \n" +
                        "Last name: %s \n" +
                        "Position: %s \n" +
                        "Age: %d \n" +
                        "Salary: %d \n" +
                        "Phone number: %s \n", this.name, this.lastName, this.position,
                this.age, this.salary, this.phone);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }
}

class SalaryComparator implements Comparator<Employee>{

    @Override
    public int compare(Employee o1, Employee o2) {
        return Integer.compare(o1.getSalary(), o2.getSalary());
    }
}
class AgeComparator implements Comparator<Employee>{

    @Override
    public int compare(Employee o1, Employee o2) {
        if (o1.getAge() > o2.getAge()) {
            return 1;
        } else if (o1.getAge() < o2.getAge()) {
            return -1;
        } else {
            return 0;
        }
    }
}


