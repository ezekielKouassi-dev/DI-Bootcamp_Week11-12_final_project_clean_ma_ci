package di.learning.clean.ma.ci.repository;

import di.learning.clean.ma.ci.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdherentRepository extends JpaRepository<User, Long> {
    public Optional<User> findUserByUsernameIgnoreCaseAndPassword(String username, String password);
}
