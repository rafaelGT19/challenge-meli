package com.meli.challenge.config;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Async tasks executor service configuration
 *
 * @author <a href="rasgut19@gmail.com">Rafael Gutierrez</a>
 * @version 1.0
 * @since 1.0
 */
@Configuration
public class ExecutorConfig {

	/***
	 * Executor service max size pool
	 */
	@Value("${threadpool.size}")
	private Integer threadPoolSize;

	/**
	 * Returns a Executor instance to perform async tasks
	 * @return
	 */
	@Bean
	public ExecutorService getAsyncExecutorInstance() {
		return Executors.newFixedThreadPool(threadPoolSize);
	}

}
