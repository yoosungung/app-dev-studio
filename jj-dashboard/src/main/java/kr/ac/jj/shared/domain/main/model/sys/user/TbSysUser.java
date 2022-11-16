package kr.ac.jj.shared.domain.main.model.sys.user;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import kr.ac.jj.shared.infrastructure.framework.core.support.context.ApplicationContextUtil;
import kr.ac.jj.shared.infrastructure.framework.web.support.codedata.CodeDataUtil;
import kr.ac.jj.shared.infrastructure.idgen.util.IdGenerationUtil;

/**
 * 시스템 - 사용자
 */
public class TbSysUser extends TbSysUserEntity {

    private static final long serialVersionUID = -2943928791188335587L;

    private String userNm;

    public TbSysUser newId() {
        this.setUserId(IdGenerationUtil.createUid("TB_SYS_USER"));

        return this;
    }

    @Override
    @JsonProperty(access = Access.WRITE_ONLY)
    public String getLoginPassword() {
        return this.loginPassword;
    }

    public void encodeLoginPassword() {
        if (StringUtils.isNotEmpty(this.getLoginPassword())) {
            PasswordEncoder passwordEncoder = ApplicationContextUtil.getBean(PasswordEncoder.class);
            this.setLoginPassword(passwordEncoder.encode(this.getLoginPassword()));
        }
    }

    public String getUserNm() {
        return this.userNm;
    }

    public void setUserNm(String userNm) {
        this.userNm = userNm;
    }

    public String getUseYnNm() {
        return CodeDataUtil.getCodeName("/common/useYn", this.getUseYn());
    }

    public String getUserLocaleNm() {
        return CodeDataUtil.getCodeName("/common/systemLocale", this.getUserLocale(), this.getUserLocale());
    }

}
