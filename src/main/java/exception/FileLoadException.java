package exception;

import java.io.IOException;

public class FileLoadException extends Exception {
    public FileLoadException(String message){
        super(message);
    }
}
