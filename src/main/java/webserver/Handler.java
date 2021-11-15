package webserver;

import obg.core.AppContext;
import obg.core.entity.Course;
import obg.response.CourseListResponse;
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

public class Handler {
    private static final ThymeleafTemplateEngine engine = new ThymeleafTemplateEngine();
    private final UserAdministrator userAdmin;
    private final Request req;
    private final Response res;
    private final AppContext context;

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
        Map<String, Object> model = new HashMap<>();
        String instructorId = req.params("username");
        obg.core.Request request = context.getInstructorCourseListRequest(instructorId);
        obg.core.Response response = context.handle(request);
        List<Course> courses = ((CourseListResponse) response).courses;
        // TODO: Add user here?
        model.put("courses", courses);
        return engine.render(new ModelAndView(model, "instructorIndex"));
    }

    Object showLoginScreen() {
        return engine.render(new ModelAndView(new HashMap<>(), "login"));
    }
}
