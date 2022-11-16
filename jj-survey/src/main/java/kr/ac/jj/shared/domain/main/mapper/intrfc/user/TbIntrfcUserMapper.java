package kr.ac.jj.shared.domain.main.mapper.intrfc.user;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import kr.ac.jj.shared.config.datasources.SharedDataSourceMainConfig.SharedMainSqlMapper;
import kr.ac.jj.shared.domain.main.model.intrfc.user.TbIntrfcUser;

/**
 * 인터페이스 - 사용자 Mapper
 */
@SharedMainSqlMapper
public interface TbIntrfcUserMapper extends TbIntrfcUserEntityMapper {

    /**
     * 목록 생성
     *
     * @param tbIntrfcUserList
     * @return
     */
    public int insertList(@Param("tbIntrfcUserList") List<TbIntrfcUser> tbIntrfcUserList);

    /**
     * 전체 삭제
     *
     * @return
     */
    public int deleteAll();

}
