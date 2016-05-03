package org.diveintojee.poc.standardstack.api;

public class ResourceNotFoundException  extends RuntimeException {
	public ResourceNotFoundException(String message) {
		super(message);
	}
}