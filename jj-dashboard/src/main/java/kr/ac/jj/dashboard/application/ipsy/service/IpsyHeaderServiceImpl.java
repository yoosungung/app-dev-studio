package kr.ac.jj.dashboard.application.ipsy.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.ac.jj.dashboard.application.ipsy.mapper.IpsyHeaderMapper;

/**
 * 입학 분석 데이터 Header Service
 *
 */
@Service
public class IpsyHeaderServiceImpl {

    private @Autowired IpsyHeaderMapper ipsyHeaderMapper;

    /**
     * 조회 - 입학 분석 데이터 Header
     *
     * @param search
     * @return
     */
    public Map<String, Object> readData() {
        return ipsyHeaderMapper.readData();
    }

}
