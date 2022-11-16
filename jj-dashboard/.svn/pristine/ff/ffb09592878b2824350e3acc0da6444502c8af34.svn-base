package kr.ac.jj.dashboard.application.ipsy.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.ac.jj.dashboard.application.ipsy.service.IpsyHeaderServiceImpl;

/**
 * 입학 분석 데이터 Header RestController
 *
 */
@RestController
@RequestMapping(path = "/ipsy/ipsyHeader", consumes = { MediaType.APPLICATION_JSON_VALUE })
public class IpsyHeaderRestController {

    private @Autowired IpsyHeaderServiceImpl ipsyHeaderServiceImpl;

    /**
     * 조회 - 입학 분석 데이터 Header
     *
     * @param search
     * @return
     */
    @PostMapping(path = "/readData")
    public Map<String, Object> readData() {
        return ipsyHeaderServiceImpl.readData();
    }

}
