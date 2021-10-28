package obg;

import java.util.Objects;

public class ErrorResponse implements Response {
    private String errMessage;

    public ErrorResponse(String errMessage) {
        this.errMessage = errMessage;
    }

    public static ErrorResponse createErrorResponse(String errorMessage) {
        return new ErrorResponse(errorMessage);
    }

    public static ErrorResponse invalidStudent() {
        return createErrorResponse("Invalid Student");
    }

    public static ErrorResponse invalidCourse() {
        return createErrorResponse("Invalid Course");
    }

    public static ErrorResponse invalidLetterGrade() { return createErrorResponse("Invalid lettergrade"); }

    public static ErrorResponse invalidInstructor() {
        return createErrorResponse("Invalid Instructor");
    }

    public static ErrorResponse invalidObjective() { return createErrorResponse("Invalid Objective"); }

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
