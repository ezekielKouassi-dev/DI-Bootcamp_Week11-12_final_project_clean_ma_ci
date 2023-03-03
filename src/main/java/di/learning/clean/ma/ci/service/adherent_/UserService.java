package di.learning.clean.ma.ci.service.adherent_;

import di.learning.clean.ma.ci.entity.User;

import java.util.List;

public interface UserService {
    public List<User> fetchAllUser();

    public User fetchUserById(Long userId);

    public String saveUser(User user);

    public User updateUser(Long userId, User user);

    public String deleteUserById(Long userId);

    public String leaveAssignment(Long userId, Long assignmentId);
}
