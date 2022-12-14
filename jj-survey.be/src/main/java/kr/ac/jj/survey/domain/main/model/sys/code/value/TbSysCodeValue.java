package kr.ac.jj.survey.domain.main.model.sys.code.value;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import kr.ac.jj.survey.domain.main.model.sys.title.TbSysTitle;
import kr.ac.jj.survey.infrastructure.idgen.util.IdGenerationUtil;

/**
 * 시스템 - 코드 값
 */
public class TbSysCodeValue extends TbSysCodeValueEntity {
    private static final long serialVersionUID = -5228653368196029047L;

    protected List<TbSysTitle> codeValueNmTitleList;

    public TbSysCodeValue newId() {
        this.setCodeValueId(IdGenerationUtil.createUid("TB_SYS_CODE_VALUE"));

        return this;
    }

    @Override
    public String getCodeValueNmTitle() {
        return StringUtils.defaultIfEmpty(this.codeValueNmTitle, "SYS_CODE_VALUE_NM." + this.getCodeValueId());
    }

    public List<TbSysTitle> getCodeValueNmTitleList() {
        return this.codeValueNmTitleList;
    }

    public void setCodeValueNmTitleList(List<TbSysTitle> codeValueNmTitleList) {
        this.codeValueNmTitleList = codeValueNmTitleList;
    }

    public String getUseYnNm() {
        return super.getBooleanCodeName("/common/useYn", this.getUseYn());
    }
}
