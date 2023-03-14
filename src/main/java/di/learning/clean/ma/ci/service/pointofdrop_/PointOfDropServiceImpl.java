package di.learning.clean.ma.ci.service.pointofdrop_;

import di.learning.clean.ma.ci.entity.PointOfDrop;
import di.learning.clean.ma.ci.model.PointOfDropPayload;
import di.learning.clean.ma.ci.repository.AdminRepository;
import di.learning.clean.ma.ci.repository.LocalityRepository;
import di.learning.clean.ma.ci.repository.PointOfDropRepository;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import java.awt.*;

@Repository
public class PointOfDropServiceImpl implements PointOfDropService{
    @Autowired
    private PointOfDropRepository pointOfDropRepository;

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private LocalityRepository localityRepository;

    /**
     *
     * @return
     */
    @Override
    public String fetchAllPointOfDrop() {
        JSONObject jsonObject;
        JSONArray jsonArray = new JSONArray();
        for (PointOfDrop pointOfDrop : pointOfDropRepository.findAll()) {
            jsonObject = new JSONObject();
            jsonObject.put("id", pointOfDrop.getPointOfDropId());
            jsonObject.put("name", (pointOfDrop.getName() != null) ? pointOfDrop.getName() : "");
            jsonObject.put("locality", (pointOfDrop.getLocality().getName() != null) ? pointOfDrop.getLocality().getName() : "");
            jsonArray.put(jsonObject);
        }
        jsonObject = new JSONObject();
        jsonObject.put("status", HttpStatus.OK.value());
        jsonObject.put("message", "success");
        jsonObject.put("data", jsonArray);
        return jsonObject.toString();
    }

    /**
     *
     * @param pointOfDropPayload
     * @param adminId
     * @return
     */
    @Override
    public String savePointOfDrop(PointOfDropPayload pointOfDropPayload, Long adminId) {
        JSONObject jsonObject;
        if(
                !adminRepository.findById(adminId).isPresent() ||
                !localityRepository.findById(pointOfDropPayload.getLocality()).isPresent()
        ) {
            jsonObject = new JSONObject();
            jsonObject.put("status", HttpStatus.NOT_FOUND);
            jsonObject.put("message", "admin or locality not found, we cannot do something");
            return jsonObject.toString();
        }
        PointOfDrop pointOfDrop = new PointOfDrop();
        pointOfDrop.setName(pointOfDropPayload.getName());
        pointOfDrop.setAdmin(adminRepository.findById(adminId).get());
        pointOfDrop.setLocality(localityRepository.findById(pointOfDropPayload.getLocality()).get());
        pointOfDropRepository.save(pointOfDrop);
        jsonObject = new JSONObject();
        jsonObject.put("status", HttpStatus.OK.value());
        jsonObject.put("message", "success");
        return jsonObject.toString();
    }
}
