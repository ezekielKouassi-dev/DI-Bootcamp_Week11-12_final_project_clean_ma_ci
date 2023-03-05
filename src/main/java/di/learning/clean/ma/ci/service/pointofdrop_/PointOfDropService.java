package di.learning.clean.ma.ci.service.pointofdrop_;

import di.learning.clean.ma.ci.entity.PointOfDrop;

public interface PointOfDropService {
    public String fetchAllPointOfDrop();

    public String savePointOfDrop(PointOfDrop pointOfDrop, Long adminId);
}
