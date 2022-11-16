package kr.ac.jj.shared.infrastructure.framework.core.support.jndi;

public class JndiConfig {

    private boolean resourceRef;
    private String jndiName;

    public boolean isResourceRef() {
        return this.resourceRef;
    }

    public void setResourceRef(boolean resourceRef) {
        this.resourceRef = resourceRef;
    }

    public String getJndiName() {
        return this.jndiName;
    }

    public void setJndiName(String jndiName) {
        this.jndiName = jndiName;
    }

}
