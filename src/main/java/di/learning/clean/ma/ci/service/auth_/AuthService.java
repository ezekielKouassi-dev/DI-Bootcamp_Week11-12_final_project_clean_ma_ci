package di.learning.clean.ma.ci.service.auth_;

import di.learning.clean.ma.ci.model.UserPayload;

public interface AuthService {
    public String register(UserPayload userPayload);

    public String login(UserPayload userPayload);
}
