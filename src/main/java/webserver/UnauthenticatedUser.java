package webserver;

public class UnauthenticatedUser extends User {

    public boolean isAuthenticated() {
        return false;
    }

    boolean canActAs(Role role) {
        return false;
    }
}
