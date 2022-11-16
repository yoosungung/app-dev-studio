package kr.ac.jj.survey.domain.main.mapper.survey.qestnr.cmmn;

import org.apache.ibatis.annotations.Param;

import kr.ac.jj.survey.domain.main.model.survey.qestnr.cmmn.TbSurveyQestnrCmmn;

/**
 * 설문_설문지_공통문항 Entity Mapper
 */
abstract interface TbSurveyQestnrCmmnEntityMapper {

    /**
     * 조회
     *
     * @param surveyQestnrId
     * @return
     */
    public TbSurveyQestnrCmmn select(@Param("surveyQestnrId") String surveyQestnrId);

    /**
     * 생성
     *
     * @param tbSurveyQestnrCmmn
     * @return
     */
    public int insert(@Param("tbSurveyQestnrCmmn") TbSurveyQestnrCmmn tbSurveyQestnrCmmn);

    /**
     * 수정
     *
     * @param tbSurveyQestnrCmmn
     * @return
     */
    public int update(@Param("tbSurveyQestnrCmmn") TbSurveyQestnrCmmn tbSurveyQestnrCmmn);

    /**
     * 삭제
     *
     * @param surveyQestnrId
     * @return
     */
    public int delete(@Param("surveyQestnrId") String surveyQestnrId);

}
