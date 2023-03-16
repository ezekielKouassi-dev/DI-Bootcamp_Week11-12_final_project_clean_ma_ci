package di.learning.clean.ma.ci.repository;

import di.learning.clean.ma.ci.entity.Ranks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RankRepository extends JpaRepository<Ranks, Long> {
}
