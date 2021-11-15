package obg.response;

import obg.core.Response;
import obg.core.entity.Course;

import java.util.List;
import java.util.Objects;

public class CourseListResponse implements Response<List<Course>> {
    public final List<Course> courses;

    public CourseListResponse(List<Course> courses) {
        this.courses = courses;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CourseListResponse that = (CourseListResponse) o;
        return Objects.equals(courses, that.courses);
    }

    public int hashCode() {
        return Objects.hash(courses);
    }

    public List<Course> getValues() {
        return courses;
    }

    public String getErrorMessage() {
        throw new RuntimeException("Danger Will Robinson");
    }

    public boolean isSuccessful() {
        return true;
    }
}
