package kr.ac.jj.dashboard.application.entrance.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.ac.jj.dashboard.application.entrance.mapper.EntranceMapper;

/**
 * 입학 데이터 Service
 *
 */
@Service
public class EntranceServiceImpl {

    private @Autowired EntranceMapper entranceMapper;

    /**
     * 조회 - 입학 데이터 Sankey 차트
     * 
     * @param search
     * @return
     */
    public List<Map<String, Object>> readList(Map<String, Object> search) {
        return entranceMapper.readList(search);
    }

}
