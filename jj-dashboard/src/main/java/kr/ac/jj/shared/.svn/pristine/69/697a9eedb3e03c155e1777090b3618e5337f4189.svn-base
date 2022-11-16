package kr.ac.jj.shared.infrastructure.framework.core.persistence.dao.handler;

public abstract class DataUpdatedCheckHandler {

    private String keyName;
    private boolean disabled;

    public DataUpdatedCheckHandler() {
        this.keyName = "X-AnyWorks-Data-Updated";
    }

    public String getKeyName() {
        return this.keyName;
    }

    public void setKeyName(String keyName) {
        this.keyName = keyName;
    }

    public boolean isDisabled() {
        return this.disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

    public abstract void handleUpdated();

}
