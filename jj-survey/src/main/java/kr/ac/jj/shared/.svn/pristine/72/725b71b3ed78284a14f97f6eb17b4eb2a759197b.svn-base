package kr.ac.jj.shared.domain.main.mapper.sys.user.author;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import kr.ac.jj.shared.config.datasources.SharedDataSourceMainConfig.SharedMainSqlMapper;
import kr.ac.jj.shared.domain.main.model.sys.user.author.TbSysUserAuthorToUser;

/**
 * 시스템 - 사용자별 권한 TO 시스템 - 사용자 Mapper
 */
@SharedMainSqlMapper
public interface TbSysUserAuthorToUserMapper {

    /**
     * 목록 조회 - AUTHOR_ID(으)로
     *
     * @param authorId
     * @return
     */
    public List<TbSysUserAuthorToUser> selectListByAuthorId(@Param("authorId") String authorId);

}
