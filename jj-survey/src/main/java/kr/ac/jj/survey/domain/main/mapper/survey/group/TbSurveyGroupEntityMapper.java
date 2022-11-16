package kr.ac.jj.survey.domain.main.mapper.survey.group;

import org.apache.ibatis.annotations.Param;

import kr.ac.jj.survey.domain.main.model.survey.group.TbSurveyGroup;

/**
 * 설문_그룹 Entity Mapper
 */
abstract interface TbSurveyGroupEntityMapper {

    /**
     * 조회
     *
     * @param surveyGroupId
     * @return
     */
    public TbSurveyGroup select(@Param("surveyGroupId") String surveyGroupId);

    /**
     * 생성
     *
     * @param tbSurveyGroup
     * @return
     */
    public int insert(@Param("tbSurveyGroup") TbSurveyGroup tbSurveyGroup);

    /**
     * 수정
     *
     * @param tbSurveyGroup
     * @return
     */
    public int update(@Param("tbSurveyGroup") TbSurveyGroup tbSurveyGroup);

    /**
     * 삭제
     *
     * @param surveyGroupId
     * @return
     */
    public int delete(@Param("surveyGroupId") String surveyGroupId);

}
