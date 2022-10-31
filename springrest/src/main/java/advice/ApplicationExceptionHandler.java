//package advice;
//
//import java.util.Map;
//
//
//import java.util.*;
//
//import org.springframework.http.HttpStatus;
//import org.springframework.web.bind.MethodArgumentNotValidException;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.ResponseStatus;
//import org.springframework.web.bind.annotation.RestControllerAdvice;
//
//import com.springrest.springrest.exception.CourseNotFoundException;
//
//@RestControllerAdvice
//public class ApplicationExceptionHandler {
//	
//	@ResponseStatus(HttpStatus.BAD_REQUEST)
//	@ExceptionHandler(MethodArgumentNotValidException.class)
//	public Map<String,String> handleInvalidArgument(MethodArgumentNotValidException ex){
//		Map<String,String> errorMap = new HashMap<>();
//		ex.getBindingResult().getFieldErrors().forEach(error ->{
//			errorMap.put(error.getField(), error.getDefaultMessage());
//		});
//		return errorMap;
//	}
//	
//	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//	@ExceptionHandler(CourseNotFoundException.class)
//	public Map<String,String> handleBusinessException(CourseNotFoundException ex){
//		Map<String,String> errorMap = new HashMap<>();
//		errorMap.put("errorMessage", ex.getMessage());
//		return errorMap;
//	}
//	
//}
