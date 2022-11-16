package kr.ac.jj.survey.domain.main.mapper.jd.survey.sec.user;

import org.apache.ibatis.annotations.Param;

import kr.ac.jj.survey.domain.main.model.jd.survey.sec.user.JdSurveySecUser;

/**
 * Entity Mapper
 */
abstract interface JdSurveySecUserEntityMapper {

    /**
     * 조회
     *
     * @param id
     * @return
     */
    public JdSurveySecUser select(@Param("id") Long id);

    /**
     * 생성
     *
     * @param jdSurveySecUser
     * @return
     */
    public int insert(@Param("jdSurveySecUser") JdSurveySecUser jdSurveySecUser);

    /**
     * 수정
     *
     * @param jdSurveySecUser
     * @return
     */
    public int update(@Param("jdSurveySecUser") JdSurveySecUser jdSurveySecUser);

    /**
     * 삭제
     *
     * @param id
     * @return
     */
    public int delete(@Param("id") Long id);

}
