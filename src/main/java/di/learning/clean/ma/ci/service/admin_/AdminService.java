package di.learning.clean.ma.ci.service.admin_;

import di.learning.clean.ma.ci.entity.Admin;
import di.learning.clean.ma.ci.entity.Locality;
import di.learning.clean.ma.ci.entity.PointOfDrop;
import di.learning.clean.ma.ci.entity.Ranks;

import java.util.List;

public interface AdminService {
    public Admin fetchAdminById(Long adminId);

    public List<Locality> fetchAllLocality(Long adminId);

    public List<Ranks> fetchAllRank(Long ranksId);

    public List<PointOfDrop> fetchAllDropOfPoint(Long pointOfDropId);

    public String saveAdmin(Admin admin);
}
