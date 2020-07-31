package com.meli.challenge.handler;

import com.meli.challenge.dto.CouponResponseDto;
import com.meli.challenge.exception.NotFoundResultsException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Handler to manage error response when exception are raised.
 *
 * @author <a href="rasgut19@gmail.com">Rafael Gutierrez</a>
 * @version 1.0
 * @since 1.0
 */
@ControllerAdvice
@Slf4j
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler({NotFoundResultsException.class})
	public ResponseEntity<Object> handleNotFoundResultsException(
		NotFoundResultsException ex, WebRequest request
	) {
		log.info("Exception raised when process request", ex);

		return super
			.handleExceptionInternal(ex, CouponResponseDto.builder().build(), new HttpHeaders(),
				HttpStatus.NOT_FOUND, request);
	}

}
