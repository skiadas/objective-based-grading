package webserver.user;

public abstract class User {
    public abstract boolean isAuthenticated();
    public abstract boolean canActAs(Role role);

    public enum Role {
        Student, Instructor, Admin
    }
}
