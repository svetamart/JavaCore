package org.example;
import java.util.Arrays;
import java.util.Comparator;

import static org.example.Manager.raiseSalary;

public class Main {
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_RESET = "\u001B[0m";

    public static void main(String[] args) {
        Employee employee1 = new Employee("John", "Smith", "designer", 27);
        employee1.setSalary(50000);
        employee1.setPhone("+4492275649034");
        employee1.showInfo();

        Employee employee2 = new Employee("Jane", "Smith", "copywriter", 36);
        employee2.setSalary(40000);
        employee2.setPhone("+4493375667834");
        employee2.showInfo();

        Employee employee3 = new Employee("Jim", "Green", "accountant", 47);
        employee3.setSalary(60000);
        employee3.setPhone("+4493374447834");
        employee3.showInfo();

        Employee employee4 = new Manager("Mary", "Brown", 50);
        employee4.setSalary(70000);
        employee4.setPhone("+4495574447834");
        employee4.showInfo();

        Employee employee5 = new Employee("John", "Doe", "HR manager", 34);
        employee5.setSalary(50000);
        employee5.setPhone("+4493374667834");
        employee5.showInfo();

        System.out.println(ANSI_RED + "\nAfter pay rise: " + ANSI_RESET);

        Employee[] employees = new Employee[] {employee1, employee2, employee3, employee4, employee5};

        raiseSalary(employees, 35, 5000);

        for (Employee employee : employees) {
            employee.showInfo();
        }

        Comparator<Employee> comp = new AgeComparator().thenComparing(new SalaryComparator());
        Arrays.sort(employees, comp);

        System.out.println(ANSI_RED + "\nSorted" + ANSI_RESET);

        for (Employee employee : employees) {
            employee.showInfo();
        }
    }
}