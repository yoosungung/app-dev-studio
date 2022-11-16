package kr.ac.jj.survey.domain.main.model.sys.code.group;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import kr.ac.jj.survey.domain.main.model.sys.title.TbSysTitle;
import kr.ac.jj.survey.infrastructure.idgen.util.IdGenerationUtil;

/**
 * 시스템 - 코드 그룹
 */
public class TbSysCodeGroup extends TbSysCodeGroupEntity {
    private static final long serialVersionUID = 1141232886901515489L;

    private List<TbSysTitle> codeGroupNmTitleList;

    public TbSysCodeGroup newId() {
        this.setCodeGroupId(IdGenerationUtil.createUid("TB_SYS_CODE_GROUP"));

        return this;
    }

    public String getCodeGroupNmTitle() {
        return StringUtils.defaultIfEmpty(this.codeGroupNmTitle, "SYS_CODE_GROUP_NM." + this.getCodeGroupId());
    }

    public List<TbSysTitle> getCodeGroupNmTitleList() {
        return this.codeGroupNmTitleList;
    }

    public void setCodeGroupNmTitleList(List<TbSysTitle> codeGroupNmTitleList) {
        this.codeGroupNmTitleList = codeGroupNmTitleList;
    }

    public String getUseYnNm() {
        return super.getBooleanCodeName("/common/useYn", this.getUseYn());
    }
}
