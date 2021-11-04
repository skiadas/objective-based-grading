package webserver;

import java.util.ArrayList;
import java.util.List;

public class AuthenticatedUser extends User {
    private final String username;
    private Role currentRole;
    private final List<Role> roles;

    public AuthenticatedUser(String username, Role currentRole, List<Role> roles) {
        this.username = username;
        this.currentRole = currentRole;
        this.roles = roles;
    }

    public boolean isAuthenticated(){ return true; }

    boolean canActAs(Role role) {
        return currentRole.equals(Role.Admin) || currentRole.equals(role);
    }

    Role getCurrentRole() {
        return currentRole;
    }

    List<Role> getRoles() {
        return roles;
    }

    public String getUsername() {
        return username;
    }
}
