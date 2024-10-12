package br.com.mmt.helpdesk.resources.exceptions;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandartError{
    private static final long serialVersionUID = 1L;

    private List<FieldMessages> errors = new ArrayList<>();

    public ValidationError() {
        super();
    }

    public ValidationError(String timestamp, Integer status, String error, String message, String path) {
        super(timestamp, status, error, message, path);
    }

    public List<FieldMessages> getErrors() {
        return errors;
    }

    public void addError(String fieldName, String message) {
        this.errors.add(new FieldMessages(fieldName, message));
    }

}
