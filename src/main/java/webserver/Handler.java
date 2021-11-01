package webserver;

import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.template.thymeleaf.ThymeleafTemplateEngine;

import java.util.HashMap;
import java.util.Map;

public class Handler {
    private static ThymeleafTemplateEngine engine = new ThymeleafTemplateEngine();
    private final Request req;
    private final Response res;

    public Handler(Request req, Response res) {
        this.req = req;
        this.res = res;
    }

    Object handleLogout() {
        logoutCurrentUser();
        res.redirect("/");
        return null;
    }

    Object handleLoginRequest() {
        String username = req.queryParams("username");
        loginUser(username);
        res.redirect("/");
        return null;
    }

    Object handleIndex() {
        // TODO: What to show on index page
        User user = retrieveUser(req);
        if (user == null) {
            res.redirect("/login");
            return null;
        }
        Map<String, Object> model = new HashMap<>();
        model.put("user", user);
        model.put("message", "Hi!");
        return engine.render(new ModelAndView(model, "index"));
    }

    Object showLoginScreen() {
        return engine.render(new ModelAndView(new HashMap<>(), "login"));
    }

    private static User retrieveUser(Request req) {
        return req.session().attribute("user");
    }

    private void loginUser(String username) {
        req.session().attribute("user", new User(username));
    }

    private void logoutCurrentUser() {
        req.session().attribute("user", null);
    }
}
