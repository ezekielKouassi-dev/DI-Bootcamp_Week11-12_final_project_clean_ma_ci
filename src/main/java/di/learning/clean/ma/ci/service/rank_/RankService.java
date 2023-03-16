package di.learning.clean.ma.ci.service.rank_;

import di.learning.clean.ma.ci.model.RankPayload;

public interface RankService {
    public String fetchAllRanks();

    public String fetchRankById(Long rankId);

    public String saveRank(RankPayload rank);

    public String updateRank(Long rankId, RankPayload rankPayload);

    public String deleteRank(Long rankId);
}
