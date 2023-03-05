package di.learning.clean.ma.ci.service.admin_;

import di.learning.clean.ma.ci.entity.Admin;
import di.learning.clean.ma.ci.entity.Locality;
import di.learning.clean.ma.ci.entity.PointOfDrop;
import di.learning.clean.ma.ci.entity.Ranks;
import di.learning.clean.ma.ci.repository.AdminRepository;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminServiceImpl implements AdminService{
    @Autowired
    AdminRepository adminRepository;

    /**
     *
     * @param adminId
     * @return
     */
    @Override
    public Admin fetchAdminById(Long adminId) {
        if(!adminRepository.findById(adminId).isPresent()) {
            return null;
        }
        return adminRepository.findById(adminId).get();
    }

    /**
     *
     * @param adminId
     * @return
     */
    @Override
    public List<Locality> fetchAllLocality(Long adminId) {
        Optional<Admin> admin = adminRepository.findById(adminId);
        if(admin.isPresent()) {
            return null;
        }

        return admin.get().getLocalityList();
    }

    /**
     *
     * @param ranksId
     * @return
     */
    @Override
    public List<Ranks> fetchAllRank(Long ranksId) {
        // TODO : fetchAllRank function
        return null;
    }

    /**
     *
     * @param pointOfDropId
     * @return
     */
    @Override
    public List<PointOfDrop> fetchAllDropOfPoint(Long pointOfDropId) {
        // TODO : fetchAllDropOfPoint function
        return null;
    }

    @Override
    public String saveAdmin(Admin admin) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("status", HttpStatus.OK.value());
        jsonObject.put("message", "success");
        adminRepository.save(admin);
        return jsonObject.toString();
    }
}
