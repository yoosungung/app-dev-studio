package kr.ac.jj.dashboard.application.dropout.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.ac.jj.dashboard.application.dropout.mapper.DropOutMapper;

/**
 * 중도탈락 Service
 */
@Service
public class DropOutServiceImpl {

    private @Autowired DropOutMapper dropOutMapper;

    /**
     * 목록 조회
     *
     * @param gridRequest
     * @return
     */
    public List<Map<String, Object>> readList(Map<String, Object> search) {

        return dropOutMapper.readList(search);
    }

    /**
     * 중도탈락 학생 조회
     *
     * @param hakbun
     * @return
     */
    public Map<String, Object> readStudent(String hakbun) {

        return dropOutMapper.readStudent(hakbun);
    }

}
