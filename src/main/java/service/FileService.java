package service;

import exception.FileLoadException;
import model.Employee;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileService {


    //Сохраняет список сотрудников в файл в формате: id, firstName, lastName, salary
    public void saveEmployeesToFile(List<Employee> employees, String filename) throws IOException {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filename, false))) {
            for (Employee employee: employees){
                String line = String.format("%d,%s,%s,%d",
                        employee.getId(),
                        employee.getFirstName(),
                        employee.getLastName(),
                        employee.getSalary());
                bufferedWriter.write(line);
                bufferedWriter.newLine();
            }
        }
    }


    /*
    Загружает список сотрудников из файла. Если файл не найден выбрасывает исключение FileLoadException
    Если допущена ошибка в строке (неправильная запись), то пропускает ее и пишет об этом в консоль
    */
    public List<Employee> loadEmployeesFromFile(String filename) throws FileLoadException {
        List<Employee> employees = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filename))){

            String line;

            while (bufferedReader.readLine() != null){
                line = bufferedReader.readLine();

                if (line.trim().isEmpty()) continue;

                String[] infoEmployee = line.split(",");
                if (infoEmployee.length != 4){
                    System.out.println("Найдена ошибка записи сотрудника в файле: " + line);
                }

                try {
                    int id = Integer.parseInt(infoEmployee[0].trim());
                    String firstName = infoEmployee[1].trim();
                    String lastName = infoEmployee[2].trim();
                    int salary = Integer.parseInt(infoEmployee[3].trim());

                    employees.add(new Employee(id, firstName, lastName, salary));
                } catch (NumberFormatException e){
                    System.out.println("Ошибка чтения числа в строке: " + line + " текст ошибки: " + e);
                }
            }

        } catch (FileNotFoundException fnle){
            throw new FileLoadException("Файл с именем: " + filename + " не найден! Ошибка:" + fnle);
        } catch (IOException ioe){
            throw new FileLoadException("Ошибка чтения файла: " + filename + " текст ошибки: " +ioe);
        }
        return employees;
    }
}
