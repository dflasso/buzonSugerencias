package ec.edu.espe.buzonESPE.exceptions.apierror;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.annotation.JsonTypeIdResolver;

import lombok.Data;

@Data
@JsonTypeInfo(include = JsonTypeInfo.As.WRAPPER_ARRAY, use = JsonTypeInfo.Id.CUSTOM, property = "error", visible = true)
@JsonTypeIdResolver(LowerCaseClassNameResolver.class)
public class ApiError {
	private HttpStatus status;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
	private LocalDateTime timestamp;
	private String message;
	private String debugMessage;
	private List<ApiSubError> subErrors;

	/**
	 * Errores que solo retornan un estado
	 * 
	 * @param status
	 */
	public ApiError(HttpStatus status) {
		this.timestamp = LocalDateTime.now();
		this.status = status;
	}

	/**
	 * Errores que retornan un estado y una excepcion
	 * 
	 * @param status
	 * @param ex
	 */
	public ApiError(HttpStatus status, Throwable ex) {
		this.timestamp = LocalDateTime.now();
		this.status = status;
		this.message = "Unexpected error";
		this.debugMessage = ex.getLocalizedMessage();
	}
	
	/**
	 * Errores que retornan un estado, una excepcion y una lista de subErrores
	 * @param status
	 * @param ex
	 */
	public ApiError(HttpStatus status, Throwable ex, List<ObjectError> globalErrors) {
		this.timestamp = LocalDateTime.now();
		this.status = status;
		this.message = "Unexpected error";
		this.debugMessage = ex.getLocalizedMessage();
		this.addValidationError(globalErrors);
	}

	/**
	 * Errores que retornan un estado, un mensaje y una excepcion
	 * 
	 * @param status
	 * @param message
	 * @param ex
	 */
	public ApiError(HttpStatus status, String message, Throwable ex) {
		this.timestamp = LocalDateTime.now();
		this.status = status;
		this.message = message;
		this.debugMessage = ex.getLocalizedMessage();
	}
	
	public ApiError(HttpStatus status, String message) {
		this.timestamp = LocalDateTime.now();
		this.status = status;
		this.message = message;
	}

	/**
	 * Agrega los subErrores a la lista subErrors
	 * 
	 * @param subError
	 */
	private void addSubError(ApiSubError subError) {
		if (subErrors == null) {
			subErrors = new ArrayList<ApiSubError>();
		}
		subErrors.add(subError);
	}

	private void addValidationError(String object, String field, Object rejectedValue, String message) {
		addSubError(new ApiValidationError(object, field, rejectedValue, message));
	}

	private void addValidationError(String object, String message) {
		addSubError(new ApiValidationError(object, message));
	}

	private void addValidationError(FieldError fieldError) {
        this.addValidationError(
                fieldError.getObjectName(),
                fieldError.getField(),
                fieldError.getRejectedValue(),
                fieldError.getDefaultMessage());
    }

    public void addValidationErrors(List<FieldError> fieldErrors) {
        fieldErrors.forEach(this::addValidationError);
    }

    private void addValidationError(ObjectError objectError) {
        this.addValidationError(
                objectError.getObjectName(),
                objectError.getDefaultMessage());
    }

    public void addValidationError(List<ObjectError> globalErrors) {
        globalErrors.forEach(this::addValidationError);
    }
}
