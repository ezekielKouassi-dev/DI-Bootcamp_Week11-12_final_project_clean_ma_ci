package di.learning.clean.ma.ci.service.assignmentUser_;

import di.learning.clean.ma.ci.entity.Assignment;
import di.learning.clean.ma.ci.entity.AssignmentUser;
import di.learning.clean.ma.ci.entity.User;
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
        Optional<User> user = adherentRepository.findById(userId);
        JSONObject jsonObject;
        JSONArray jsonArray = new JSONArray();
        if (!user.isPresent()) {
            jsonObject = new JSONObject();
            jsonObject.put("status", HttpStatus.NOT_FOUND.value());
            jsonObject.put("message", "user dont' be found");
            return jsonObject.toString();
        }
        List<AssignmentUser> assignmentUsers = assignmentUserRepository.findAllByUserAndAndState(user.get(), state);

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

}
