package service;

import exception.EmployeeNotFoundException;
import model.Employee;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeServiceTest {

    private final EmployeeService service = new EmployeeService();
    private final List<Employee> employees = Arrays.asList(
            new Employee(1, "Строев", "Иван", 100000),
            new Employee(2, "Петров", "Петр", 100000),
            new Employee(3, "Соболев", "Илья", 83000),
            new Employee(4, "Смирнов", "Антон", 94000)
    );


    //Проверяет работу метода getEmployeeById по получению сотрудника по id, если такой сотрудник существует в массиве
    @Test
    void getEmployeeByIdSuccess() {
        Employee employee = service.getEmployeeById(2, employees);
        Assertions.assertEquals(2,employee.getId());
    }

    /*
    Проверяет работу метода getEmployeeById по получению сотрудника по id,
    если такого сотрудника нет в массиве (проверяет будет ли выброшено исключение)
    */
    @Test
    void getEmployeeByIdNotFound(){
        Assertions.assertThrows(EmployeeNotFoundException.class, ()->{
            service.getEmployeeById(0, employees);
        });
    }

    //Проверяет работу метода getEmployeesBySalaryGreaterThan
    @Test
    void getEmployeesBySalaryGreaterThan() {
        List<Employee> result = service.getEmployeesBySalaryGreaterThan(95000, employees);
        Assertions.assertEquals(2,result.size());
    }
}