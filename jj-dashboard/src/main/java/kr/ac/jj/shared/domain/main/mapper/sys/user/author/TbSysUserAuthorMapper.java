package kr.ac.jj.shared.domain.main.mapper.sys.user.author;

import org.apache.ibatis.annotations.Param;

import kr.ac.jj.shared.config.datasources.SharedDataSourceMainConfig.SharedMainSqlMapper;
import kr.ac.jj.shared.domain.main.model.sys.user.author.TbSysUserAuthor;

/**
 * 시스템 - 사용자별 권한 Mapper
 */
@SharedMainSqlMapper
public interface TbSysUserAuthorMapper extends TbSysUserAuthorEntityMapper {

    /**
     * 생성 - 존재하지 않는 건
     *
     * @param tbSysUserAuthor
     * @return
     */
    public int insertNotExists(@Param("tbSysUserAuthor") TbSysUserAuthor tbSysUserAuthor);

}
