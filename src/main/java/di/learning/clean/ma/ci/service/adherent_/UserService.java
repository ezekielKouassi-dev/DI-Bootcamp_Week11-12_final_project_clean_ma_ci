package di.learning.clean.ma.ci.service.adherent_;

import di.learning.clean.ma.ci.entity.User;
import di.learning.clean.ma.ci.model.UserPayload;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService {
    public List<User> fetchAllUser();

    public User fetchUserById(Long userId);

    public String saveUser(User user);

    public User updateUser(Long userId, User user);

    public String deleteUserById(Long userId);

    public String leaveAssignment(Long userId, Long assignmentId);

    public String register(UserPayload userPayload);

    public String login(UserPayload userPayload);

    //public ResponseEntity<Page<?>> fetchAllAssignmentByState(Pageable , Sort );
}
