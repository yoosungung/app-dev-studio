package kr.ac.jj.survey.domain.main.service.jd.survey.question;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jd.survey.domain.settings.Question;
import com.jd.survey.domain.settings.QuestionType;

import kr.ac.jj.survey.domain.main.mapper.jd.survey.question.columnlabel.JdSurveyQuestionColumnLabelMapper;
import kr.ac.jj.survey.domain.main.mapper.jd.survey.question.option.JdSurveyQuestionOptionMapper;
import kr.ac.jj.survey.domain.main.mapper.jd.survey.question.rowlabel.JdSurveyQuestionRowLabelMapper;
import kr.ac.jj.survey.domain.main.model.jd.survey.question.JdSurveyQuestion;

/**
 * Service
 */
@Service
public class JdSurveyQuestionServiceImpl {

    private @Autowired JdSurveyQuestionOptionMapper jdSurveyQuestionOptionMapper;
    private @Autowired JdSurveyQuestionColumnLabelMapper jdSurveyQuestionColumnLabelMapper;
    private @Autowired JdSurveyQuestionRowLabelMapper jdSurveyQuestionRowLabelMapper;

    /**
     * 하위항목 복사 목록 생성
     *
     * @param sourceJdSurveyQuestionId
     * @param targetJdSurveyQuestion
     */
    public void createSubItemCopyList(Long sourceJdSurveyQuestionId, JdSurveyQuestion targetJdSurveyQuestion) {
        QuestionType questionType = QuestionType.valueOf(targetJdSurveyQuestion.getType());

        Question targetQuestion = new Question();
        targetQuestion.setId(targetJdSurveyQuestion.getId());
        targetQuestion.setType(questionType);

        this.createSubItemCopyList(sourceJdSurveyQuestionId, targetQuestion);
    }

    /**
     * 하위항목 복사 목록 생성
     *
     * @param sourceQuestionId
     * @param targetQuestion
     */
    public void createSubItemCopyList(Long sourceQuestionId, Question targetQuestion) {
        if (targetQuestion.getType().getRequiresOptions()) {
            jdSurveyQuestionOptionMapper.insertCopyList(sourceQuestionId, targetQuestion.getId());
        }

        if (targetQuestion.getType().getIsMatrix()) {
            jdSurveyQuestionColumnLabelMapper.insertCopyList(sourceQuestionId, targetQuestion.getId());
            jdSurveyQuestionRowLabelMapper.insertCopyList(sourceQuestionId, targetQuestion.getId());
        }
    }

}
