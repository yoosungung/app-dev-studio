package kr.ac.jj.survey.domain.main.model.sys.author;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import kr.ac.jj.survey.domain.main.model.sys.title.TbSysTitle;
import kr.ac.jj.survey.infrastructure.idgen.util.IdGenerationUtil;
import kr.ac.jj.survey.infrastructure.title.util.TitleUtil;

/**
 * 시스템 - 권한
 */
public class TbSysAuthor extends TbSysAuthorEntity {
    private static final long serialVersionUID = 6021517453382809465L;

    private List<TbSysTitle> authorNmTitleList;

    public TbSysAuthor newId() {
        this.setAuthorId(IdGenerationUtil.createUid("TB_SYS_AUTHOR"));

        return this;
    }

    public String getAuthorNm() {
        return TitleUtil.getTitleCn(this.getAuthorNmTitle());
    }

    public String getAuthorNmTitle() {
        return StringUtils.defaultIfEmpty(this.authorNmTitle, "SYS_AUTHOR_NM." + this.getAuthorId());
    }

    public List<TbSysTitle> getAuthorNmTitleList() {
        return this.authorNmTitleList;
    }

    public void setAuthorNmTitleList(List<TbSysTitle> authorNmTitleList) {
        this.authorNmTitleList = authorNmTitleList;
    }

    public String getUseYnNm() {
        return super.getBooleanCodeName("/common/useYn", this.getUseYn());
    }

    public String getBassAuthorYnNm() {
        return super.getBooleanCodeName("/common/yesNo", this.getBassAuthorYn());
    }

    public String getUserAuthorYnNm() {
        return super.getBooleanCodeName("/common/yesNo", this.getUserAuthorYn());
    }
}
