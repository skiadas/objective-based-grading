package webserver;

import obg.core.Presenter;
import obg.core.entity.*;
import obg.response.TargetGradeRequirementsResponse;
import spark.ModelAndView;
import spark.template.thymeleaf.ThymeleafTemplateEngine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ResultProducer implements Presenter {
    static final ThymeleafTemplateEngine engine = new ThymeleafTemplateEngine();
    final Map<String, Object> model = new HashMap<>();
    String result;

    public ResultProducer() {
    }

    public void reportError(String errorMessage) {
        throw new ErrorResponseException(errorMessage);
    }

    @Override
    public void presentRemainingAttempts(int attempts) {
    }

    @Override
    public void presentSuccessfulRemove(String message) {

    }

    public void presentInstructorCourseList(List<Course> courses) {
        addToModel("courses", courses);
        addToModel("instructor", new Instructor("skiadas", "Haris", "Skiadas"));
        // TODO
        result = engine.render(new ModelAndView(model, "instructorIndex"));
    }

    void addToModel(String key, Object value) {
        model.put(key, value);
    }

    public void presentStudentCourseList(List<Course> courses) {
        // TODO
    }

    public void presentAttempt(Attempt attempt) {
        // TODO
    }

    public void presentTargetGradeRequirements(TargetGradeRequirementsResponse response) {
        // TODO
    }

    @Override
    public void presentTargetGradeUnmetRequirements(List<ObjectiveGroup> unmetRequirements) {
        // TODO
    }

    public void presentPendingAttempts(List<Attempt> pendingAttempts) {
        // TODO
    }

    public void presentUnattemptedObjectives(List<String> objectives) {
        // TODO

    }

    public void presentObjectiveGrade(int objGrade) {
        // TODO
    }

    @Override
    public void presentAddedStudent(Student student) {

    }

    public void presentPendingAttempts() {
        //TODO
    }

    public void presentIndexPage() {
        result = engine.render(new ModelAndView(model, "index"));
    }

    public void presentLoginScreen() {
        result = engine.render(new ModelAndView(model, "login"));
    }

    @Override
    public void presentsRemovedStudent(Enrollment enrollment) {

    }

    static class ErrorResponseException extends RuntimeException {
        final String errorMessage;

        ErrorResponseException(String errorMessage) {
            this.errorMessage = errorMessage;
        }
    }
}