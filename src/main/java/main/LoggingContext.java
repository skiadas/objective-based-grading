package main;

import obg.ConcreteAppContext;
import obg.core.AppContext;
import obg.core.Presenter;

public class LoggingContext implements AppContext {
    private final AppContext context;

    public LoggingContext(AppContext context) {
        this.context = context;
    }

    public void instructorCourseListRequested(String instructorId, Presenter presenter) {
        System.out.println("Requesting course list: " + instructorId);
        context.instructorCourseListRequested(instructorId, presenter);
    }

    public void attemptRequested(String studentId, String courseId, String objective, Presenter presenter) {
        System.out.println("Requesting attempt: " + studentId + ", " + courseId + ", " + objective);
        context.attemptRequested(studentId, courseId, objective, presenter);
    }
}
