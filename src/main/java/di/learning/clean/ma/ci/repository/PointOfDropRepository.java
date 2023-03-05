package di.learning.clean.ma.ci.repository;

import di.learning.clean.ma.ci.entity.PointOfDrop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PointOfDropRepository extends JpaRepository<PointOfDrop, Long> {
}
