package kr.ac.jj.shared.domain.main.model.com.person;

import java.util.List;
import java.util.Locale;

import org.apache.commons.lang3.RegExUtils;
import org.apache.commons.lang3.StringUtils;

import kr.ac.jj.shared.domain.main.model.com.file.TbComFile;
import kr.ac.jj.shared.infrastructure.framework.web.context.request.RequestContextUtil;
import kr.ac.jj.shared.infrastructure.framework.web.support.codedata.CodeDataUtil;
import kr.ac.jj.shared.infrastructure.idgen.util.IdGenerationUtil;

/**
 * 공통 - 사람
 */
public class TbComPerson extends TbComPersonEntity {

    private static final long serialVersionUID = 5276765726760701206L;

    private String deptNames;
    private List<TbComFile> atchFileList;

    public TbComPerson newId() {
        this.setPersonId(IdGenerationUtil.createUid("TB_COM_PERSON"));

        return this;
    }

    public String getPersonNm() {
        Locale userLocale = RequestContextUtil.getLocale();

        if (Locale.KOREA.equals(userLocale) || Locale.KOREAN.equals(userLocale)) {
            return this.getKoreanNm();
        }

        if (Locale.CHINA.equals(userLocale) || Locale.CHINESE.equals(userLocale)) {
            return this.getChcrtNm();
        }

        return this.getEnglNm();
    }

    @Override
    public void setEmailAdres(String emailAdres) {
        this.emailAdres = RegExUtils.replaceAll(StringUtils.trim(emailAdres), "(\r\n|\r|\n|\n\r)", "");
    }

    public String getDeptNames() {
        return this.deptNames;
    }

    public void setDeptNames(String deptNames) {
        this.deptNames = deptNames;
    }

    public String getHffcSttusNm() {
        return CodeDataUtil.getCodeName("[HFFC_STTUS]", this.getHffcSttus(), this.getHffcSttus());
    }

    public List<TbComFile> getAtchFileList() {
        return this.atchFileList;
    }

    public void setAtchFileList(List<TbComFile> atchFileList) {
        this.atchFileList = atchFileList;
    }

}
