package di.learning.clean.ma.ci.service.adherent_;

import di.learning.clean.ma.ci.entity.*;
import di.learning.clean.ma.ci.model.UserPayload;
import di.learning.clean.ma.ci.repository.*;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    ProcessingCompanyRepository processingCompanyRepository;
    @Override
    public List<Adherent> fetchAllUser() {
        return adherentRepository.findAll();
    }

    @Override
    public Adherent fetchUserById(Long userId)  {
        if(!adherentRepository.findById(userId).isPresent()) {
            // throw new AdherentNotFoundExceptionHandler("Adherent not found");
        }
        return adherentRepository.findById(userId).get();
    }

    @Override
    public String saveUser(Long userId, Long assignmentId) {
        Optional<Adherent> user = adherentRepository.findById(userId);
        Optional<Assignment> assignment = assignmentRepository.findById(assignmentId);
        JSONObject jsonObject;

        if(!user.isPresent() || !assignment.isPresent()) {
            jsonObject = new JSONObject();
            jsonObject.put("status", HttpStatus.NOT_FOUND.value());
            jsonObject.put("message", "user or assignment don't be found");
            return jsonObject.toString();
        }

        System.out.println("I will find you");

        assignment.get().setNumberOfAcceptation(assignment.get().getNumberOfAcceptation() + 1);
        AssignmentUser assignmentUser = new AssignmentUser();
        AssignmentUserId assignmentUserId = new AssignmentUserId();
        assignmentUserId.setAdherentId(userId);
        assignmentUserId.setAssignmentId(assignmentId);
        assignmentUser.setId(assignmentUserId);
        assignmentUser.setAdherent(user.get());
        assignmentUser.setAssignment(assignment.get());
        user.get().getAssignmentUsers().add(assignmentUser);
        assignment.get().getAssignmentUsers().add(assignmentUser);
        assignmentUserRepository.save(assignmentUser);
        assignmentRepository.save(assignment.get());
        adherentRepository.save(user.get());

        jsonObject = new JSONObject();
        jsonObject.put("status", HttpStatus.OK.value());
        jsonObject.put("message", "Congrats! your work begin now!!!");
        return jsonObject.toString();
    }

    /*
        this method is for update Adherent properties, for now it is only possible
        to update the firstname, lastname, and Adherent's password
     */
    @Override
    public Adherent updateUser(Long userId, Adherent adherent) {
        Adherent adr = adherentRepository.findById(userId).get();

        if(Objects.nonNull(adherent.getFirstName()) &&
                !"".equalsIgnoreCase(adherent.getFirstName())
        ) {
            adr.setFirstName(adherent.getFirstName());
        }

        if(Objects.nonNull(adherent.getLastName()) &&
                !"".equalsIgnoreCase(adherent.getLastName())
        ) {
            adr.setLastName(adherent.getLastName());
        }

        if(Objects.nonNull(adherent.getPassword()) &&
                !"".equalsIgnoreCase(adherent.getPassword())
        ) {
            adr.setPassword(adherent.getPassword());
        }
        return adherentRepository.save(adr);
    }

    @Override
    public String deleteUserById(Long adherentId) {
        Optional<Adherent> adherent = adherentRepository.findById(adherentId);

        if(!adherent.isPresent()) {
            // throw new AdherentNotFoundExceptionHandler("Adherent is not found");
        }
        adherentRepository.deleteById(adherentId);
        return "Adherent successfully deleted";
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
        Optional<Role> role = roleRepository.findById(userPayload.getRoleId());

        if(!role.isPresent()) {
            return  null;
        }

        switch (role.get().getRoleName()) {
            case "USER":
                Adherent adherent = new Adherent();

                adherent.setLastName(userPayload.getLastName());
                adherent.setFirstName(userPayload.getFirstName());
                adherent.setUserName(userPayload.getIdentifier());
                adherent.setEmail(userPayload.getEmail());
                adherent.setRole(role.get());
                adherent.setPassword(userPayload.getPassword());

                adherentRepository.save(adherent);
                break;

            case "ADMIN":
                Admin admin = new Admin();

                admin.setLastName(userPayload.getLastName());
                admin.setFirstName(userPayload.getFirstName());
                admin.setUserName(userPayload.getIdentifier());
                admin.setEmail(userPayload.getEmail());
                admin.setRole(role.get());
                admin.setPassword(userPayload.getPassword());

                adminRepository.save(admin);
                break;

            case "SOCIETY":
                ProcessingCompany processingCompany = new ProcessingCompany();

                processingCompany.setLastName(userPayload.getLastName());
                processingCompany.setUserName(userPayload.getIdentifier());
                processingCompany.setEmail(userPayload.getEmail());
                processingCompany.setRole(role.get());
                processingCompany.setPassword(userPayload.getPassword());

                processingCompanyRepository.save(processingCompany);
            default:
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("status", HttpStatus.NOT_FOUND.value());
                jsonObject.put("message", "user don't be found");

                return jsonObject.toString();

        }

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("status", HttpStatus.OK.value());
        jsonObject.put("message", "success");
        return jsonObject.toString();
    }

    @Override
    public String login(UserPayload userPayload) {
        Optional<Adherent> user = adherentRepository.findUserByUserNameIgnoreCaseAndPassword(userPayload.getIdentifier(), userPayload.getPassword());
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
