package kr.ac.jj.shared.infrastructure.idgen.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.ac.jj.shared.infrastructure.idgen.service.IdGenerationServiceImpl;

/**
 * ID 생성 RestController
 */
@RestController
@RequestMapping(value = "/infrastructure/idgen", consumes = { MediaType.APPLICATION_JSON_VALUE })
public class IdGenerationRestController {

    private @Autowired IdGenerationServiceImpl idGenerationService;

    /**
     * 조회
     *
     * @param type
     * @return
     */
    @GetMapping
    public String get(@RequestParam(required = false) Character type) {
        return idGenerationService.getUid(type == null ? '0' : type);
    }

}
