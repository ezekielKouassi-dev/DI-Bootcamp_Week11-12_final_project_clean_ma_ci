package di.learning.clean.ma.ci.service.locality_;

import di.learning.clean.ma.ci.entity.Locality;

import java.util.List;

public interface LocalityService {
    public List<Locality> fetchAllLocality();

    public Locality fetchLocalityById(Long localityId);

    public String saveLocality(Long adminId, Locality locality);
}
