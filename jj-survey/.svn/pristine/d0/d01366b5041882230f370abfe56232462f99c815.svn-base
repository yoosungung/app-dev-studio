package kr.ac.jj.survey.domain.main.mapper.survey.qestnr;

import org.apache.ibatis.annotations.Param;

import kr.ac.jj.survey.domain.main.model.survey.qestnr.TbSurveyQestnr;

/**
 * 설문_설문지 Entity Mapper
 */
abstract interface TbSurveyQestnrEntityMapper {

    /**
     * 조회
     *
     * @param surveyQestnrId
     * @return
     */
    public TbSurveyQestnr select(@Param("surveyQestnrId") String surveyQestnrId);

    /**
     * 생성
     *
     * @param tbSurveyQestnr
     * @return
     */
    public int insert(@Param("tbSurveyQestnr") TbSurveyQestnr tbSurveyQestnr);

    /**
     * 수정
     *
     * @param tbSurveyQestnr
     * @return
     */
    public int update(@Param("tbSurveyQestnr") TbSurveyQestnr tbSurveyQestnr);

    /**
     * 삭제
     *
     * @param surveyQestnrId
     * @return
     */
    public int delete(@Param("surveyQestnrId") String surveyQestnrId);

}
