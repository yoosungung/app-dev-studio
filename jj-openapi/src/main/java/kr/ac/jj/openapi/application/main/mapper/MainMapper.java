package kr.ac.jj.openapi.application.main.mapper;

import java.util.List;

import kr.ac.jj.openapi.config.datasources.DataSourceMainConfig.MainSqlMapper;
import kr.ac.jj.shared.application.bbs.faq.model.BbsFaqModel;
import kr.ac.jj.shared.application.bbs.gnrl.model.BbsGeneralModel;
import kr.ac.jj.shared.application.bbs.notice.model.BbsNoticeModel;

/**
 * 메인 Mapper
 */
@MainSqlMapper
public interface MainMapper {

    public List<BbsNoticeModel> readNoticeList();

    public List<BbsGeneralModel> readQnaList();

    public List<BbsFaqModel> readFaqList();

}
