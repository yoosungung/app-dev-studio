package kr.ac.jj.dashboard.application.ipsy.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.ac.jj.dashboard.application.ipsy.service.IpsyServiceImpl;

/**
 * 입학 분석 데이터 RestController (2/2)
 *
 */
@RestController
@RequestMapping(path = "/ipsy/Ipsy2", consumes = { MediaType.APPLICATION_JSON_VALUE })
public class IpsyRestController2 {

    private @Autowired IpsyServiceImpl ipsyService;

    /**
     * 조회 - 입학 분석 데이터 (2/2)
     *
     * @param search
     * @return
     */
    @PostMapping(path = "/readList")
    public List<Map<String, Object>> readList(@RequestBody Map<String, Object> search) {
        return ipsyService.readList2(search);
    }

    /**
     * 조회 - 입학 분석 차트 데이터 (2/2)
     *
     * @param search
     * @return
     */
    @PostMapping(path = "/readChartList")
    public List<Map<String, Object>> readChartList2(@RequestBody Map<String, Object> search) {
        return ipsyService.readChartList2(search);
    }

}
