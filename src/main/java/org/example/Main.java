package org.example;

import exception.EmployeeNotFoundException;
import exception.FileLoadException;
import model.Employee;
import service.EmployeeService;
import service.FileService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        EmployeeService employeeService = new EmployeeService();
        FileService fileService = new FileService();


        List<Employee> list = new ArrayList<>();

        list.add(new Employee(1, "Строев", "Иван", 100000));
        list.add(new Employee(2, "Петров", "Петр", 100000));
        list.add(new Employee(3, "Соболев", "Илья", 83000));
        list.add(new Employee(4, "Смирнов", "Антон", 94000));
        list.add(new Employee(5, "Пономарев", "Никита", 113000));
        list.add(new Employee(6, "Иванов", "Иван", 200000));

        System.out.println("All employees: ");
        list.forEach(System.out::println);

        try {
            Employee e = employeeService.getEmployeeById(2, list);
            System.out.println("\nНайден сотрудник с id = 2: " + e);
        } catch (EmployeeNotFoundException exception){
            System.out.println("Ошибка: " + exception.getMessage());
        }

        try {
            employeeService.getEmployeeById(0, list);
        } catch (EmployeeNotFoundException exception){
            System.out.println("Проверка метода getEmployeeById: " + exception.getMessage());
        }

        System.out.println("Сотрудники, чья зп >= 100000: ");
        List<Employee> employees = employeeService.getEmployeesBySalaryGreaterThan(100000, list);
        employees.forEach(System.out::println);

        Map<String, Employee> map = employeeService.getemployeeMap(list);
        System.out.println("Ключ: ");
        map.keySet().forEach(System.out::println);

        String filename = "employees.txt";
        try{
            fileService.saveEmployeesToFile(list,filename);
            System.out.println("Сохранен в файл: " + filename);

            List<Employee> employeeList = fileService.loadEmployeesFromFile(filename);
            System.out.println("Из файла загружены следующие работники: ");
            employeeList.forEach(System.out::println);

        } catch (IOException e) {
            System.out.println("Ошибка чтения или записи: " + e.getMessage());
        } catch (FileLoadException fle){
            System.out.println("Файл с именем: " + filename + " не найден: " + fle.getMessage());
        }

    }
}