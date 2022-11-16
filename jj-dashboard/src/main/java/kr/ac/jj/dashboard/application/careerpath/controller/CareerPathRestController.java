package kr.ac.jj.dashboard.application.careerpath.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.ac.jj.dashboard.application.careerpath.service.CareerPathServiceImpl;
import kr.ac.jj.shared.infrastructure.grid.model.GridRequest;

/**
 * 선도학생 RestController
 */
@RestController
@RequestMapping(path = "/careerpath/CareerPath", consumes = { MediaType.APPLICATION_JSON_VALUE })
public class CareerPathRestController {

    private @Autowired CareerPathServiceImpl careerPathService;

    /**
     * 선도학생 목록조회
     *
     * @param gridRequest
     */
    @PostMapping(path = "/readList")
    public void readList(@RequestBody GridRequest gridRequest) {
        careerPathService.readList(gridRequest);
    }

    /**
     * 선도학생 조회
     *
     * @param hakbun
     * @return
     */
    @GetMapping(path = "/readStudent/{hakbun}")
    public Map<String, Object> readStudent(@PathVariable String hakbun) {
        return careerPathService.readStudent(hakbun);
    }

    /**
     * 수강데이터 조회
     *
     * @param param
     * @return
     */
    @PostMapping(path = "/readSugangData")
    public List<Map<String, Object>> readSugangData(@RequestBody Map<String, Object> param) {
        return careerPathService.readSugangData(param);
    }

    /**
     * 선도학생 추가
     *
     * @param student
     */
    @PostMapping(path = "/create")
    public void post(@RequestBody Map<String, Object> student) {
        careerPathService.create(student);
    }

    /**
     * 선도학생 수정
     *
     * @param student
     */
    @PutMapping(path = "/{hakbun}")
    public void put(@PathVariable String hakbun, @RequestBody Map<String, Object> student) {
        careerPathService.update(student);
    }

    /**
     * 선도학생 삭제
     *
     * @param hakbun
     * @param model
     */
    @DeleteMapping(path = "/{hakbun}")
    public void delete(@PathVariable String hakbun) {
        careerPathService.delete(hakbun);
    }

}
