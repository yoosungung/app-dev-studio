package kr.ac.jj.survey.domain.main.mapper.jd.survey.question.columnlabel;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import kr.ac.jj.survey.domain.main.model.jd.survey.question.columnlabel.JdSurveyQuestionColumnLabel;

/**
 * Entity Mapper
 */
abstract interface JdSurveyQuestionColumnLabelEntityMapper {

    /**
     * 목록 조회 - QUESTION_ID(으)로
     *
     * @param questionId
     * @return
     */
    public List<JdSurveyQuestionColumnLabel> selectListByQuestionId(@Param("questionId") Long questionId);

    /**
     * 조회
     *
     * @param id
     * @return
     */
    public JdSurveyQuestionColumnLabel select(@Param("id") Long id);

    /**
     * 생성
     *
     * @param jdSurveyQuestionColumnLabel
     * @return
     */
    public int insert(@Param("jdSurveyQuestionColumnLabel") JdSurveyQuestionColumnLabel jdSurveyQuestionColumnLabel);

    /**
     * 수정
     *
     * @param jdSurveyQuestionColumnLabel
     * @return
     */
    public int update(@Param("jdSurveyQuestionColumnLabel") JdSurveyQuestionColumnLabel jdSurveyQuestionColumnLabel);

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
