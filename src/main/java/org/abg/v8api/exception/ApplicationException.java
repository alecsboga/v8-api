package org.abg.v8api.exception;

public class ApplicationException extends Exception {

	private static final long serialVersionUID = 1L;

	public ApplicationException(Throwable e) {
		super(e);
	}

	public ApplicationException(String msg, Throwable e) {
		super(msg, e);
	}

	public ApplicationException(String msg) {
		super(msg);
	}
}
