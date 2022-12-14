package kr.ac.jj.survey.application.qestnrmanage.controller;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.ac.jj.shared.domain.main.model.com.dept.TbComDept;
import kr.ac.jj.shared.infrastructure.grid.model.GridRequest;
import kr.ac.jj.survey.application.qestnrmanage.model.QuestionnaireManageModel;
import kr.ac.jj.survey.application.qestnrmanage.model.QuestionnairePublishModel;
import kr.ac.jj.survey.application.qestnrmanage.service.QuestionnaireManageServiceImpl;
import kr.ac.jj.survey.application.qestnrmanage.service.QuestionnaireManageServiceNoTxImpl;

/**
 * 설문지 관리 RestController
 */
@RestController
@RequestMapping(path = "/qestnrmanage/QuestionnaireManage", consumes = { MediaType.APPLICATION_JSON_VALUE })
public class QuestionnaireManageRestController {

    private @Autowired QuestionnaireManageServiceImpl questionnaireManageService;
    private @Autowired QuestionnaireManageServiceNoTxImpl questionnaireManageServiceNoTx;

    /**
     * 목록 조회
     *
     * @param gridRequest
     */
    @PostMapping(path = "/readList")
    public void readList(@RequestBody GridRequest gridRequest) {
        questionnaireManageService.readList(gridRequest);
    }

    /**
     * 조회 - 생성용
     *
     * @return
     */
    @GetMapping
    public QuestionnaireManageModel get() {
        return questionnaireManageService.read();
    }

    /**
     * 조회
     *
     * @param surveyQestnrId
     * @return
     */
    @GetMapping(path = "/{surveyQestnrId}")
    public QuestionnaireManageModel get(@PathVariable String surveyQestnrId) {
        return questionnaireManageService.read(surveyQestnrId);
    }

    /**
     * 생성
     *
     * @param model
     * @return
     */
    @PostMapping
    public String post(@RequestBody QuestionnaireManageModel model) {
        return questionnaireManageService.create(model);
    }

    /**
     * 수정
     *
     * @param model
     */
    @PutMapping(path = "/{surveyQestnrId}")
    public void put(@RequestBody QuestionnaireManageModel model) {
        questionnaireManageService.update(model);
    }

    /**
     * 삭제
     *
     * @param surveyQestnrId
     */
    @DeleteMapping(path = "/{surveyQestnrId}")
    public void delete(@PathVariable String surveyQestnrId) {
        questionnaireManageService.delete(surveyQestnrId);
    }

    /**
     * 제출(발송)
     *
     * @param surveyQestnrId
     * @param publishModel
     */
    @PutMapping(path = "/{surveyQestnrId}", params = { "publish" })
    public void publish(@PathVariable String surveyQestnrId, @RequestBody QuestionnairePublishModel publishModel) {
        questionnaireManageService.publish(surveyQestnrId, publishModel);
    }

    /**
     * 템플릿 검색 목록 조회
     *
     * @param gridRequest
     */
    @PostMapping(path = "/readTemplateSearchList")
    public void readTemplateSearchList(@RequestBody GridRequest gridRequest) {
        questionnaireManageService.readTemplateSearchList(gridRequest);
    }

    /**
     * 템플릿 조회 - 생성용
     *
     * @return
     */
    @GetMapping(params = "surveyTmplatId")
    public QuestionnaireManageModel getTemplate(@RequestParam String surveyTmplatId) {
        return questionnaireManageService.readTemplate(surveyTmplatId);
    }

    /**
     * 이전 설문지 검색 목록 조회
     *
     * @param gridRequest
     */
    @PostMapping(path = "/readPrevSurveySearchList")
    public void readPrevSurveySearchList(@RequestBody GridRequest gridRequest) {
        questionnaireManageService.readPrevSurveySearchList(gridRequest);
    }

