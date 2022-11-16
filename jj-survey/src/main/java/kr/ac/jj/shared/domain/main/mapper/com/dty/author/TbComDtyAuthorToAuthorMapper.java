package kr.ac.jj.shared.domain.main.mapper.com.dty.author;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import kr.ac.jj.shared.config.datasources.SharedDataSourceMainConfig.SharedMainSqlMapper;
import kr.ac.jj.shared.domain.main.model.com.dty.author.TbComDtyAuthorToAuthor;

/**
 * 공통 - 직무별 권한 TO 시스템 - 권한 Mapper
 */
@SharedMainSqlMapper
public interface TbComDtyAuthorToAuthorMapper {

    /**
     * 목록 조회 - DTY_ID(으)로
     *
     * @param dtyId
     * @return
     */
    public List<TbComDtyAuthorToAuthor> selectListByDtyId(@Param("dtyId") String dtyId);

}
