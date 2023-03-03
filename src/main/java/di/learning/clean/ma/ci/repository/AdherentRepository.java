package di.learning.clean.ma.ci.repository;

import di.learning.clean.ma.ci.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdherentRepository extends JpaRepository<User, Long> {
}
