package kr.ac.jj.survey.domain.main.model.com.person;

import java.util.Locale;

import kr.ac.jj.survey.domain.main.model.com.file.TbComFileList;
import kr.ac.jj.survey.infrastructure.framework.web.context.request.RequestContextUtil;
import kr.ac.jj.survey.infrastructure.idgen.util.IdGenerationUtil;

/**
 * 공통 - 사람
 */
public class TbComPerson extends TbComPersonEntity {
    private static final long serialVersionUID = 5276765726760701206L;

    private String writngPsnNm;
    private String changePsnNm;
    private String deptNm;
    private TbComFileList atchFileList;

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

    public String getWritngPsnNm() {
        return this.writngPsnNm;
    }

    public void setWritngPsnNm(String writngPsnNm) {
        this.writngPsnNm = writngPsnNm;
    }

    public String getChangePsnNm() {
        return this.changePsnNm;
    }

    public void setChangePsnNm(String changePsnNm) {
        this.changePsnNm = changePsnNm;
    }

    public String getDeptNm() {
        return this.deptNm;
    }

    public void setDeptNm(String deptNm) {
        this.deptNm = deptNm;
    }
    /*
     * public String getHffcSttusNm() { return
     * CodeDataUtil.getCodeName("[HFFC_STTUS]", this.getHffcSttus(),
     * this.getHffcSttus()); }
     */

    public TbComFileList getAtchFileList() {
        return this.atchFileList;
    }

    public void setAtchFileList(TbComFileList atchFileList) {
        this.atchFileList = atchFileList;
    }
}
