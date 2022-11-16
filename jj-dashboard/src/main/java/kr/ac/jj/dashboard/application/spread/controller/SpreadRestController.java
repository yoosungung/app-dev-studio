package kr.ac.jj.dashboard.application.spread.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.ac.jj.dashboard.application.spread.model.SpreadModel;
import kr.ac.jj.dashboard.application.spread.service.SpreadServiceImpl;

/**
 * 출신 지역 분포 RestController
 *
 */
@RestController
@RequestMapping(path = "/spread/Spread", consumes = { MediaType.APPLICATION_JSON_VALUE })
public class SpreadRestController {

    private @Autowired SpreadServiceImpl spreadService;

    /**
     * 조회 - 출신 지역 분포 Clusterer
     *
     * @param search
     * @return
     */
    @PostMapping(path = "/readList")
    public SpreadModel readList(@RequestBody Map<String, Object> search) {
        return spreadService.readList(search);
    }

}
