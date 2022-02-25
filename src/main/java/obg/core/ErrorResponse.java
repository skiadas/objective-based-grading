package obg.core;

import java.util.Objects;

public class ErrorResponse {
    public static final String INVALID_STUDENT = "Invalid Student";
    public static final String INVALID_COURSE = "Invalid Course";
    public static final String INVALID_LETTER_GRADE = "Invalid letter grade";
    public static final String INVALID_INSTRUCTOR = "Invalid Instructor";
    public static final String INVALID_OBJECTIVE = "Invalid Objective";
    public static final String STUDENT_NOT_ENROLLED = "Student not enrolled";
    public static final String INVALID_ENROLLMENT = "Enrollment does not exist";
    public static final String INVALID_ATTEMPT = "Attempt does not exist";
    public static final String INVALID_SCORE = "Invalid integer score";
    public static String INVALID_COURSE_INSTRUCTOR = "Not course instructor";
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
