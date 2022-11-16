package kr.ac.jj.dashboard.application.recommendjob.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.ac.jj.dashboard.application.recommendjob.service.RecommendJobServiceImpl;
import kr.ac.jj.shared.infrastructure.grid.model.GridRequest;

/**
 * 직업추천 RestController
 *
 */
@RestController
@RequestMapping(path = "/recommendjob/RecommendJob", consumes = { MediaType.APPLICATION_JSON_VALUE })
public class RecommendJobRestController {

    private @Autowired RecommendJobServiceImpl recommendJobService;

    /**
     * 조회 - 직업추천 리스트
     * 
     * @param search
     * @return
     */
    @PostMapping(path = "/readList")
    public List<Map<String, Object>> readList(@RequestBody GridRequest gridRequest) {
        return recommendJobService.readList(gridRequest);
    }

}
