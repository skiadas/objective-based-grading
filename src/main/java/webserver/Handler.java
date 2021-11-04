package webserver;

import obg.Gateway;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.template.thymeleaf.ThymeleafTemplateEngine;

import java.util.HashMap;
import java.util.Map;

public class Handler {
    private static final ThymeleafTemplateEngine engine = new ThymeleafTemplateEngine();
    private static final Gateway gateway = new InMemoryGateway();
    private final UserAdministrator userAdmin;
    private final Request req;
    private final Response res;

    public Handler(Request req, Response res, UserAdministrator userAdmin) {
        this.req = req;
        this.res = res;
        this.userAdmin = userAdmin;
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

    Object showLoginScreen() {
        return engine.render(new ModelAndView(new HashMap<>(), "login"));
    }

}
