package com.meli.challenge.exception;

import lombok.Getter;

/**
 * Exception raised when coupon processed not found results
 *
 * @author <a href="rasgut19@gmail.com">Rafael Gutierrez</a>
 * @version 1.0
 * @since 1.0
 */
@Getter
public class NotFoundResultsException extends RuntimeException{

	private static final long serialVersionUID = 245371441596536079L;

	private Float amount;

	public NotFoundResultsException(Float amount) {
		super(String.format("Not found results for amount [%f]", amount));
		this.amount = amount;
	}
}
