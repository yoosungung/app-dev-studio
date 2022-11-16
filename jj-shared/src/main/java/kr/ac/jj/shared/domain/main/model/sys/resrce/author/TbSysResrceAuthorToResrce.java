package kr.ac.jj.shared.domain.main.model.sys.resrce.author;

import kr.ac.jj.shared.domain.main.model.sys.resrce.TbSysResrce;

/**
 * 시스템 - 리소스별 권한 TO 시스템 - 리소스
 */
public class TbSysResrceAuthorToResrce extends TbSysResrce {

    private static final long serialVersionUID = 4991681651340393403L;

    private String authorId;

    public String getAuthorId() {
        return this.authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

}
