package di.learning.clean.ma.ci.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * this POJO is a recipient, I use it for retrieve user registration data
 * before insertion in database
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserPayload {
    String lastName;
    String firstName;
    String identifier;
    String email;
    String password;
    String role = "USER";
}
