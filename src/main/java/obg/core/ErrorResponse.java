package obg.core;

import java.util.Objects;

public class ErrorResponse {
    public static final String INVALID_STUDENT = "Invalid Student";
    public static final String INVALID_COURSE = "Invalid Course";
    public static final String INVALID_LETTER_GRADE = "Invalid letter grade";
    public static final String INVALID_INSTRUCTOR = "Invalid Instructor";
    public static final String INVALID_OBJECTIVE = "Invalid Objective";
    public static final String INVALID_ENROLLMENT = "Enrollment does not exist";
    public static final String INVALID_ATTEMPT = "Attempt does not exist";
    public static final String INVALID_SCORE = "Invalid integer score";
    public static final String INVALID_ATTEMPT_STATUS = "Invalid attempt status";
    public static final String EXISTING_ENROLLMENT = "Student already enrolled in course";
    public static final String INVALID_ATTEMPTNUMBER = "Attempt Numbers cannot be negative";
    public static String NOT_COURSE_INSTRUCTOR = "Not course instructor";
    public static String EXISTING_STUDENT = "Student already exists";
    private final String errMessage;

    public ErrorResponse(String errMessage) {
        this.errMessage = errMessage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ErrorResponse that = (ErrorResponse) o;
        return Objects.equals(errMessage, that.errMessage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(errMessage);
    }

}
