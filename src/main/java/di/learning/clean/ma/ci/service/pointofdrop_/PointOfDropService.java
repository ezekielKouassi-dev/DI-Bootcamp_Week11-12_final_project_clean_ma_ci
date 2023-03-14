package di.learning.clean.ma.ci.service.pointofdrop_;

import di.learning.clean.ma.ci.entity.PointOfDrop;
import di.learning.clean.ma.ci.model.PointOfDropPayload;

public interface PointOfDropService {
    public String fetchAllPointOfDrop();

    public String savePointOfDrop(PointOfDropPayload pointOfDropName, Long adminId);
}
