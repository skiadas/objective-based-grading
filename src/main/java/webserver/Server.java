package webserver;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Spark;
import spark.template.thymeleaf.ThymeleafTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;

public class Server {
    private static Logger logger = LogManager.getLogger();
    private static ThymeleafTemplateEngine engine = new ThymeleafTemplateEngine();

    public static void main(String[] args) {
        before("/*", (req, res) -> logger.info("Received call: " + req.pathInfo()));
        get("/", Server::handleIndex);
        get("/login", Server::showLoginScreen);
        post("/login", Server::handleLoginRequest);
        post("/logout", Server::handleLogout);
        logger.info("Server started, serving at localhost:" + Spark.port());
    }

    private static Object handleLogout(Request req, Response res) {
        req.session().attribute("user", null);
        res.redirect("/");
        return null;

    }

    private static Object handleLoginRequest(Request req, Response res) {
        String username = req.queryParams("username");
        req.session().attribute("user", new User(username));
        res.redirect("/");
        return null;
    }

    private static Object showLoginScreen(Request req, Response res) {
        return engine.render(new ModelAndView(new HashMap<>(), "login"));
    }

    private static Object handleIndex(Request req, Response res) {
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

    private static User retrieveUser(Request req) {
        return req.session().attribute("user");
    }
}
