package webserver;

import obg.core.AppContext;
import spark.Request;
import spark.Response;
import webserver.user.AuthenticatedUser;
import webserver.user.User;
import webserver.user.UserAdministrator;

public class Handler {
    private final UserAdministrator userAdmin;
    private final Request req;
    private final Response res;
    private final AppContext context;
    private final ResultProducer resultProducer = new ResultProducer();

    public Handler(Request req, Response res, UserAdministrator userAdmin, AppContext context) {
        this.req = req;
        this.res = res;
        this.userAdmin = userAdmin;
        this.context = context;
    }

    Object handleLogout() {
        userAdmin.logoutCurrentUser(req);
        return redirect("/");
    }

    Object handleLoginRequest() {
        String username = req.queryParams("username");
        userAdmin.loginUser(username, req);
        return redirect("/");
    }

    private Object redirect(String path) {
        res.redirect(path);
        return null;
    }

    Object handleIndex() {
        // TODO: What to show on index page
        AuthenticatedUser user = (AuthenticatedUser) userAdmin.retrieveUser(req);
        User.Role currentRole = user.getCurrentRole();
        if (currentRole.equals(User.Role.Instructor)) {
            return redirect("/instructor/" + user.getUsername());
        } else if (currentRole.equals(User.Role.Student)) {
            return redirect("/student" + user.getUsername());
        } else {
            resultProducer.addToModel("user", user);
            resultProducer.addToModel("message", "Hi!");
            resultProducer.presentIndexPage();
            return resultProducer.result;
        }
    }

    Object getInstructorIndexPage() {
        String instructorId = req.params("username");
        context.instructorCourseListRequested(instructorId, resultProducer);
        return resultProducer.result;
    }

    Object getStudentIndexPage() {
        String studentId = req.params("username");
        context.studentCourseListRequested(studentId, resultProducer);
        return resultProducer.result;
    }

    Object showLoginScreen() {
        resultProducer.presentLoginScreen();
        return resultProducer.result;
    }

}
