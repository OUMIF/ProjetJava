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
<<<<<<< HEAD
        // Set the current user in the session
=======

>>>>>>> ed2afd397cdbe6cd396fc0a6856205b0d1d61bcf
        // Optionally, store the token for session management
        return token;
    }
}
