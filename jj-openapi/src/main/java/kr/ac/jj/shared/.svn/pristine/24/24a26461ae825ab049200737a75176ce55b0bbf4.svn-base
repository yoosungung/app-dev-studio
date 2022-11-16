package kr.ac.jj.shared.domain.main.mapper.sys.resrce;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import kr.ac.jj.shared.config.datasources.SharedDataSourceMainConfig.SharedMainSqlMapper;
import kr.ac.jj.shared.domain.main.model.sys.resrce.TbSysResrce;

/**
 * 시스템 - 리소스 Mapper
 */
@SharedMainSqlMapper
public interface TbSysResrceMapper extends TbSysResrceEntityMapper {

    /**
     * 조회 - MAX 정렬 순서
     *
     * @return
     */
    public int selectMaxSortOrdr();

    /**
     * 조회 - 정렬 순서 목록
     *
     * @return
     */
    public List<TbSysResrce> selectSortOrdrList();

    /**
     * 수정 - 정렬 순서
     *
     * @param tbSysResrce
     * @return
     */
    public int updateSortOrdr(@Param("tbSysResrce") TbSysResrce tbSysResrce);

}
