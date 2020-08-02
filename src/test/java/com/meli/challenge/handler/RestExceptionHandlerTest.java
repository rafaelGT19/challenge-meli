package com.meli.challenge.handler;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

import com.meli.challenge.exception.NotFoundResultsException;
import javax.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

/**
 * Test class {@link RestExceptionHandler}
 *
 * @author <a href="rasgut19@gmail.com">Rafael Gutierrez</a>
 * @version 1.0
 * @since 1.0
 */
@ExtendWith(MockitoExtension.class)
class RestExceptionHandlerTest {

	private WebRequest webRequestMock;

	@InjectMocks
	private RestExceptionHandler exceptionHandler;

	@BeforeEach
	void setUp() {
		HttpServletRequest request = mock(HttpServletRequest.class);
		this.webRequestMock = new ServletWebRequest(request);
	}

	@Test
	void givenException_whenExceptionIsThrown_thenReturnsAppropiatedResponse() {
		NotFoundResultsException exception = new NotFoundResultsException(100F);

		ResponseEntity<Object> response = exceptionHandler
			.handleNotFoundResultsException(exception, this.webRequestMock);

		assertThat(response).isNotNull();
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
	}

}
