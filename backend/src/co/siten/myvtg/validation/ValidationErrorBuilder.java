package co.siten.myvtg.validation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

public class ValidationErrorBuilder {

    public static ValidationError fromBindingErrors(Errors errors) {
        ValidationError error = new ValidationError("Validation failed. " + errors.getErrorCount() + " error(s)");
//        error.getErrors();
//    	List<String> e = new ArrayList<String>();
    	
        for (FieldError e : errors.getFieldErrors()) {
            error.addValidationError(e.getField() + ": " + e.getDefaultMessage());
        }
        for (ObjectError e : errors.getGlobalErrors()) {
            error.addValidationError(e.getObjectName() + ": " + e.getDefaultMessage());
        }
        
//        for (ObjectError objectError : errors.getAllErrors()) {
//        	
//            error.addValidationError(objectError.getObjectName()+":"+ objectError.getDefaultMessage());
//        }
        return error;
    }
}