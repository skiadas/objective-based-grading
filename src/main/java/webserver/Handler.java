package webserver;

import obg.core.AppContext;
import obg.core.Presenter;
import obg.core.entity.Attempt;
import obg.core.entity.Course;
import obg.response.TargetGradeRequirementsResponse;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.template.thymeleaf.ThymeleafTemplateEngine;
import webserver.user.AuthenticatedUser;
import webserver.user.User;
import webserver.user.UserAdministrator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Handler implements Presenter {
    private static final ThymeleafTemplateEngine engine = new ThymeleafTemplateEngine();
    private final UserAdministrator userAdmin;
    private final Request req;
    private final Response res;
    private final AppContext context;
    private final Map<String, Object> model = new HashMap<>();
    private String result;

    public Handler(Request req, Response res, UserAdministrator userAdmin, AppContext context) {
        this.req = req;
        this.res = res;
        this.userAdmin = userAdmin;
        this.context = context;
    }

    Object handleLogout() {
        userAdmin.logoutCurrentUser(req);
        res.redirect("/");
        return null;
    }

    Object handleLoginRequest() {
        String username = req.queryParams("username");
        userAdmin.loginUser(username, req);
        res.redirect("/");
        return null;
    }

    Object handleIndex() {
        // TODO: What to show on index page
        AuthenticatedUser user = (AuthenticatedUser) userAdmin.retrieveUser(req);
        User.Role currentRole = user.getCurrentRole();
        if (currentRole.equals(User.Role.Instructor)) {
            res.redirect("/instructor/" + user.getUsername());
            return null;
        } else {
            Map<String, Object> model = new HashMap<>();
            model.put("user", user);
            model.put("message", "Hi!");
            return engine.render(new ModelAndView(model, "index"));
        }
    }

    Object getInstructorIndexPage() {
        String instructorId = req.params("username");
        context.instructorCourseListRequested(instructorId, this);
        return result;
    }

    Object showLoginScreen() {
        return engine.render(new ModelAndView(new HashMap<>(), "login"));
    }

    public void reportError(String errorMessage) {
        throw new ErrorResponseException(errorMessage);
    }

    public void presentInstructorCourseList(List<Course> courses) {
        model.put("courses", courses);
        // TODO
        result = engine.render(new ModelAndView(model, "instructorIndex"));
    }

    public void presentStudentCourseList(List<Course> courses) {
        // TODO
    }

    public void presentAttemptCreated(Attempt attempt) {
        // TODO
    }

    public void presentTargetGradeRequirements(TargetGradeRequirementsResponse response) {
        // TODO
    }

    @Override
    public void presentPendingAttempts() {
        //TODO
    }

    static class ErrorResponseException extends RuntimeException {
        final String errorMessage;

        ErrorResponseException(String errorMessage) {
            this.errorMessage = errorMessage;
        }
    }
}
