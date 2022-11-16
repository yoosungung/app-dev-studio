package kr.ac.jj.shared.domain.main.model.com.email.recptn;

import javax.mail.Message.RecipientType;

import com.fasterxml.jackson.annotation.JsonIgnore;

import kr.ac.jj.shared.infrastructure.idgen.util.IdGenerationUtil;

/**
 * 공통 메일 수신
 */
public class TbComEmailRecptn extends TbComEmailRecptnEntity {

    private static final long serialVersionUID = 742060933195469933L;

    public TbComEmailRecptn newId() {
        this.setEmailRecptnId(IdGenerationUtil.createUid("TB_COM_EMAIL_RECPTN"));

        return this;
    }

    @JsonIgnore
    public RecipientType getRecptnTyEnum() {
        if ("CC".equalsIgnoreCase(this.recptnTy)) {
            return RecipientType.CC;
        }

        if ("BCC".equalsIgnoreCase(this.recptnTy)) {
            return RecipientType.BCC;
        }

        return RecipientType.TO;
    }

    @JsonIgnore
    public void setRecptnTyEnum(RecipientType recptnTy) {
        this.recptnTy = recptnTy.toString().toUpperCase();
    }

}
