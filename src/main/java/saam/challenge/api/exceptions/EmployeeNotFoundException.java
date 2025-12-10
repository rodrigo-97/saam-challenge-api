package saam.challenge.api.exceptions;

import org.springframework.http.HttpStatus;

public class EmployeeNotFoundException extends ApiException {
    public EmployeeNotFoundException() {
        super("EMPLOYEE_NOT_FOUND");
    }

    @Override
    public HttpStatus getStatus() {
        return HttpStatus.NOT_FOUND;
    }
}