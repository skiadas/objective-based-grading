package webserver.user;

import obg.gateway.Gateway;
import spark.Request;
import webserver.firewall.UserProvider;

import java.util.ArrayList;
import java.util.List;

public class UserAdministrator implements UserProvider<User> {

    private final Gateway gateway;

    public UserAdministrator(Gateway gateway) {
        this.gateway = gateway;
    }

    public User retrieveUser(Request req) {
        User storedUser = req.session().attribute("user");
        return storedUser == null ? new UnauthenticatedUser() : storedUser;
    }

    public void loginUser(String username, Request req) {
        req.session().attribute("user", authenticateUser(username));
    }

    public void logoutCurrentUser(Request req) {
        req.session().attribute("user", new UnauthenticatedUser());
    }

    private AuthenticatedUser authenticateUser(String username) {
        User.Role currentRole = null;
        List<User.Role> roles = new ArrayList<>();
        if (gateway.getStudent(username) != null) {
            roles.add(User.Role.Student);
            currentRole = User.Role.Student;
        }
        if (gateway.getInstructor(username) != null) {
            roles.add(User.Role.Instructor);
            currentRole = User.Role.Instructor;
        }

        return new AuthenticatedUser(username, currentRole, roles);
    }
}