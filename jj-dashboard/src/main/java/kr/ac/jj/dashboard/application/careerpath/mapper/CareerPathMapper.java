package kr.ac.jj.dashboard.application.careerpath.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import kr.ac.jj.dashboard.config.datasources.DataSourceDwConfig.DwSqlMapper;
import kr.ac.jj.shared.infrastructure.framework.core.persistence.dao.paging.model.PagingInfo;
import kr.ac.jj.shared.infrastructure.framework.core.support.collection.BaseMap;
import kr.ac.jj.shared.infrastructure.grid.handler.GridDataResultHandler;

/**
 * 선도학생 Mapper
 */
@DwSqlMapper
public interface CareerPathMapper {

    /**
     * 선도학생 목록 조회
     *
     * @param resultHandler
     * @param paging
     * @param search
     */
    public void readList(GridDataResultHandler resultHandler, @Param("paging") PagingInfo paging,
            @Param("search") BaseMap search);

    /**
     * 선도학생 조회
     *
     * @param hakbun
     * @return
     */
    public Map<String, Object> readStudent(@Param("hakbun") String hakbun);

    /**
     * 선도학생 교과 조회
     *
     * @param param
     * @return
     */
    public List<Map<String, Object>> readSugangData(@Param("param") Map<String, Object> param);

    /**
     * 선도학생 비교과, Star-T조회
     *
     * @param param
     * @return
     */
    public List<Map<String, Object>> readOtherData(@Param("param") Map<String, Object> param);

    /**
     * 선도학생 추가
     *
     * @param student
     * @return
     */
    public Map<String, Object> create(@Param("student") Map<String, Object> student);

    /**
     * 선도학생 업데이트
     *
     * @param student
     * @return
     */
    public Map<String, Object> update(@Param("student") Map<String, Object> student);

    /**
     * 선도학생 삭제
     *
     * @param hakbun
     * @return
     */
    public Map<String, Object> delete(@Param("hakbun") String hakbun);

}