    /**
     * 이전 설문지 조회 - 생성용
     *
     * @return
     */
    @GetMapping(params = "surveyQestnrId")
    public QuestionnaireManageModel getPrevSurvey(@RequestParam String surveyQestnrId) {
        return questionnaireManageService.readPrevSurvey(surveyQestnrId);
    }

    /**
     * 사람(대상자) 목록 조회
     *
     * @param gridRequest
     */
    @PostMapping(path = "/readPersonList")
    public void readPersonList(@RequestBody GridRequest gridRequest) {
        questionnaireManageService.readPersonList(gridRequest);
    }

    /**
     * 사람(대상자) 그룹 검색
     *
     * @param gridRequest
     */
    @PostMapping(path = "/readPersonGroupList")
    public void readPersonGroupList(@RequestBody GridRequest gridRequest) {
        questionnaireManageService.readPersonGroupList(gridRequest);
    }

    /**
     * 그룹별 사람(대상자) 목록 생성
     *
     * @param surveyQestnrId
     * @param surveyGroupId
     * @return
     */
    @PostMapping(path = "/{surveyQestnrId}", params = { "surveyGroupId" })
    public int postGroup(@PathVariable String surveyQestnrId, @RequestBody String surveyGroupId) {
        return questionnaireManageService.createGroupPersonList(surveyQestnrId, surveyGroupId);
    }

    /**
     * 사람(대상자) 목록 검색
     *
     * @param gridRequest
     */
    @PostMapping(path = "/readPersonSearchList")
    public void readPersonSearchList(@RequestBody GridRequest gridRequest) {
        questionnaireManageService.readPersonSearchList(gridRequest);
    }

    /**
     * 사람(대상자) 생성
     *
     * @param surveyQestnrId
     * @param personId
     */
    @PostMapping(path = "/{surveyQestnrId}", params = { "personId" })
    public void post(@PathVariable String surveyQestnrId, @RequestBody String personId) {
        questionnaireManageService.createPerson(surveyQestnrId, personId);
    }

    /**
     * 사람(대상자) 삭제
     *
     * @param surveyQestnrId
     * @param surveyPersonId
     */
    @DeleteMapping(path = "/{surveyQestnrId}", params = { "surveyPersonId" })
    public void deleteSurveyPerson(@RequestParam String surveyPersonId) {
        questionnaireManageService.deletePerson(surveyPersonId);
    }

    /**
     * 조직 트리 조회
     *
     * @param surveyQestnrId
     * @param deptSe
     * @return
     */
    @GetMapping(path = "/readDeptTree")
    public List<Map<String, Object>> readDeptTree(@RequestParam String surveyQestnrId, @RequestParam String deptSe) {
        return questionnaireManageService.readDeptTree(surveyQestnrId, deptSe);
    }

    /**
     * 조직별 사람(대상자) 목록 생성
     *
     * @param surveyQestnrId
     * @param tbComDeptList
     */
    @PostMapping(path = "/{surveyQestnrId}", params = { "deptId" })
    public void postDept(@PathVariable String surveyQestnrId, @RequestBody List<TbComDept> tbComDeptList) {
        questionnaireManageService.createDeptPersonList(surveyQestnrId, tbComDeptList);
    }

    /**
     * 미응답자 메일 일괄 발송
     *
     * @param surveyQestnrId
     * @param publishModel
     */
    @PutMapping(path = "/{surveyQestnrId}", params = { "sendMail" })
    public void sendMail(@PathVariable String surveyQestnrId, @RequestBody QuestionnairePublishModel publishModel) {
        questionnaireManageServiceNoTx.readSurveySendTargetUnAnswered(surveyQestnrId, publishModel);
    }

    /**
     * 대상 메일 발송
     *
     * @param surveyPersonId
     * @param publishModel
     */
    @PutMapping(path = "/{surveyPersonId}", params = { "sendMailTarget" })
    public void sendMailTarget(@PathVariable String surveyPersonId,
            @RequestBody QuestionnairePublishModel publishModel) {
        questionnaireManageService.sendMailTarget(surveyPersonId, publishModel);
    }

}
