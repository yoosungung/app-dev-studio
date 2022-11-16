package kr.ac.jj.survey.domain.umslink.mapper.members;

import org.apache.ibatis.annotations.Param;

import kr.ac.jj.survey.config.datasources.DataSourceUmslinkConfig.UmslinkSqlMapper;

@UmslinkSqlMapper
public interface MembersMapper {

    /**
     * 조회 - UMS 그룹 ID
     *
     * @param ssocode
     * @return
     */
    public String selectUmsGroupId(@Param("ssocode") String ssocode);

}
