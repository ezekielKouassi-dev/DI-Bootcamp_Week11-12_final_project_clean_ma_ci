package di.learning.clean.ma.ci.service.rank_;

import di.learning.clean.ma.ci.entity.Admin;
import di.learning.clean.ma.ci.entity.Ranks;
import di.learning.clean.ma.ci.model.RankPayload;
import di.learning.clean.ma.ci.repository.AdminRepository;
import di.learning.clean.ma.ci.repository.RankRepository;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class RankServiceImpl implements RankService{
    @Autowired
    private RankRepository rankRepository;

    @Autowired
    private AdminRepository adminRepository;
    /**
     * @return
     */
    @Override
    public String fetchAllRanks() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("status", HttpStatus.OK.value());
        jsonObject.put("message", "success");
        jsonObject.put("data", rankRepository.findAll());
        return jsonObject.toString();
    }

    /**
     * @param rankId
     * @return
     */
    @Override
    public String fetchRankById(Long rankId) {
        Optional<Ranks> rank = rankRepository.findById(rankId);

        if(!rank.isPresent()) {
            return null;
        }

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("status", HttpStatus.OK.value());
        jsonObject.put("message", "user found");
        jsonObject.put("data", rank.get());
        return jsonObject.toString();
    }

    /**
     * @param rank
     * @return
     */
    @Override
    public String saveRank(RankPayload rank) {
        Optional<Admin> admin = adminRepository.findById(rank.getAdminId());

        if(!admin.isPresent()) {
            return null;
        }

        Ranks ranks = new Ranks();
        ranks.setTitle(rank.getName());
        ranks.setDescription(rank.getDescription());
        ranks.setAdmin(admin.get());

        rankRepository.save(ranks);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("status", HttpStatus.OK.value());
        jsonObject.put("message", "rank has been successfully register");
        return jsonObject.toString();
    }

    /**
     * @param rankId
     * @param rankPayload
     * @return
     */
    @Override
    public String updateRank(Long rankId, RankPayload rankPayload) {
        Optional<Ranks> ranks = rankRepository.findById(rankId);
        Optional<Admin> admin = adminRepository.findById(rankPayload.getAdminId());

        if(!ranks.isPresent() && !admin.isPresent()) {
            return null;
        }

        if(Objects.nonNull(rankPayload.getName()) && !"".equalsIgnoreCase(rankPayload.getName())) {
            ranks.get().setTitle(rankPayload.getName());
        }

        if(Objects.nonNull(rankPayload.getDescription()) && !"".equalsIgnoreCase(rankPayload.getDescription())) {
            ranks.get().setDescription(rankPayload.getDescription());
        }

        ranks.get().setAdmin(admin.get());

        rankRepository.save(ranks.get());

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("status", HttpStatus.OK.value());
        jsonObject.put("message", "update of the rank has been a success");

        return jsonObject.toString();
    }

    /**
     * @param rankId
     * @return
     */
    @Override
    public String deleteRank(Long rankId) {
        Optional<Ranks> ranks = rankRepository.findById(rankId);

        if(!ranks.isPresent()) {
            return null;
        }

        ranks.get().setStatus("deleted");

        rankRepository.save(ranks.get());

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("status", HttpStatus.OK.value());
        jsonObject.put("message", "rank has been successfully removed");

        return jsonObject.toString();
    }
}
