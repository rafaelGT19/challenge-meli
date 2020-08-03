package com.meli.challenge.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * Exception to handle unexpected error consuming remote REST services
 *
 * @author <a href="rasgut19@gmail.com">Rafael Gutierrez</a>
 * @version 1.0
 * @since 1.0
 */
@Getter
public class RemoteApiFailedException extends RuntimeException {

	private static final long serialVersionUID = 402417817574635888L;

	/**
	 * Https Status Error code given remote api
	 */
	private final HttpStatus statusErrorCode;

	/**
	 * Error description cause
	 */
	private final String errorDescription;

	/**
	 * Remote endpoint
	 */
	private final String url;

	/**
	 * Constructor with code and description of error.
	 *
	 * @param statusErrorCode  {@link HttpStatus} Error code given by remote api
	 *                         service.
	 * @param errorDescription {@link String} Error description given by remote api
	 *                         service.
	 */
	public RemoteApiFailedException(HttpStatus statusErrorCode, String errorDescription, String url) {

		super(String.format("Error calling remote api, status code [%s] and url [%s]", statusErrorCode.value(),
			url));
		this.statusErrorCode = statusErrorCode;
		this.errorDescription = errorDescription;
		this.url = url;
	}

}
