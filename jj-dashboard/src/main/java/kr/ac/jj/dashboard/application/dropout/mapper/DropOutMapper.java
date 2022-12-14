package kr.ac.jj.dashboard.application.dropout.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import kr.ac.jj.dashboard.config.datasources.DataSourceDwConfig.DwSqlMapper;

/**
 * 중도탈락 Mapper
 */
@DwSqlMapper
public interface DropOutMapper {

    /**
     * 중도탈락 학생 목록 조회
     *
     * @param resultHandler
     * @param paging
     * @param search
     */
    public List<Map<String, Object>> readList(@Param("search") Map<String, Object> search);

    /**
     * 중도탈락 학생 조회
     *
     * @param hakbun
     * @return
     */
    public Map<String, Object> readStudent(@Param("hakbun") String hakbun);

    /**
     * 중도탈락 학생 추가
     *
     * @param student
     * @return
     */
    public void create(@Param("student") Map<String, Object> student);

    /**
     * 중도탈락 학생 중복 조회
     *
     * @param student
     * @return
     */
    public int isDuplicate(@Param("hakbun") String hakbun);

}
