package kr.ac.jj.shared.infrastructure.title.mapper;

import java.util.List;

import kr.ac.jj.shared.config.datasources.SharedDataSourceMainConfig.SharedMainSqlMapper;
import kr.ac.jj.shared.domain.main.model.sys.title.TbSysTitle;

/**
 * 타이틀 Mapper
 */
@SharedMainSqlMapper
public interface TitleMapper {

    /**
     * 목록 조회
     *
     * @return
     */
    public List<TbSysTitle> selectList();

}
