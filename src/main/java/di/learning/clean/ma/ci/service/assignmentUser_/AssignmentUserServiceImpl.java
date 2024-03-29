package di.learning.clean.ma.ci.service.assignmentUser_;

import di.learning.clean.ma.ci.entity.Adherent;
import di.learning.clean.ma.ci.entity.AssignmentUser;
import di.learning.clean.ma.ci.repository.AdherentRepository;
import di.learning.clean.ma.ci.repository.AssignmentRepository;
import di.learning.clean.ma.ci.repository.AssignmentUserRepository;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AssignmentUserServiceImpl implements AssignmentUserService {
    @Autowired
    AssignmentUserRepository assignmentUserRepository;

    @Autowired
    AdherentRepository adherentRepository;

    @Autowired
    AssignmentRepository assignmentRepository;

    @Override
    public String fetchAssignmentUserById(Long userId, String state) {
        Optional<Adherent> user = adherentRepository.findById(userId);
        JSONObject jsonObject;
        JSONArray jsonArray = new JSONArray();
        if (!user.isPresent()) {
            jsonObject = new JSONObject();
            jsonObject.put("status", HttpStatus.NOT_FOUND.value());
            jsonObject.put("message", "user dont' be found");
            return jsonObject.toString();
        }
        List<AssignmentUser> assignmentUsers = assignmentUserRepository.findAllByAdherentAndState(user.get(), state);

        for (AssignmentUser el : assignmentUsers) {
            jsonObject = new JSONObject();
            jsonObject.put("assignmentId", el.getId().getAssignmentId());
            jsonObject.put("assignmentTitle", el.getAssignment().getTitle());
            jsonObject.put("assignmentDescription", el.getAssignment().getDescription());
            jsonObject.put("isCompleted", el.getState());
            jsonArray.put(jsonObject);
        }
        jsonObject = new JSONObject();
        jsonObject.put("status", HttpStatus.OK.value());
        jsonObject.put("message", "success");
        jsonObject.put("data", jsonArray);
        return jsonObject.toString();
    }

    /**
     * @param state
     * @param localityName
     * @return
     */
    @Override
    public String fetchStatistcsPerLocality(String state, String localityName) {
        JSONObject jsonObject = new JSONObject();
        double result = 0;
        result = assignmentUserRepository.countAllByAssignment_PointOfDrop_Locality_NameAndState(localityName, state);

        jsonObject.put("status", HttpStatus.OK.value());
        jsonObject.put("message", "success");
        jsonObject.put("data", result);

        return jsonObject.toString();
    }

    /**
     * @param adherentId
     * @return
     */
    @Override
    public String fetchStatisticsForAdherent(Long adherentId) {
        JSONObject jsonObject = new JSONObject();
        Optional<Adherent> adherent = adherentRepository.findById(adherentId);

        if(!adherent.isPresent()) {
            jsonObject.put("status", HttpStatus.NOT_FOUND.value());
            jsonObject.put("message", "not found");
            return jsonObject.toString();
        }

        double dblTotalAssignmentByUser =  assignmentUserRepository.countAssignmentUserByAdherent(adherent.get());
        double dblTotalAssignmentProgress = assignmentUserRepository.countAssignmentUserByAdherentAndState(adherent.get(), "progress");
        double dblTotalAssignmentCompleted = assignmentUserRepository.countAssignmentUserByAdherentAndState(adherent.get(), "completed");
        double dblTotalAssignmentLeave = assignmentUserRepository.countAssignmentUserByAdherentAndState(adherent.get(), "leave");


        double dblStatsAssignmentProgress = (dblTotalAssignmentProgress * 100)/dblTotalAssignmentByUser;
        double dblStatsAssignmentCompleted = (dblTotalAssignmentCompleted * 100)/dblTotalAssignmentByUser;
        double dblStatsAssignmentLeave = (dblTotalAssignmentLeave * 100)/dblTotalAssignmentByUser;
        jsonObject.put("status", HttpStatus.OK.value());
        jsonObject.put("message", "success");
        jsonObject.put("dblStatsAssignmentProgress", dblStatsAssignmentProgress);
        jsonObject.put("dblStatsAssignmentCompleted", dblStatsAssignmentCompleted);
        jsonObject.put("dblStatsAssignmentLeave", dblStatsAssignmentLeave);
        return jsonObject.toString();
    }

}