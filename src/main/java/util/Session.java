package util;

import model.User;

public class Session {
    private static User currentUser;  // Static user session

    public static User getCurrentUser() {
        return currentUser;
    }

    public static void setUser(User user) {
        currentUser = user;
    }
}
