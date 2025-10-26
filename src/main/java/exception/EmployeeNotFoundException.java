package exception;

//Исключение, если сотрудник не был найден
public class EmployeeNotFoundException extends RuntimeException{
    public EmployeeNotFoundException(String message){
        super(message);
    }
}
