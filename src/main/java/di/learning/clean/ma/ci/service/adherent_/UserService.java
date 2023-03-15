package di.learning.clean.ma.ci.service.adherent_;

import di.learning.clean.ma.ci.entity.Adherent;
import di.learning.clean.ma.ci.model.UserPayload;

import java.util.List;

public interface UserService {
    public List<Adherent> fetchAllUser();

    public Adherent fetchUserById(Long userId);

    public String saveUser(Long userId, Long assignmentId);

    public Adherent updateUser(Long userId, Adherent adherent);

    public String deleteUserById(Long userId);

    public String leaveAssignment(Long userId, Long assignmentId);

    public String register(UserPayload userPayload);

    public String login(UserPayload userPayload);

    //public ResponseEntity<Page<?>> fetchAllAssignmentByState(Pageable , Sort );
}
