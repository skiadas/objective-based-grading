package obg;

import java.util.List;
import java.util.Objects;

public class CourseListResponse implements Response {
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
}
