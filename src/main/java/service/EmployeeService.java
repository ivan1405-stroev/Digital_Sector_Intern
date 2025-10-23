package service;

import exception.EmployeeNotFoundException;
import model.Employee;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class EmployeeService {

    public Employee getEmployeeById(int id, List<Employee> employees) {
        return employees.stream()
                .filter(employee -> employee.getId() == id)
                .findFirst()
                .orElseThrow(()-> new EmployeeNotFoundException("Сотрудника с id: " + id + "не удалось найти!"));
    }

    public List<Employee> getEmployeesBySalaryGreaterThan(int targetSalary, List<Employee> employees){
        return employees.stream()
                .filter(employee -> employee.getSalary() >= targetSalary)
                .collect(Collectors.toList());
    }

    public Map<String, Employee> getemployeeMap(List<Employee> employees){
        Map<String, Employee> hashMapEmployees = new HashMap<>();

        if (employees == null) return hashMapEmployees;

        for (Employee employee: employees){
            hashMapEmployees.put("id" + employee.getId(), employee);
        }

        return hashMapEmployees;
    }


}
