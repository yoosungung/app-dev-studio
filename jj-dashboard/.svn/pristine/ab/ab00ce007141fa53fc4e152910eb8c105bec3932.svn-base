package kr.ac.jj.dashboard.application.ipsy.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.ac.jj.dashboard.application.ipsy.mapper.IpsyMapper;

/**
 * 입학 분석 데이터 Service
 *
 */
@Service
public class IpsyServiceImpl {

    private @Autowired IpsyMapper ipsyMapper;

    /**
     * 조회 - 입학 분석 데이터 (1/2)
     *
     * @param search
     * @return
     */
    public List<Map<String, Object>> readList(Map<String, Object> search) {
        return ipsyMapper.readList(search);
    }

    /**
     * 조회 - 입학 분석 차트 데이터 (1/2)
     *
     * @return
     */
    public List<Map<String, Object>> readChartList() {
        return ipsyMapper.readChartList();
    }

    /**
     * 조회 - 입학 분석 데이터 (2/2)
     *
     * @param search
     * @return
     */
    public List<Map<String, Object>> readList2(Map<String, Object> search) {
        return ipsyMapper.readList2(search);
    }

    /**
     * 조회 - 입학 분석 차트 데이터 (2/2)
     *
     * @return
     */
    public List<Map<String, Object>> readChartList2(Map<String, Object> search) {
        return ipsyMapper.readChartList2(search);
    }

}
