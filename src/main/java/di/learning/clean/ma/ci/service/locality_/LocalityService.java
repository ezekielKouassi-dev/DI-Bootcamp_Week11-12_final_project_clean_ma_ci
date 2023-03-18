package di.learning.clean.ma.ci.service.locality_;

import di.learning.clean.ma.ci.entity.Locality;
import di.learning.clean.ma.ci.model.LocalityPayload;

import java.util.List;

public interface LocalityService {
    public String fetchAllLocality();

    public Locality fetchLocalityById(Long localityId);

    public String saveLocality(LocalityPayload localityPayload);
}
