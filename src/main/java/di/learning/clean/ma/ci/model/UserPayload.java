package di.learning.clean.ma.ci.model;

/**
 *
 * this POJO is a recipient, I use it for retrieve user registration data
 * before insertion in database
 */

public class UserPayload {
    String lastName;
    String firstName;
    String identifier;
    String email;
    String password;
    String role = "USER";
}
