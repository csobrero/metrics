package com.challenge.metrics.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class MetricNotFoundException extends RuntimeException {

	public MetricNotFoundException(String exception) {
		super(exception);
	}

}
