package kr.ac.jj.dashboard.application.spread.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.ac.jj.dashboard.application.spread.mapper.SpreadMapper;
import kr.ac.jj.dashboard.application.spread.model.SpreadModel;

/**
 * 출신 지역 분포 Service
 *
 */
@Service
public class SpreadServiceImpl {

    private @Autowired SpreadMapper spreadMapper;

    /**
     * 조회 - 출신 지역 분포 Clusterer
     *
     * @param search
     * @return
     */
    public SpreadModel readList(Map<String, Object> search) {
        SpreadModel model = new SpreadModel();

        List<String> junhyungNameList = spreadMapper.readJunhyungNameList();

        model.setJunhyungNameList(junhyungNameList);
        model.setResultList(spreadMapper.readList(junhyungNameList, search));

        return model;
    }

}
