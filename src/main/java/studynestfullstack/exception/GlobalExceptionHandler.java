package studynestfullstack.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import studynestfullstack.response.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

	// ================= EMAIL EXISTS =================

	@ExceptionHandler(EmailAlreadyExistsException.class)
	public ResponseEntity<ApiResponse<Object>> handleEmailExists(EmailAlreadyExistsException ex) {

		ApiResponse<Object> response = new ApiResponse<>(false, ex.getMessage(), null);

		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}

	// ================= INVALID CREDENTIALS =================

	@ExceptionHandler(InvalidCredentialsException.class)
	public ResponseEntity<ApiResponse<Object>> handleInvalidCredentials(InvalidCredentialsException ex) {

		ApiResponse<Object> response = new ApiResponse<>(false, ex.getMessage(), null);

		return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
	}

	// ================= USER NOT FOUND =================

	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<ApiResponse<Object>> handleUserNotFound(UserNotFoundException ex) {

		ApiResponse<Object> response = new ApiResponse<>(false, ex.getMessage(), null);

		return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	}

	// ================= STUDENT NOT FOUND =================

	@ExceptionHandler(StudentNotFoundException.class)
	public ResponseEntity<ApiResponse<Object>> handleStudentNotFound(StudentNotFoundException ex) {

		ApiResponse<Object> response = new ApiResponse<>(false, ex.getMessage(), null);

		return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	}

	// ================= VALIDATION =================

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ApiResponse<Map<String, String>>> handleValidation(MethodArgumentNotValidException ex) {

		Map<String, String> errors = new HashMap<>();

		ex.getBindingResult().getFieldErrors()
				.forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));

		ApiResponse<Map<String, String>> response = new ApiResponse<>(false, "Validation failed", errors);

		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}

	// ================= GENERAL EXCEPTION =================

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ApiResponse<Object>> handleException(Exception ex) {

		ApiResponse<Object> response = new ApiResponse<>(false, "Something went wrong", null);

		return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}