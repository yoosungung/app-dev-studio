package kr.ac.jj.shared.domain.main.model.sys.resrce.author;

import kr.ac.jj.shared.domain.main.model.sys.resrce.TbSysResrce;

/**
 * 시스템 - 리소스별 권한 TO 시스템 - 리소스
 */
public class TbSysResrceAuthorToResrce extends TbSysResrce {

    private static final long serialVersionUID = 4991681651340393403L;

    private Integer resrceAuthorCo;
    private Boolean resrceAuthorYn;

    public Integer getResrceAuthorCo() {
        return this.resrceAuthorCo;
    }

    public void setResrceAuthorCo(Integer resrceAuthorCo) {
        this.resrceAuthorCo = resrceAuthorCo;
    }

    public Boolean getResrceAuthorYn() {
        return this.resrceAuthorYn;
    }

    public void setResrceAuthorYn(Boolean resrceAuthorYn) {
        this.resrceAuthorYn = resrceAuthorYn;
    }

}
