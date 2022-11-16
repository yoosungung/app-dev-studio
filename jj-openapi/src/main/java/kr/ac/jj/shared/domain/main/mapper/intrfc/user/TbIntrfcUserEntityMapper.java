package kr.ac.jj.shared.domain.main.mapper.intrfc.user;

import org.apache.ibatis.annotations.Param;

import kr.ac.jj.shared.domain.main.model.intrfc.user.TbIntrfcUser;

/**
 * 인터페이스 - 사용자 Entity Mapper
 */
abstract interface TbIntrfcUserEntityMapper {

    /**
     * 조회
     *
     * @param userId
     * @return
     */
    public TbIntrfcUser select(@Param("userId") String userId);

    /**
     * 생성
     *
     * @param tbIntrfcUser
     * @return
     */
    public int insert(@Param("tbIntrfcUser") TbIntrfcUser tbIntrfcUser);

    /**
     * 수정
     *
     * @param tbIntrfcUser
     * @return
     */
    public int update(@Param("tbIntrfcUser") TbIntrfcUser tbIntrfcUser);

    /**
     * 삭제
     *
     * @param userId
     * @return
     */
    public int delete(@Param("userId") String userId);

}
