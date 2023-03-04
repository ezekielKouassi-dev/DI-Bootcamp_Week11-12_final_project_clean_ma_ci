package di.learning.clean.ma.ci.repository;

import di.learning.clean.ma.ci.entity.ProcessingCompany;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProcessingCompanyRepository extends JpaRepository<ProcessingCompany, Long> {
}
