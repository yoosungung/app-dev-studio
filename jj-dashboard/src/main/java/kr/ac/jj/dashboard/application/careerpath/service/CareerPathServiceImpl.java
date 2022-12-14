package kr.ac.jj.dashboard.application.careerpath.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.ac.jj.dashboard.application.careerpath.mapper.CareerPathMapper;
import kr.ac.jj.shared.infrastructure.framework.web.security.util.SecurityContextUtil;
import kr.ac.jj.shared.infrastructure.grid.handler.GridDataResultHandler;
import kr.ac.jj.shared.infrastructure.grid.model.GridRequest;

/**
 * 선도학생 Service
 */
@Service
public class CareerPathServiceImpl {

    private @Autowired CareerPathMapper careerPathMapper;

    /**
     * 목록 조회
     *
     * @param gridRequest
     * @return
     */
    public List<Map<String, Object>> readList(GridRequest gridRequest) {
        GridDataResultHandler resultHandler = new GridDataResultHandler(gridRequest);
        careerPathMapper.readList(resultHandler, gridRequest.getPaging(), gridRequest.getSearch());

        return resultHandler.getResultList();
    }

    /**
     * 선도학생 조회
     *
     * @param hakbun
     * @return
     */
    public Map<String, Object> readStudent(String hakbun) {
        Map<String, Object> student = careerPathMapper.readStudent(hakbun);
        student.put("isEditable", SecurityContextUtil.isUserReachableAuthority("ROLE_ADMIN"));
        return student;
    }

    /**
     * 수강데이터 조회
     *
     * @param param
     * @return
     */
    public List<Map<String, Object>> readSugangData(Map<String, Object> param) {
        return careerPathMapper.readSugangData(param);
    }

    /**
     * 선도학생 추가
     *
     * @param student
     * @return
     */
    public void create(Map<String, Object> student) {
        careerPathMapper.create(student);
    }

    /**
     * 선도학생 수정
     *
     * @param student
     * @return
     */
    public void update(Map<String, Object> student) {
        careerPathMapper.update(student);
    }

    /**
     * 선도학생 삭제
     *
     * @param hakbun
     * @return
     */
    public void delete(String hakbun) {
        careerPathMapper.delete(hakbun);
    }

}
