package di.learning.clean.ma.ci.service.adherent_;

import di.learning.clean.ma.ci.entity.*;
import di.learning.clean.ma.ci.model.UserPayload;
import di.learning.clean.ma.ci.repository.AdherentRepository;
import di.learning.clean.ma.ci.repository.AdminRepository;
import di.learning.clean.ma.ci.repository.AssignmentRepository;
import di.learning.clean.ma.ci.repository.AssignmentUserRepository;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
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
    AssignmentRepository assignmentRepository;
    @Autowired
    AssignmentUserRepository assignmentUserRepository;

    @Autowired
    AdminRepository adminRepository;
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
    public String saveUser(Long userId, Long assignmentId) {
        Optional<User> user = adherentRepository.findById(userId);
        Optional<Assignment> assignment = assignmentRepository.findById(assignmentId);
        JSONObject jsonObject;

        if(!user.isPresent() || !assignment.isPresent()) {
            jsonObject = new JSONObject();
            jsonObject.put("status", HttpStatus.NOT_FOUND.value());
            jsonObject.put("message", "user or assignment don't be found");
            return jsonObject.toString();
        }

        assignment.get().setNumberOfAcceptation(assignment.get().getNumberOfAcceptation() + 1);
        AssignmentUser assignmentUser = new AssignmentUser();
        AssignmentUserId assignmentUserId = new AssignmentUserId();
        assignmentUserId.setUserId(userId);
        assignmentUserId.setAssignmentId(assignmentId);
        assignmentUser.setId(assignmentUserId);
        assignmentUser.setUser(user.get());
        assignmentUser.setAssignment(assignment.get());
        user.get().getAssignmentUsers().add(assignmentUser);
        assignment.get().getAssignmentUsers().add(assignmentUser);
        adherentRepository.save(user.get());

        jsonObject = new JSONObject();
        jsonObject.put("status", HttpStatus.OK.value());
        jsonObject.put("message", "Congrats! your work begin now!!!");
        return jsonObject.toString();
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
        JSONObject jsonObject;
        if(assignmentUser.isPresent()) {
            // throw new exception
            assignmentUser.get().setState("leave");
            assignmentUserRepository.save(assignmentUser.get());
            jsonObject = new JSONObject();
            jsonObject.put("status", HttpStatus.OK.value());
            jsonObject.put("message", "assignment has been leave");
            return jsonObject.toString();
        }

        jsonObject = new JSONObject();
        jsonObject.put("status", HttpStatus.NOT_FOUND.value());
        jsonObject.put("message", "leave assignment failed");
        return jsonObject.toString();

    }

    @Override
    public String register(UserPayload userPayload) {
        User user = new User();
        user.setLastName(userPayload.getLastName());
        user.setFirstName(userPayload.getFirstName());
        user.setUsername(userPayload.getIdentifier());
        user.setEmail(userPayload.getEmail());
        user.setRole(userPayload.getRole());
        user.setPassword(userPayload.getPassword());
        adherentRepository.save(user);

        /*
           formation of json
        */

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("status", HttpStatus.OK.value());
        jsonObject.put("message", "success");
        return jsonObject.toString();
    }

    @Override
    public String login(UserPayload userPayload) {
        Optional<User> user = adherentRepository.findUserByUsernameIgnoreCaseAndPassword(userPayload.getIdentifier(), userPayload.getPassword());
        Optional<Admin> admin = adminRepository.findAdminByUserNameAndPassword(userPayload.getIdentifier(), userPayload.getPassword());
        JSONObject jsonObject;
        if(!user.isPresent()) {
            if(!admin.isPresent()) {
                jsonObject = new JSONObject();
                jsonObject.put("status", HttpStatus.NOT_FOUND.value());
                jsonObject.put("message", "identifier or password didn't match");
                return jsonObject.toString();
            }
            jsonObject = new JSONObject();
            jsonObject.put("status", HttpStatus.OK.value());
            jsonObject.put("id", admin.get().getAdminId());
            jsonObject.put("role", admin.get().getRole());
            jsonObject.put("message", "login success");
            return jsonObject.toString();
        }

        jsonObject = new JSONObject();
        jsonObject.put("status", HttpStatus.OK.value());
        jsonObject.put("id", user.get().getAdherentId());
        jsonObject.put("role", user.get().getRole());
        jsonObject.put("message", "login success");
        return jsonObject.toString();

    }
}
