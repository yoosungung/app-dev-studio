package kr.ac.jj.shared.domain.main.mapper.sys.user.author;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import kr.ac.jj.shared.config.datasources.SharedDataSourceMainConfig.SharedMainSqlMapper;
import kr.ac.jj.shared.domain.main.model.sys.resrce.author.TbSysResrceAuthorToAuthor;

/**
 * 시스템 - 사용자별 권한 TO 시스템 - 권한 Mapper
 */
@SharedMainSqlMapper
public interface TbSysUserAuthorToAuthorMapper {

    /**
     * 목록 조회 - USER_ID(으)로
     *
     * @param userId
     * @return
     */
    public List<TbSysResrceAuthorToAuthor> selectListByUserId(@Param("userId") String userId);

}
