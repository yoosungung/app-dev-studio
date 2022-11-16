package kr.ac.jj.shared.interfaces.admin.appmanage.hrmanage.mapper;

import org.apache.ibatis.annotations.Param;

import kr.ac.jj.shared.config.datasources.SharedDataSourcePortalConfig.SharedPortalSqlMapper;
import kr.ac.jj.shared.domain.main.model.intrfc.code.TbIntrfcCode;
import kr.ac.jj.shared.domain.main.model.intrfc.dept.empl.TbIntrfcDeptEmpl;
import kr.ac.jj.shared.domain.main.model.intrfc.user.TbIntrfcUser;
import kr.ac.jj.shared.infrastructure.framework.mybatis.persistence.dao.handler.DataResultHandler;

/**
 * 인사정보 관리 Portal Mapper
 */
@SharedPortalSqlMapper
public interface HumanResourceManagePortalMapper {

    /**
     * 코드 목록 조회
     *
     * @param codeType
     * @param resultHandler
     */
    public void selectCodeList(@Param("codeType") String codeType, DataResultHandler<TbIntrfcCode> resultHandler);

    /**
     * 사용자 목록 조회
     *
     * @param userId
     * @param resultHandler
     */
    public void selectUserList(@Param("userId") String userId, DataResultHandler<TbIntrfcUser> resultHandler);

    /**
     * 직원 조직 목록 조회
     *
     * @param resultHandler
     */
    public void selectEmployeeOrganizationList(DataResultHandler<TbIntrfcDeptEmpl> resultHandler);

}
