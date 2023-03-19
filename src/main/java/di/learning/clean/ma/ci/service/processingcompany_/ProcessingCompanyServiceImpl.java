package di.learning.clean.ma.ci.service.processingcompany_;

import di.learning.clean.ma.ci.entity.Admin;
import di.learning.clean.ma.ci.entity.Assignment;
import di.learning.clean.ma.ci.entity.ProcessingCompany;
import di.learning.clean.ma.ci.model.ProcessingCompanyPayload;
import di.learning.clean.ma.ci.repository.AdminRepository;
import di.learning.clean.ma.ci.repository.ProcessingCompanyRepository;
import di.learning.clean.ma.ci.repository.RoleRepository;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ProcessingCompanyServiceImpl implements ProcessingCompanyService{
    @Autowired
    private ProcessingCompanyRepository processingCompanyRepository;

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private RoleRepository roleRepository;

    /**
     *
     * @return
     */
    @Override
    public String fetchAllProcessingCompanies() {
        JSONObject jsonObject;
        JSONArray jsonArray = new JSONArray();

        for(ProcessingCompany processingCompany: processingCompanyRepository.findAll()) {
            jsonObject = new JSONObject();
            jsonObject.put("id", processingCompany.getUserId());
            jsonObject.put("name", (processingCompany.getLastName() != null) ? processingCompany.getLastName() : "");
            jsonObject.put("email", (processingCompany.getEmail() != null) ? processingCompany.getEmail() : "");
            jsonObject.put("phone", (processingCompany.getPhone() != null) ? processingCompany.getPhone() : "");
            jsonObject.put("username", (processingCompany.getUserName() != null) ? processingCompany.getUserName() : "");
            jsonArray.put(jsonObject);
        }

        jsonObject = new JSONObject();
        jsonObject.put("status", HttpStatus.OK.value());
        jsonObject.put("message", "success");
        jsonObject.put("data", jsonArray);
        return jsonObject.toString();
    }

    /**
     * get company using id property
     *
     * @param processingCompanyId
     * @return
     */
    @Override
    public ProcessingCompany fetchProcessingCompany(Long processingCompanyId) {
        if(!processingCompanyRepository.findById(processingCompanyId).isPresent()) {
            // TODO : throw exception not found
            return null;
        }
        return processingCompanyRepository.findById(processingCompanyId).get();
    }

    /**
     *
     * @param processingCompanyPayload
     * @return
     */
    @Override
    public String saveProcessingCompany(ProcessingCompanyPayload processingCompanyPayload) {
        Optional<Admin> admin = adminRepository.findById(processingCompanyPayload.getAdminId());
        JSONObject jsonObject;

        if(!admin.isPresent()) {
            jsonObject = new JSONObject();
            jsonObject.put("status", HttpStatus.NOT_FOUND.value());
            jsonObject.put("message", "your session is out");
            return jsonObject.toString();
        }

        ProcessingCompany processingCompany = new ProcessingCompany();
        processingCompany.setLastName(processingCompanyPayload.getName());
        processingCompany.setUserName(processingCompanyPayload.getUsername());
        processingCompany.setAdmin(admin.get());
        processingCompany.setPhone(processingCompanyPayload.getPhone());
        processingCompany.setEmail(processingCompanyPayload.getEmail());
        processingCompany.setPassword(processingCompanyPayload.getPassword());
        processingCompany.setRole(roleRepository.findById(3L).get());
        processingCompanyRepository.save(processingCompany);

        jsonObject = new JSONObject();
        jsonObject.put("status", HttpStatus.OK.value());
        jsonObject.put("message", "successfully save processing company");
        return jsonObject.toString();
    }

    /**
     *
     * @param processingCompanyId
     * @return
     */
    @Override
    public String deleteProcessingCompany(Long processingCompanyId) {
        if(!processingCompanyRepository.findById(processingCompanyId).isPresent()) {
            return null;
        }
        processingCompanyRepository.delete(processingCompanyRepository.findById(processingCompanyId).get());
        return "successfully delete processing Company";
    }

    /**
     *
     * @param processingCompanyId
     * @return
     */
    @Override
    public String updateProcessingCompany(Long processingCompanyId, ProcessingCompany processingCompany) {
        // TODO : add verification to processing company
        ProcessingCompany prc = processingCompanyRepository.findById(processingCompanyId).get();

        if(Objects.nonNull(prc.getLastName()) &&
                !"".equalsIgnoreCase(prc.getLastName())
        ) {
            prc.setLastName(processingCompany.getLastName());
        }

        if(Objects.nonNull(prc.getEmail()) &&
                !"".equalsIgnoreCase(prc.getEmail())
        ) {
            prc.setEmail(processingCompany.getEmail());
        }

        if(Objects.nonNull(prc.getPhone()) &&
                !"".equalsIgnoreCase(prc.getPhone())
        ) {
            prc.setPhone(processingCompany.getPhone());
        }

        // TODO : add all condition for update

        processingCompanyRepository.save(prc);
        return "successfully update processing company";
    }

    /**
     *
     * @param processingCompanyId
     * @return
     */
    @Override
    public String fetchAllAssignment(Long processingCompanyId) {
        Optional<ProcessingCompany> processingCompany = processingCompanyRepository.findById(processingCompanyId);
        JSONObject jsonObject;
        JSONArray jsonArray = new JSONArray();
        if(!processingCompany.isPresent()) {
            return null;
        }

        for (Assignment assignment : processingCompany.get().getAssignmentList()) {
            jsonObject = new JSONObject();
            jsonObject.put("id", assignment.getAssignmentId());

            jsonObject.put("title", assignment.getTitle());
            jsonObject.put("description", assignment.getDescription());
            jsonObject.put("numberOfAdherent", assignment.getNumberOfAdherent());
            jsonObject.put("numberOfAcceptation", assignment.getNumberOfAcceptation());
            jsonObject.put("pointOfDrop", assignment.getPointOfDrop().getName());
            jsonObject.put("duration", assignment.getDuration());
            jsonObject.put("reward", assignment.getReward());
            jsonObject.put("isCompleted", assignment.isCompleted());
            jsonArray.put(jsonObject);
        }

        jsonObject = new JSONObject();
        jsonObject.put("status", HttpStatus.OK.value());
        jsonObject.put("message", "success");
        jsonObject.put("data", jsonArray);

        return jsonObject.toString();
    }
}
