package kr.ac.jj.survey.domain.umslink.mapper.em.tran;

import org.apache.ibatis.annotations.Param;

import kr.ac.jj.survey.config.datasources.DataSourceUmslinkConfig.UmslinkSqlMapper;
import kr.ac.jj.survey.domain.umslink.model.em.tran.EmTran;

@UmslinkSqlMapper
public interface EmTranMapper {

    /**
     * ์กฐํ
     *
     * @param tranPr
     * @return
     */
    public EmTran select(@Param("tranPr") Long tranPr);

    /**
     * ์์ฑ
     *
     * @param emTran
     * @return
     */
    public int insert(@Param("emTran") EmTran emTran);

}
