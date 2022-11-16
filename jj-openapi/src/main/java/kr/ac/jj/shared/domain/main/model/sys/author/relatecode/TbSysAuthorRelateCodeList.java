package kr.ac.jj.shared.domain.main.model.sys.author.relatecode;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import kr.ac.jj.shared.infrastructure.framework.core.support.collection.BaseMap;
import kr.ac.jj.shared.infrastructure.framework.core.support.collection.BaseMapList;

public class TbSysAuthorRelateCodeList extends ArrayList<TbSysAuthorRelateCode> {

    private static final long serialVersionUID = -5538143993544311647L;

    private String authorId;
    private String relateCodeSe;

    public void setAuthorId(String authorId) {
        this.authorId = authorId;

        for (TbSysAuthorRelateCode tbSysAuthorRelateCode : this) {
            tbSysAuthorRelateCode.setAuthorId(authorId);
        }
    }

    public String getRelateCodeSe() {
        return this.relateCodeSe;
    }

    public void setRelateCodeSe(String relateCodeSe) {
        this.relateCodeSe = relateCodeSe;

        for (TbSysAuthorRelateCode tbSysAuthorRelateCode : this) {
            tbSysAuthorRelateCode.setRelateCodeSe(relateCodeSe);
        }
    }

    public void setCodeDataList(BaseMapList codeDataList) {
        this.setCodeDataList(codeDataList, null);
    }

    public void setCodeDataList(BaseMapList codeDataList, String[] codes) {
        for (BaseMap codeData : codeDataList) {
            if (codes != null && !ArrayUtils.contains(codes, codeData.get("code"))) {
                continue;
            }

            TbSysAuthorRelateCode tbSysAuthorRelateCode = new TbSysAuthorRelateCode();
            tbSysAuthorRelateCode.setAuthorId(this.authorId);
            tbSysAuthorRelateCode.setRelateCodeSe(this.relateCodeSe);
            tbSysAuthorRelateCode.setRelateCodeValue((String) codeData.get("code"));
            tbSysAuthorRelateCode.setRelateCodeValueNm((String) codeData.get("name"));

            this.add(tbSysAuthorRelateCode);
        }
    }

    public void setValueList(List<TbSysAuthorRelateCode> valueList, boolean updateYn) {
        if (updateYn) {
            for (TbSysAuthorRelateCode tbSysAuthorRelateCode : this) {
                tbSysAuthorRelateCode.setRelateCodeAuthorYn(false);

                for (TbSysAuthorRelateCode value : valueList) {
                    if (StringUtils.equals(tbSysAuthorRelateCode.getRelateCodeValue(), value.getRelateCodeValue())) {
                        tbSysAuthorRelateCode.setRelateCodeAuthorYn(true);
                        break;
                    }
                }
            }
        } else {
            for (TbSysAuthorRelateCode tbSysAuthorRelateCode : valueList) {
                tbSysAuthorRelateCode.setRelateCodeAuthorYn(true);

                this.add(tbSysAuthorRelateCode);
            }
        }
    }

}
