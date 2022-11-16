package kr.ac.jj.survey.infrastructure.framework.web.multipart.policy;

import java.util.HashMap;
import java.util.Map;

import kr.ac.jj.survey.infrastructure.framework.core.foundation.exception.SysException;

public class FilePolicies {
    private final FilePolicy defaultPolicy;
    private final Map<String, FilePolicy> filePolicies;

    public FilePolicies() {
        this.defaultPolicy = new FilePolicy();
        this.filePolicies = new HashMap<String, FilePolicy>();
    }

    public FilePolicy getDefaultPolicy() {
        return this.defaultPolicy;
    }

    public FilePolicy newFilePolicy(String name, FilePolicy basePolicy) {
        FilePolicy filePolicy = new FilePolicy(basePolicy);

        this.filePolicies.put(name, filePolicy);

        return filePolicy;
    }

    public FilePolicy newFilePolicy(String name) {
        return this.newFilePolicy(name, this.defaultPolicy);
    }

    public FilePolicy getFilePolicy(String name) {
        FilePolicy filePolicy = this.filePolicies.get(name);

        if (filePolicy == null) {
            throw new SysException("File policy [" + name + "] is not exists.");
        }

        return filePolicy;
    }
}
