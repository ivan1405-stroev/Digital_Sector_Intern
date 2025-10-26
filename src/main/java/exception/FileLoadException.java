package exception;

import java.io.IOException;

//Исключение, если файл не был найден
public class FileLoadException extends Exception {
    public FileLoadException(String message){
        super(message);
    }
}
