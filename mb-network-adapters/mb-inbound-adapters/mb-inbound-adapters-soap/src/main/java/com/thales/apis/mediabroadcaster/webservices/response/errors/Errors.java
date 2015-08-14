package com.thales.apis.mediabroadcaster.webservices.response.errors;

import javax.servlet.http.HttpServletResponse;

import com.thales.apis.mediabroadcastercommontypes.ErrorType;

public enum Errors {

	BAD_REQUEST(HttpServletResponse.SC_BAD_REQUEST), SERVER_INTERNAL_ERROR(HttpServletResponse.SC_INTERNAL_SERVER_ERROR), CONFLICT(
			HttpServletResponse.SC_CONFLICT), EXPIRED_MESSAGE_RECEIVED(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);

	int errorCode;

	Errors(int errorCode) {
		this.errorCode = errorCode;
	}

	public ErrorType getInstance(String errorMessage) {

		final ErrorType error = new ErrorType();

		error.setErrorCode(String.valueOf(this.errorCode));

		error.setErrorMessage(errorMessage);

		return error;
	}

}
