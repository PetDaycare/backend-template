package com.petdaycare.userservice.exception;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.I_AM_A_TEAPOT)
public class IfThisIsThrownThenThereIsSomethingExtremelyWrongAndYouShouldConsultGodOnYourFurtherCourseOfActionException extends RuntimeException {

    /**
     * Constructs a new runtime exception with the specified detail message.
     * The cause is not initialized, and may subsequently be initialized by a
     * call to {@link #initCause}.
     *
     * @param message the detail message. The detail message is saved for
     *                later retrieval by the {@link #getMessage()} method.
     */
    public IfThisIsThrownThenThereIsSomethingExtremelyWrongAndYouShouldConsultGodOnYourFurtherCourseOfActionException(String message) {

        super(message);
    }
}
