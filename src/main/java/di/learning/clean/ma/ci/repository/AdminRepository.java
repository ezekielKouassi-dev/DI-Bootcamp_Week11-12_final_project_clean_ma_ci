package di.learning.clean.ma.ci.repository;

import di.learning.clean.ma.ci.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {
    public Optional<Admin> findAdminByUserNameAndPassword(String username, String password);
}
