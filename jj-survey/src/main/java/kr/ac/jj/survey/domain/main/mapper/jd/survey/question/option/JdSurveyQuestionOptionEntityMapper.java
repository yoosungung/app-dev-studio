package kr.ac.jj.survey.domain.main.mapper.jd.survey.question.option;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import kr.ac.jj.survey.domain.main.model.jd.survey.question.option.JdSurveyQuestionOption;

/**
 * Entity Mapper
 */
abstract interface JdSurveyQuestionOptionEntityMapper {

    /**
     * 목록 조회 - QUESTION_ID(으)로
     *
     * @param questionId
     * @return
     */
    public List<JdSurveyQuestionOption> selectListByQuestionId(@Param("questionId") Long questionId);

    /**
     * 조회
     *
     * @param id
     * @return
     */
    public JdSurveyQuestionOption select(@Param("id") Long id);

    /**
     * 생성
     *
     * @param jdSurveyQuestionOption
     * @return
     */
    public int insert(@Param("jdSurveyQuestionOption") JdSurveyQuestionOption jdSurveyQuestionOption);

    /**
     * 수정
     *
     * @param jdSurveyQuestionOption
     * @return
     */
    public int update(@Param("jdSurveyQuestionOption") JdSurveyQuestionOption jdSurveyQuestionOption);

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
