package di.learning.clean.ma.ci.service.locality_;

import di.learning.clean.ma.ci.entity.Locality;
import di.learning.clean.ma.ci.repository.AdminRepository;
import di.learning.clean.ma.ci.repository.LocalityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocalityServiceImpl implements LocalityService{
    @Autowired
    private LocalityRepository localityRepository;

    @Autowired
    private AdminRepository adminRepository;
    @Override
    public List<Locality> fetchAllLocality() {
        return localityRepository.findAll();
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
        return "Locality successfully save";
    }
}
