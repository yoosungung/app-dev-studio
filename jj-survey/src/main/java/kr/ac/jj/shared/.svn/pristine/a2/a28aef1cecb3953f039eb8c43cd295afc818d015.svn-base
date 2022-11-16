package kr.ac.jj.shared.application.common.codedata;

import java.util.List;
import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.ac.jj.shared.infrastructure.framework.web.support.codedata.CodeDataUtil;
import kr.ac.jj.shared.infrastructure.framework.web.support.codedata.RequestCode;

/**
 * 코드데이터 RestController
 */
@RestController
@RequestMapping(path = "/common/codedata/CodeData", consumes = { MediaType.APPLICATION_JSON_VALUE })
public class CodeDataRestController {

    /**
     * 조회
     *
     * @param requestCodeList
     * @return
     */
    @PostMapping
    public Map<String, Object> read(@RequestBody List<RequestCode> requestCodeList) {
        return CodeDataUtil.getCodeDataMap(requestCodeList);
    }

}
