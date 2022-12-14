package kr.ac.jj.dashboard.application.dropout.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.ac.jj.dashboard.application.dropout.service.DropOutServiceImpl;

/**
 * 중도탈락 RestController
 */
@RestController
@RequestMapping(path = "/dropout/DropOut", consumes = { MediaType.APPLICATION_JSON_VALUE })
public class DropOutRestController {

    private @Autowired DropOutServiceImpl dropOutService;

    /**
     * 중도탈락 학생 목록조회
     *
     * @param gridRequest
     */
    @PostMapping(path = "/readList")
    public List<Map<String, Object>> readList(@RequestBody Map<String, Object> search) {
        return dropOutService.readList(search);
    }

    /**
     * 중도탈락 학생 조회
     *
     * @param hakbun
     * @return
     */
    @GetMapping(path = "/readStudent/{hakbun}")
    public Map<String, Object> readStudent(@PathVariable String hakbun) {
        return dropOutService.readStudent(hakbun);
    }

}
