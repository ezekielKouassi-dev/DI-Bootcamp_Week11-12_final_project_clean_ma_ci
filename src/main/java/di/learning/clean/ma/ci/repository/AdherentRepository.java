package di.learning.clean.ma.ci.repository;

import di.learning.clean.ma.ci.entity.Adherent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdherentRepository extends JpaRepository<Adherent, Long> {
    public Optional<Adherent> findUserByUserNameIgnoreCaseAndPassword(String username, String password);
}
