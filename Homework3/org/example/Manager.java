package org.example;

public class Manager extends Employee{

    public Manager(String name, String lastName, int age) {
        super(name, lastName, "manager", age);

    }

    public static void raiseSalary(Employee[] employees, int age, int payRaise) {
        for (int i = 0; i < employees.length; i++) {
            if (employees[i].getAge() >= age && !(employees[i] instanceof Manager)) {
                employees[i].setSalary(employees[i].getSalary() + payRaise);
            }

        }
    }
}
