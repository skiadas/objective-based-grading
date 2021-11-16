package obg.response;

import obg.core.entity.Attempt;
import obg.core.entity.Course;

import java.util.ArrayList;

public class ViewPendingAttemptsResponse {

    public Course course;
    public ArrayList<Attempt> attempts;

    public ViewPendingAttemptsResponse(Course course, ArrayList<Attempt> attempts) {
        this.course = course;
        this.attempts = attempts;
    }
}
