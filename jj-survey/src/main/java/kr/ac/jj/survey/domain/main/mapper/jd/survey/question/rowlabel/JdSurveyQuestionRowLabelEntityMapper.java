package kr.ac.jj.survey.domain.main.mapper.jd.survey.question.rowlabel;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import kr.ac.jj.survey.domain.main.model.jd.survey.question.rowlabel.JdSurveyQuestionRowLabel;

/**
 * Entity Mapper
 */
abstract interface JdSurveyQuestionRowLabelEntityMapper {

    /**
     * 목록 조회 - QUESTION_ID(으)로
     *
     * @param questionId
     * @return
     */
    public List<JdSurveyQuestionRowLabel> selectListByQuestionId(@Param("questionId") Long questionId);

    /**
     * 조회
     *
     * @param id
     * @return
     */
    public JdSurveyQuestionRowLabel select(@Param("id") Long id);

    /**
     * 생성
     *
     * @param jdSurveyQuestionRowLabel
     * @return
     */
    public int insert(@Param("jdSurveyQuestionRowLabel") JdSurveyQuestionRowLabel jdSurveyQuestionRowLabel);

    /**
     * 수정
     *
     * @param jdSurveyQuestionRowLabel
     * @return
     */
    public int update(@Param("jdSurveyQuestionRowLabel") JdSurveyQuestionRowLabel jdSurveyQuestionRowLabel);

    /**
     * 삭제
     *
     * @param id
     * @return
     */
    public int delete(@Param("id") Long id);

    /**
     * 목록 삭제 - QUESTION_ID(으)로
     *
     * @param questionId
     * @return
     */
    public int deleteListByQuestionId(@Param("questionId") Long questionId);

}
