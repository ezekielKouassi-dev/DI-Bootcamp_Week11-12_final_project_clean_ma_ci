package di.learning.clean.ma.ci.service.auth_;

import di.learning.clean.ma.ci.entity.Adherent;
import di.learning.clean.ma.ci.entity.Admin;
import di.learning.clean.ma.ci.entity.ProcessingCompany;
import di.learning.clean.ma.ci.entity.Role;
import di.learning.clean.ma.ci.model.UserPayload;
import di.learning.clean.ma.ci.repository.*;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService{

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private AdherentRepository adherentRepository;
    @Autowired
    private ProcessingCompanyRepository processingCompanyRepository;

    /**
     * @param userPayload
     * @return
     */
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

    /**
     * @param userPayload
     * @return
     */
    @Override
    public String login(UserPayload userPayload) {
        return null;
    }
}
