package di.learning.clean.ma.ci.service.assignmentUser_;

import di.learning.clean.ma.ci.entity.AssignmentUser;
import di.learning.clean.ma.ci.entity.User;
import di.learning.clean.ma.ci.repository.AdherentRepository;
import di.learning.clean.ma.ci.repository.AssignmentUserRepository;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssignmentUserServiceImpl implements AssignmentUserService{
    @Autowired
    AssignmentUserRepository assignmentUserRepository;

    @Autowired
    AdherentRepository adherentRepository;

    @Override
    public String fetchAssignmentUserById(Long userId, String state) {
        User user = adherentRepository.findById(userId).get();
        List<AssignmentUser> assignmentUsers = assignmentUserRepository.findAllByUserAndAndState(user, state);
        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray = new JSONArray();

        /*
            this loop below is used to render list of assignments for a specific user using json format
         */

        for(AssignmentUser el : assignmentUsers){
            jsonObject.put("id",el.getId());
            jsonObject.put("assignmentTitle", el.getAssignment().getTitle());
            jsonObject.put("assignmentDescription", el.getAssignment().getDescription());
            jsonObject.put("isCompleted", el.getState());
            jsonArray.put(jsonObject);
            jsonObject = new JSONObject();
        }

        return jsonArray.toString();
    }
}
