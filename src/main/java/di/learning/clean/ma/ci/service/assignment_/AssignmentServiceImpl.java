package di.learning.clean.ma.ci.service.assignment_;

import di.learning.clean.ma.ci.entity.Adherent;
import di.learning.clean.ma.ci.entity.Assignment;
import di.learning.clean.ma.ci.entity.AssignmentUser;
import di.learning.clean.ma.ci.entity.ProcessingCompany;
import di.learning.clean.ma.ci.repository.AdherentRepository;
import di.learning.clean.ma.ci.repository.AssignmentRepository;
import di.learning.clean.ma.ci.repository.AssignmentUserRepository;
import di.learning.clean.ma.ci.repository.ProcessingCompanyRepository;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
@Service
public class AssignmentServiceImpl implements AssignmentService{
    @Autowired
    private AssignmentRepository assignmentRepository;
    @Autowired
    private AdherentRepository adherentRepository;
    @Autowired
    private AssignmentUserRepository assignmentUserRepository;

    @Autowired
    private ProcessingCompanyRepository processingCompanyRepository;
    @PersistenceContext
    private EntityManager em;
    @Override
    public List<Assignment> fetchAssignments() {
        return assignmentRepository.findAll();
    }

    /**
     * @param start
     * @param limit
     * @param search_value
     * @return
     */
    @Override
    public List<Assignment> fetchAllOrOneAssignment(int start, int limit, String search_value, List<Long> assignmentIds) {
        List<Assignment> assignmentList = new ArrayList<>();
        try{
            Query q = em.createQuery("select e from Assignment e where e.assignmentId not in :assignmentIds")
                    .setParameter("assignmentIds", assignmentIds)
                    .setFirstResult(start)
                    .setMaxResults(limit);
            assignmentList = q.getResultList();
        }catch (Exception e) {
            e.printStackTrace();
        }
        return assignmentList;
    }

    @Override
    public Assignment fetchAssignment(Long assignmentId) {
        Optional<Assignment> assignment = assignmentRepository.findById(assignmentId);

        if(!assignment.isPresent()) {
            // throw new AssignmentNotFoundExceptionHandler("Assignment not found");
        }
        return assignment.get();
    }

    @Override
    public String saveAssignment(Assignment assignment, Long processingCompanyId) {
        Optional<ProcessingCompany> processingCompany = processingCompanyRepository.findById(processingCompanyId);

        if(!processingCompany.isPresent()) {
            return "registration failed this processing company is not found ";
        }
        assignment.setProcessingCompany(processingCompany.get());
        assignmentRepository.save(assignment);
        return "successfully register assignment";
    }

    @Override
    public Assignment updateAssignment(Long assignmentId, Assignment assignment) {
        Assignment ass = assignmentRepository.findById(assignmentId).get();

        if(Objects.nonNull(assignment.getTitle()) &&
                !"".equalsIgnoreCase(assignment.getTitle())) {
            ass.setTitle(assignment.getTitle());
        }

        if(Objects.nonNull(assignment.getDescription()) &&
                !"".equalsIgnoreCase(assignment.getDescription())) {
            ass.setDescription(assignment.getDescription());
        }

        return assignmentRepository.save(ass);
    }

    @Override
    public String fetchAllCollaborator(Long assignmentId) {
        JSONObject jsonObject;
        JSONArray jsonArray = new JSONArray();
        if(!assignmentRepository.findById(assignmentId).isPresent()) {
            jsonObject = new JSONObject();
            jsonObject.put("status", HttpStatus.NOT_FOUND.value());
            jsonObject.put("message", "resources not found");
            return jsonObject.toString();
        }
        for(AssignmentUser assignmentUser : assignmentRepository.findById(assignmentId).get().getAssignmentUsers()) {
            jsonObject = new JSONObject();
            jsonObject.put("id", assignmentUser.getAdherent().getAdherentId());
            jsonObject.put("fullName", assignmentUser.getAdherent().getFirstName() + " " + assignmentUser.getAdherent().getLastName());
            jsonArray.put(jsonObject);
        }
        jsonObject = new JSONObject();
        jsonObject.put("status", HttpStatus.OK.value());
        jsonObject.put("message", "success");
        jsonObject.put("data", jsonArray);
        return jsonObject.toString();
    }

    /**
     * @param userId
     * @return
     */
    @Override
    public String fetchAvailableAssignments(Long userId, int start, int limit, String search_value) {

        Optional<Adherent> user = adherentRepository.findById(userId);
        JSONObject jsonObject;
        JSONArray jsonArray = new JSONArray();

        if(!user.isPresent()) {
            jsonObject = new JSONObject();
            jsonObject.put("status", HttpStatus.NOT_FOUND.value());
            jsonObject.put("message", "user don't be found");
            return jsonObject.toString();
        }

        List<AssignmentUser> assignmentUsers = assignmentUserRepository.findAssignmentUserByAdherent(user.get());

        if(assignmentUsers.size() > 0) {
            List<Long> assignmentIds = new ArrayList<Long>();
            for(AssignmentUser assignmentUser : assignmentUsers) {
                assignmentIds.add(assignmentUser.getAssignment().getAssignmentId());
            }



            for(Assignment assignment : this.fetchAllOrOneAssignment(start, limit, search_value, assignmentIds)) {
                jsonObject = new JSONObject();
                jsonObject.put("assignmentId", assignment.getAssignmentId());
                jsonObject.put("assignmentTitle", assignment.getTitle());
                jsonObject.put("assignmentDescription", assignment.getDescription());
                jsonObject.put("numberOfAdherent", assignment.getNumberOfAdherent());
                jsonObject.put("numberOfAcceptation", assignment.getNumberOfAcceptation());
                jsonObject.put("reward", assignment.getReward());
                jsonArray.put(jsonObject);
            }


            jsonObject = new JSONObject();
            jsonObject.put("draw", 1);
            // jsonObject.put("recordsTotal", this.CountAllAvailableAssignments(start, limit, search_value, assignmentIds));
            // jsonObject.put("recordsFiltered", this.CountAllAvailableAssignments(start, limit, search_value, assignmentIds));
            jsonObject.put("data", jsonArray);


            return jsonObject.toString();
        }

        for(Assignment assignment : assignmentRepository.findAll()) {
            jsonObject = new JSONObject();
            jsonObject.put("assignmentId", assignment.getAssignmentId());
            jsonObject.put("assignmentTitle", assignment.getTitle());
            jsonObject.put("assignmentDescription", assignment.getDescription());
            jsonObject.put("numberOfAdherent", assignment.getNumberOfAdherent());
            jsonObject.put("numberOfAcceptation", assignment.getNumberOfAcceptation());
            jsonObject.put("reward", assignment.getReward());
            jsonArray.put(jsonObject);
        }

        jsonObject = new JSONObject();
        jsonObject.put("status", HttpStatus.OK.value());
        jsonObject.put("message", "success");
        jsonObject.put("data", jsonArray);
        return jsonObject.toString();

    }

    /**
     * @param start
     * @param limit
     * @param search_value
     * @param assignmentIds
     * @return
     */
    @Override
    public int CountAllAvailableAssignments(int start, int limit, String search_value, List<Long> assignmentIds) {
        int total = 0;
        try{
            Query q = em.createQuery("select count(e.assignmentId) from Assignment e where e.title LIKE ?1 or e.description LIKE ?1 and e.assignmentId not in :assignmentIds")
                    .setParameter(1, search_value)
                    .setFirstResult(start)
                    .setMaxResults(limit);
            total = (int) q.getSingleResult();
        }catch (Exception e) {
            e.printStackTrace();
        }
        return total;
    }
}
