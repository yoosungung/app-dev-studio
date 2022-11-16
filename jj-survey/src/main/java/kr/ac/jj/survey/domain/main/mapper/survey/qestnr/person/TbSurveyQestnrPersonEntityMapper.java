package kr.ac.jj.survey.domain.main.mapper.survey.qestnr.person;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import kr.ac.jj.survey.domain.main.model.survey.qestnr.person.TbSurveyQestnrPerson;

/**
 * 설문_설문지 - 사람(대상자) Entity Mapper
 */
abstract interface TbSurveyQestnrPersonEntityMapper {

    /**
     * 목록 조회 - SURVEY_QESTNR_ID(으)로
     *
     * @param surveyQestnrId
     * @return
     */
    public List<TbSurveyQestnrPerson> selectListBySurveyQestnrId(@Param("surveyQestnrId") String surveyQestnrId);

    /**
     * 목록 조회 - PERSON_ID(으)로
     *
     * @param personId
     * @return
     */
    public List<TbSurveyQestnrPerson> selectListByPersonId(@Param("personId") String personId);

    /**
     * 조회
     *
     * @param surveyPersonId
     * @return
     */
    public TbSurveyQestnrPerson select(@Param("surveyPersonId") String surveyPersonId);

    /**
     * 생성
     *
     * @param tbSurveyQestnrPerson
     * @return
     */
    public int insert(@Param("tbSurveyQestnrPerson") TbSurveyQestnrPerson tbSurveyQestnrPerson);

    /**
     * 수정
     *
     * @param tbSurveyQestnrPerson
     * @return
     */
    public int update(@Param("tbSurveyQestnrPerson") TbSurveyQestnrPerson tbSurveyQestnrPerson);

    /**
     * 삭제
     *
     * @param surveyPersonId
     * @return
     */
    public int delete(@Param("surveyPersonId") String surveyPersonId);

    /**
     * 목록 삭제 - SURVEY_QESTNR_ID(으)로
     *
     * @param surveyQestnrId
     * @return
     */
    public int deleteListBySurveyQestnrId(@Param("surveyQestnrId") String surveyQestnrId);

    /**
     * 목록 삭제 - PERSON_ID(으)로
     *
     * @param personId
     * @return
     */
    public int deleteListByPersonId(@Param("personId") String personId);

}
