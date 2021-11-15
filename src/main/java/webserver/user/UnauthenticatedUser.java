package webserver.user;

public class UnauthenticatedUser extends User {

    public boolean isAuthenticated() {
        return false;
    }

    public boolean canActAs(Role role) {
        return false;
    }
}
