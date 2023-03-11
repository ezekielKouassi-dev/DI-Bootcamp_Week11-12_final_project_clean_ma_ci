package di.learning.clean.ma.ci.repository;

import di.learning.clean.ma.ci.entity.Assignment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssignmentRepository extends JpaRepository<Assignment, Long> {
    public List<Assignment> findAssignmentByAssignmentIdNotIn(List<Long> assignmentIds);

}
