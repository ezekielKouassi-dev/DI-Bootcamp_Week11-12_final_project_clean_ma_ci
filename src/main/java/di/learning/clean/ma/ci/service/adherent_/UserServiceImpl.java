package di.learning.clean.ma.ci.service.adherent_;

import di.learning.clean.ma.ci.entity.AssignmentUser;
import di.learning.clean.ma.ci.entity.AssignmentUserId;
import di.learning.clean.ma.ci.entity.User;
import di.learning.clean.ma.ci.repository.AdherentRepository;
import di.learning.clean.ma.ci.repository.AssignmentUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    AdherentRepository adherentRepository;
    @Autowired
    AssignmentUserRepository assignmentUserRepository;
    @Override
    public List<User> fetchAllUser() {
        return adherentRepository.findAll();
    }

    @Override
    public User fetchUserById(Long userId)  {
        if(!adherentRepository.findById(userId).isPresent()) {
            // throw new AdherentNotFoundExceptionHandler("User not found");
        }
        return adherentRepository.findById(userId).get();
    }

    @Override
    public String saveUser(User User) {
        adherentRepository.save(User);
        return "successfully save User";
    }

    /*
        this method is for update User properties, for now it is only possible
        to update the firstname, lastname, and User's password
     */
    @Override
    public User updateUser(Long userId, User user) {
        User adr = adherentRepository.findById(userId).get();

        if(Objects.nonNull(user.getFirstName()) &&
                !"".equalsIgnoreCase(user.getFirstName())
        ) {
            adr.setFirstName(user.getFirstName());
        }

        if(Objects.nonNull(user.getLastName()) &&
                !"".equalsIgnoreCase(user.getLastName())
        ) {
            adr.setLastName(user.getLastName());
        }

        if(Objects.nonNull(user.getPassword()) &&
                !"".equalsIgnoreCase(user.getPassword())
        ) {
            adr.setPassword(user.getPassword());
        }
        return adherentRepository.save(adr);
    }

    @Override
    public String deleteUserById(Long adherentId) {
        Optional<User> adherent = adherentRepository.findById(adherentId);

        if(!adherent.isPresent()) {
            // throw new AdherentNotFoundExceptionHandler("User is not found");
        }
        adherentRepository.deleteById(adherentId);
        return "User successfully deleted";
    }

    @Override
    public String leaveAssignment(Long userId, Long assignmentId) {
        AssignmentUserId assignmentUserId = new AssignmentUserId(userId, assignmentId);
        Optional<AssignmentUser> assignmentUser = assignmentUserRepository.findById(assignmentUserId);
        if(assignmentUser.isPresent()) {
            // throw new exception
            assignmentUser.get().setState("leave");
            assignmentUserRepository.save(assignmentUser.get());
            return "assignment has been leave";
        }
        return "leave assignment failed";

    }

    /*@Override
    public ResponseEntity<Page<?>> fetchAllAssignmentByState(Pageable pageable, Sort sort) {
        Page<AssignmentUser>
        return null;
    }*/
}
