package kr.ac.jj.survey.domain.main.mapper.survey.tmplat;

import org.apache.ibatis.annotations.Param;

import kr.ac.jj.survey.domain.main.model.survey.tmplat.TbSurveyTmplat;

/**
 * 설문_템플릿 Entity Mapper
 */
abstract interface TbSurveyTmplatEntityMapper {

    /**
     * 조회
     *
     * @param surveyTmplatId
     * @return
     */
    public TbSurveyTmplat select(@Param("surveyTmplatId") String surveyTmplatId);

    /**
     * 생성
     *
     * @param tbSurveyTmplat
     * @return
     */
    public int insert(@Param("tbSurveyTmplat") TbSurveyTmplat tbSurveyTmplat);

    /**
     * 수정
     *
     * @param tbSurveyTmplat
     * @return
     */
    public int update(@Param("tbSurveyTmplat") TbSurveyTmplat tbSurveyTmplat);

    /**
     * 삭제
     *
     * @param surveyTmplatId
     * @return
     */
    public int delete(@Param("surveyTmplatId") String surveyTmplatId);

}
