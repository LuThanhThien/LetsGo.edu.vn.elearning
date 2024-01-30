package vn.letsgo.elearning.exception.dto;

import org.springframework.core.MethodParameter;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import vn.letsgo.elearning.exception.HandleGlobalException;

@HandleGlobalException
public class EmptyFieldsException extends MethodArgumentNotValidException {

    /**
     * Constructor for {@link MethodArgumentNotValidException}.
     *
     * @param parameter     the parameter that failed validation
     * @param bindingResult the results of the validation
     */
    public EmptyFieldsException(MethodParameter parameter, BindingResult bindingResult) {
        super(parameter, bindingResult);
    }
}
