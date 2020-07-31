package com.meli.challenge.exception;

import lombok.Getter;

@Getter
public class NotFoundResultsException extends RuntimeException{

	private static final long serialVersionUID = 245371441596536079L;

	private Float amount;

	public NotFoundResultsException(Float amount) {
		super(String.format("Not found results for amount [%f]", amount));
		this.amount = amount;
	}
}
