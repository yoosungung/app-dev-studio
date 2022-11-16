package kr.ac.jj.survey.domain.main.service.jd.survey.definition;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.ac.jj.shared.infrastructure.framework.core.foundation.exception.BizException;
import kr.ac.jj.survey.domain.main.mapper.jd.survey.definition.JdSurveyDefinitionMapper;
import kr.ac.jj.survey.domain.main.mapper.jd.survey.definition.page.JdSurveyDefinitionPageMapper;
import kr.ac.jj.survey.domain.main.mapper.jd.survey.question.JdSurveyQuestionMapper;
import kr.ac.jj.survey.domain.main.mapper.jd.survey.question.columnlabel.JdSurveyQuestionColumnLabelMapper;
import kr.ac.jj.survey.domain.main.mapper.jd.survey.question.option.JdSurveyQuestionOptionMapper;
import kr.ac.jj.survey.domain.main.mapper.jd.survey.question.rowlabel.JdSurveyQuestionRowLabelMapper;
import kr.ac.jj.survey.domain.main.model.jd.survey.definition.JdSurveyDefinition;
import kr.ac.jj.survey.domain.main.model.jd.survey.definition.page.JdSurveyDefinitionPage;
import kr.ac.jj.survey.domain.main.model.jd.survey.question.JdSurveyQuestion;
import kr.ac.jj.survey.domain.main.service.jd.survey.question.JdSurveyQuestionServiceImpl;

/**
 * Service
 */
@Service
public class JdSurveyDefinitionServiceImpl {

    private @Autowired JdSurveyDefinitionMapper jdSurveyDefinitionMapper;
    private @Autowired JdSurveyDefinitionPageMapper jdSurveyDefinitionPageMapper;
    private @Autowired JdSurveyQuestionMapper jdSurveyQuestionMapper;
    private @Autowired JdSurveyQuestionOptionMapper jdSurveyQuestionOptionMapper;
    private @Autowired JdSurveyQuestionColumnLabelMapper jdSurveyQuestionColumnLabelMapper;
    private @Autowired JdSurveyQuestionRowLabelMapper jdSurveyQuestionRowLabelMapper;
    private @Autowired JdSurveyQuestionServiceImpl jdSurveyQuestionService;

    /**
     * 삭제
     *
     * @param surveyDefinitionId
     */
    public void delete(Long surveyDefinitionId) {
        jdSurveyQuestionOptionMapper.deleteListBySurveyDefinitionId(surveyDefinitionId);
        jdSurveyQuestionColumnLabelMapper.deleteListBySurveyDefinitionId(surveyDefinitionId);
        jdSurveyQuestionRowLabelMapper.deleteListBySurveyDefinitionId(surveyDefinitionId);
        jdSurveyQuestionMapper.deleteListBySurveyDefinitionId(surveyDefinitionId);
        jdSurveyDefinitionPageMapper.deleteListBySurveyDefinitionId(surveyDefinitionId);

        if (jdSurveyDefinitionMapper.deleteIfStatusI(surveyDefinitionId) == 0) {
            throw new BizException("작성중인 설문지만 삭제할 수 있습니다.");
        }
    }

    /**
     * 복사 생성
     *
     * @param sourceSurveyDefinitionId
     * @param targetSurveyDefinition
     */
    public void createCopy(Long sourceSurveyDefinitionId, JdSurveyDefinition targetSurveyDefinition) {
        if (sourceSurveyDefinitionId == null) {
            return;
        }

        List<JdSurveyDefinitionPage> jdSurveyDefinitionPageList = jdSurveyDefinitionPageMapper
                .selectListBySurveyDefinitionId(sourceSurveyDefinitionId);

        for (JdSurveyDefinitionPage jdSurveyDefinitionPage : jdSurveyDefinitionPageList) {
            Long sourceSurveyDefinitionPageId = jdSurveyDefinitionPage.getId();

            jdSurveyDefinitionPage.setId(null);
            jdSurveyDefinitionPage.setSurveyDefinitionId(targetSurveyDefinition.getId());
            jdSurveyDefinitionPage.setVersion(0);
            jdSurveyDefinitionPageMapper.insert(jdSurveyDefinitionPage);

            List<JdSurveyQuestion> jdSurveyQuestionList = jdSurveyQuestionMapper
                    .selectListBySurveyDefinitionPageId(sourceSurveyDefinitionPageId);

            for (JdSurveyQuestion jdSurveyQuestion : jdSurveyQuestionList) {
                Long sourceQuestionId = jdSurveyQuestion.getId();

                jdSurveyQuestion.setId(null);
                jdSurveyQuestion.setSurveyDefinitionPageId(jdSurveyDefinitionPage.getId());
                jdSurveyQuestion.setVersion(0);
                jdSurveyQuestionMapper.insert(jdSurveyQuestion);

                jdSurveyQuestionService.createSubItemCopyList(sourceQuestionId, jdSurveyQuestion);
            }
        }
    }

}
