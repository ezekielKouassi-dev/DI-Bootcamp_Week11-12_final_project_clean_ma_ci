package di.learning.clean.ma.ci.service.locality_;

import di.learning.clean.ma.ci.entity.Locality;
import di.learning.clean.ma.ci.repository.AdminRepository;
import di.learning.clean.ma.ci.repository.LocalityRepository;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocalityServiceImpl implements LocalityService{
    @Autowired
    private LocalityRepository localityRepository;

    @Autowired
    private AdminRepository adminRepository;
    @Override
    public String fetchAllLocality() {
        JSONObject jsonObject;
        JSONArray jsonArray = new JSONArray();
        for(Locality locality: localityRepository.findAll()) {
            jsonObject = new JSONObject();
            jsonObject.put("id", locality.getLocalityId());
            jsonObject.put("name", locality.getName());
            jsonArray.put(jsonObject);
        }

        jsonObject = new JSONObject();
        jsonObject.put("status", HttpStatus.OK.value());
        jsonObject.put("message", "success");
        jsonObject.put("data",jsonArray);
        return jsonObject.toString();
    }

    @Override
    public Locality fetchLocalityById(Long localityId) {
        if(!localityRepository.findById(localityId).isPresent()) {
            return null;
        }
        return localityRepository.findById(localityId).get();
    }

    @Override
    public String saveLocality(Long adminId, Locality locality) {
        if(!adminRepository.findById(adminId).isPresent()) {
            return null;
        }

        locality.setAdmin(adminRepository.findById(adminId).get());
        localityRepository.save(locality);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("status", HttpStatus.OK.value());
        jsonObject.put("message", "Locality successfully save");

        return jsonObject.toString();
    }
}
