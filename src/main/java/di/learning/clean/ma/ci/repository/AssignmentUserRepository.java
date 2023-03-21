package di.learning.clean.ma.ci.repository;

import di.learning.clean.ma.ci.entity.Adherent;
import di.learning.clean.ma.ci.entity.Assignment;
import di.learning.clean.ma.ci.entity.AssignmentUser;
import di.learning.clean.ma.ci.entity.AssignmentUserId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssignmentUserRepository extends JpaRepository<AssignmentUser, AssignmentUserId> {
    public List<AssignmentUser> findAllByAdherentAndState(Adherent adherent, String state);
    public List<AssignmentUser> findAssignmentUserByAdherent(Adherent adherent);
    public List<AssignmentUser> findAllByAssignmentAndState(Assignment assignment, String state);
    /*
    * method for count all assignment that has been completed, leave or in progress for a specific adherent
    * */
   public double countAllByAdherent_UserIdAndState(Long adherentId, String state);

    /*
    * method for count all user which completed, leave or progress assignment for a specific processing company
    * */
    public double countAllByAssignment_ProcessingCompany_UserIdAndState(Long processingCompanyId, String state);

    /*
    * method for all assignment that has been completed, leave or in progress per locality
    * */
    public double countAllByAssignment_PointOfDrop_Locality_NameAndState(String localityName, String state);

    /*
    * method for all assignment for a specific user
    * */
    public double countAssignmentUserByAdherent(Adherent adherent);

    /*
    * method for all assignment for a specific user by the state
    * */
    public double countAssignmentUserByAdherentAndState(Adherent adherent, String state);
}