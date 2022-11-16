package kr.ac.jj.openapi.application.bannermanage.mapper;

import java.util.List;

import kr.ac.jj.openapi.config.datasources.DataSourceMainConfig.MainSqlMapper;
import kr.ac.jj.openapi.domain.main.model.api.banner.TbApiBanner;

/**
 * 배너 관리 Mapper
 */
@MainSqlMapper
public interface BannerManageMapper {

    /**
     * 전체 목록 조회
     *
     */
    public List<TbApiBanner> readList();

    /**
     * 사용중 배너목록 조회
     *
     */
    public List<String> readUsingList();

}
