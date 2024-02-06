package exception;

import org.springframework.http.HttpStatus;

import java.util.List;

public class ApiException extends RuntimeException {
    private final HttpStatus status;
    private final List<String> errorMessages;

    public ApiException(HttpStatus status, List<String> errorMessages) {
        super("ApiException: " + status + ", " + errorMessages);  // Include status and errorMessages in the exception message
        this.status = status;
        this.errorMessages = errorMessages;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public List<String> getErrorMessages() {
        return errorMessages;
    }
}
