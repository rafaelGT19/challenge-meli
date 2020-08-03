package com.meli.challenge.config;

import com.meli.challenge.exception.RemoteApiFailedException;
import feign.Response;
import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

/**
 * Provide handler configuration for feign client
 *
 * @author <a href="rasgut19@gmail.com">Rafael Gutierrez</a>
 * @version 1.0
 * @since 1.0
 */
@Configuration
public class FeignClientConfiguration {

	@Bean
	public ErrorDecoder errorDecoder() {
		return new LocalErrorDecoder();
	}

	public static class LocalErrorDecoder implements ErrorDecoder {

		@Override
		public Exception decode(String methodKey, Response response) {
			String errorDescription = "";

			if (response.status() == HttpStatus.NOT_FOUND.value()) {
				errorDescription = "Not found item in external service";
			} else if (response.status() == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
				errorDescription = "Communication error with external service";
			}

			return new RemoteApiFailedException(HttpStatus.valueOf(response.status()), errorDescription,
				response.request().url());
		}

	}

}
