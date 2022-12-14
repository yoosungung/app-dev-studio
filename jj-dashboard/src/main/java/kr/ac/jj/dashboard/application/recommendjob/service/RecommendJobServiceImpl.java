package kr.ac.jj.dashboard.application.recommendjob.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.ac.jj.dashboard.application.recommendjob.mapper.RecommendJobMapper;
import kr.ac.jj.shared.infrastructure.grid.handler.GridDataResultHandler;
import kr.ac.jj.shared.infrastructure.grid.model.GridRequest;

/**
 * 직업추천 Service
 *
 */
@Service
public class RecommendJobServiceImpl {

    private @Autowired RecommendJobMapper recommendJobMapper;

    /**
     * 조회 - 직업추천 리스트
     * 
     * @param search
     * @return
     */
    public List<Map<String, Object>> readList(GridRequest gridRequest) {
        GridDataResultHandler resultHandler = new GridDataResultHandler(gridRequest);
        recommendJobMapper.readList(resultHandler, gridRequest.getPaging(), gridRequest.getSearch());
        return resultHandler.getResultList();
    }

}
