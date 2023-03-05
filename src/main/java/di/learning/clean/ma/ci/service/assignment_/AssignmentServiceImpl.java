package di.learning.clean.ma.ci.service.assignment_;

import di.learning.clean.ma.ci.entity.Assignment;
import di.learning.clean.ma.ci.entity.AssignmentUser;
import di.learning.clean.ma.ci.entity.ProcessingCompany;
import di.learning.clean.ma.ci.repository.AssignmentRepository;
import di.learning.clean.ma.ci.repository.ProcessingCompanyRepository;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
@Service
public class AssignmentServiceImpl implements AssignmentService{
    @Autowired
    private AssignmentRepository assignmentRepository;

    @Autowired
    private ProcessingCompanyRepository processingCompanyRepository;
    @Override
    public List<Assignment> fetchAssignments() {
        return assignmentRepository.findAll();
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
        Assignment assignment = assignmentRepository.findById(assignmentId).get();
        JSONObject jsonObject;
        JSONArray jsonArray = new JSONArray();
        for(AssignmentUser assignmentUser : assignment.getAssignmentUsers()) {
            jsonObject = new JSONObject();
            jsonObject.put("id", assignmentUser.getUser().getAdherentId());
            jsonObject.put("fullName", assignmentUser.getUser().getFirstName() + " " + assignmentUser.getUser().getLastName());
            jsonArray.put(jsonObject);
        }
        return jsonArray.toString();
    }
}
