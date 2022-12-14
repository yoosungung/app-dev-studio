package kr.ac.jj.survey.domain.main.model.com.bbs;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import kr.ac.jj.survey.domain.main.model.sys.title.TbSysTitle;
import kr.ac.jj.survey.infrastructure.idgen.util.IdGenerationUtil;
import kr.ac.jj.survey.infrastructure.title.util.TitleUtil;

/**
 * 공통 - 게시판
 */
public class TbComBbs extends TbComBbsEntity {
    private static final long serialVersionUID = -1764834081708960157L;

    protected List<TbSysTitle> bbsNmTitleList;

    public TbComBbs newId() {
        this.setBbsId(IdGenerationUtil.createUid("TB_COM_BBS"));

        return this;
    }

    public String getBbsNm() {
        return TitleUtil.getTitleCn(this.getBbsNmTitle());
    }

    @Override
    public String getBbsNmTitle() {
        return StringUtils.defaultIfEmpty(this.bbsNmTitle, "COM_BBS_NM." + this.getBbsId());
    }

    public List<TbSysTitle> getBbsNmTitleList() {
        return this.bbsNmTitleList;
    }

    public void setBbsNmTitleList(List<TbSysTitle> bbsNmTitleList) {
        this.bbsNmTitleList = bbsNmTitleList;
    }

    public String getAnswerPosblYnNm() {
        return super.getBooleanCodeName("/common/yesNo", this.getAnswerPosblYn());
    }

    public String getRdcntIndictYnNm() {
        return super.getBooleanCodeName("/common/yesNo", this.getRdcntIndictYn());
    }

    public String getNoticeBbsYnNm() {
        return super.getBooleanCodeName("/common/yesNo", this.getNoticeBbsYn());
    }

    public String getEditrApplcYnNm() {
        return super.getBooleanCodeName("/common/yesNo", this.getEditrApplcYn());
    }
}
