package util;

import model.User;

import java.util.UUID;

public class TokenUtil {

    public static String generateToken(User user) {
        // Generate a unique token using UUID
        String token = UUID.randomUUID().toString();

        // Log the generated token
        System.out.println("Token generated for user: " + user.getEmail());
        System.out.println("Generated Token: " + token);
        Session.setUser(user);

        // Optionally, store the token for session management
        return token;
    }
}
