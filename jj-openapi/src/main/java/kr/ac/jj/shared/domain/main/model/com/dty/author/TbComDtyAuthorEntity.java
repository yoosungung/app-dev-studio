package kr.ac.jj.shared.domain.main.model.com.dty.author;

import kr.ac.jj.shared.domain.main.model.MainEntity;

/**
 * 공통 - 직무별 권한 Entity
 */
abstract class TbComDtyAuthorEntity extends MainEntity {

    private static final long serialVersionUID = 2652973245587278255L;

    protected String dtyId;
    protected String authorId;

    public String getDtyId() {
        return this.dtyId;
    }

    public void setDtyId(String dtyId) {
        this.dtyId = dtyId;
    }

    public String getAuthorId() {
        return this.authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

}
