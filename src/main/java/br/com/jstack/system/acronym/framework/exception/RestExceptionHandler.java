package br.com.jstack.system.acronym.framework.exception;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

import br.com.jstack.system.acronym.model.ErrorResponse;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@Slf4j
@RestControllerAdvice
public class RestExceptionHandler {
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorResponse> handleValidationBody(MethodArgumentNotValidException ex, WebRequest request) {
		List<String> details = ex.getBindingResult()
			.getFieldErrors()
			.stream()
			.map(f -> f.getField() + ": " + f.getDefaultMessage())
			.toList();
		
		String message = "Validation error on request body";
		
		return buildResponse(HttpStatus.BAD_REQUEST, message, details.toString(), request);
	}
	
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<ErrorResponse> handleValidationParam(ConstraintViolationException ex, WebRequest request) {
		List<String> violations = ex.getConstraintViolations()
			.stream()
			.map(ConstraintViolation::getMessage)
			.toList();
		
		return buildResponse(HttpStatus.BAD_REQUEST, "Constraint validation failed", String.join(", ", violations), request);
	}
	
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ResponseEntity<ErrorResponse> handleParamMismatch(MethodArgumentTypeMismatchException ex, WebRequest request) {
		String message = String.format("Parameter '%s' should be of type '%s'",
			ex.getName(), ex.getRequiredType().getSimpleName());
		
		return buildResponse(HttpStatus.BAD_REQUEST, "Parameter type mismatch", message, request);
	}
	
	@ExceptionHandler(NoSuchElementException.class)
	public ResponseEntity<ErrorResponse> handleNotFound(NoSuchElementException ex, WebRequest request) {
		return buildResponse(HttpStatus.NOT_FOUND, "Resource not found", ex.getMessage(), request);
	}
	
	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<ErrorResponse> handleIllegalArgument(IllegalArgumentException ex, WebRequest request) {
		return buildResponse(HttpStatus.BAD_REQUEST, "Invalid argument", ex.getMessage(), request);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> handleUnexpected(Exception ex, WebRequest request) {
		log.error("Unhandled exception", ex);
		return buildResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Unexpected error", ex.getMessage(), request);
	}
	
	private ResponseEntity<ErrorResponse> buildResponse(HttpStatus status, String error, String message, WebRequest request) {
		String path = request.getDescription(false).replace("uri=", "");
		ErrorResponse errorResponse = new ErrorResponse(
			LocalDateTime.now(),
			(long) status.value(),
			error,
			message,
			path
		);
		
		log.error("[API Error] {} - {}", error, message);
		return ResponseEntity.status(status)
			.contentType(MediaType.APPLICATION_JSON)
			.body(errorResponse);
	}
}