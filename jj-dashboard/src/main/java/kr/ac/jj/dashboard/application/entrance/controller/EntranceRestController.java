package kr.ac.jj.dashboard.application.entrance.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.ac.jj.dashboard.application.entrance.service.EntranceServiceImpl;

/**
 * 입학 데이터 RestController
 *
 */
@RestController
@RequestMapping(path = "/entrance/Entrance", consumes = { MediaType.APPLICATION_JSON_VALUE })
public class EntranceRestController {

    private @Autowired EntranceServiceImpl entranceService;

    /**
     * 조회 - 입학 데이터 Sankey 차트
     * 
     * @param search
     * @return
     */
    @PostMapping(path = "/readList")
    public List<Map<String, Object>> readList(@RequestBody Map<String, Object> search) {
        return entranceService.readList(search);
    }

}
