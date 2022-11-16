package kr.ac.jj.survey.domain.main.mapper.survey.group.person;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import kr.ac.jj.survey.domain.main.model.survey.group.person.TbSurveyGroupPerson;

/**
 * 설문_그룹_사람 Entity Mapper
 */
abstract interface TbSurveyGroupPersonEntityMapper {

    /**
     * 목록 조회 - SURVEY_GROUP_ID(으)로
     *
     * @param surveyGroupId
     * @return
     */
    public List<TbSurveyGroupPerson> selectListBySurveyGroupId(@Param("surveyGroupId") String surveyGroupId);

    /**
     * 목록 조회 - PERSON_ID(으)로
     *
     * @param personId
     * @return
     */
    public List<TbSurveyGroupPerson> selectListByPersonId(@Param("personId") String personId);

    /**
     * 조회
     *
     * @param surveyGroupId
     * @param personId
     * @return
     */
    public TbSurveyGroupPerson select(@Param("surveyGroupId") String surveyGroupId, @Param("personId") String personId);

    /**
     * 생성
     *
     * @param tbSurveyGroupPerson
     * @return
     */
    public int insert(@Param("tbSurveyGroupPerson") TbSurveyGroupPerson tbSurveyGroupPerson);

    /**
     * 수정
     *
     * @param tbSurveyGroupPerson
     * @return
     */
    public int update(@Param("tbSurveyGroupPerson") TbSurveyGroupPerson tbSurveyGroupPerson);

    /**
     * 삭제
     *
     * @param surveyGroupId
     * @param personId
     * @return
     */
    public int delete(@Param("surveyGroupId") String surveyGroupId, @Param("personId") String personId);

    /**
     * 목록 삭제 - SURVEY_GROUP_ID(으)로
     *
     * @param surveyGroupId
     * @return
     */
    public int deleteListBySurveyGroupId(@Param("surveyGroupId") String surveyGroupId);

    /**
     * 목록 삭제 - PERSON_ID(으)로
     *
     * @param personId
     * @return
     */
    public int deleteListByPersonId(@Param("personId") String personId);

}
