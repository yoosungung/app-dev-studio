package kr.ac.jj.shared.config.props;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "config.shared")
public class SharedConfigProperties {

    private java.io.File logsDirectory;
    private java.io.File tempDirectory;
    private String defaultLocale;
    private Csrf csrf;
    private String serverUrl;
    private Ssl ssl;
    private Message message;
    private Error error;
    private Log log;
    private File file;
    private Email email;
    private Sms sms;
    private Login login;

    public java.io.File getLogsDirectory() {
        return this.logsDirectory;
    }

    public void setLogsDirectory(java.io.File logsDirectory) {
        this.logsDirectory = logsDirectory;
    }

    public java.io.File getTempDirectory() {
        return this.tempDirectory;
    }

    public void setTempDirectory(java.io.File tempDirectory) {
        this.tempDirectory = tempDirectory;
    }

    public String getDefaultLocale() {
        return this.defaultLocale;
    }

    public void setDefaultLocale(String defaultLocale) {
        this.defaultLocale = defaultLocale;
    }

    public Csrf getCsrf() {
        return this.csrf;
    }

    public void setCsrf(Csrf csrf) {
        this.csrf = csrf;
    }

    public String getServerUrl() {
        return this.serverUrl;
    }

    public void setServerUrl(String serverUrl) {
        this.serverUrl = serverUrl;
    }

    public Ssl getSsl() {
        return this.ssl;
    }

    public void setSsl(Ssl ssl) {
        this.ssl = ssl;
    }

    public Message getMessage() {
        return this.message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public Error getError() {
        return this.error;
    }

    public void setError(Error error) {
        this.error = error;
    }

    public Log getLog() {
        return this.log;
    }

    public void setLog(Log log) {
        this.log = log;
    }

    public File getFile() {
        return this.file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public Email getEmail() {
        return this.email;
    }

    public void setEmail(Email email) {
        this.email = email;
    }

    public Sms getSms() {
        return this.sms;
    }

    public void setSms(Sms sms) {
        this.sms = sms;
    }

    public Login getLogin() {
        return this.login;
    }

    public void setLogin(Login login) {
        this.login = login;
    }

    public static class Csrf {

        private Boolean disable;
        private String[] ignoringAntMatchers;

        public Boolean getDisable() {
            return this.disable;
        }

        public void setDisable(Boolean disable) {
            this.disable = disable;
        }

        public String[] getIgnoringAntMatchers() {
            return this.ignoringAntMatchers;
        }

        public void setIgnoringAntMatchers(String[] ignoringAntMatchers) {
            this.ignoringAntMatchers = ignoringAntMatchers;
        }

    }

    public static class Ssl {

        private String cacertsPath;

        public String getCacertsPath() {
            return this.cacertsPath;
        }

        public void setCacertsPath(String cacertsPath) {
            this.cacertsPath = cacertsPath;

            if (StringUtils.isNotEmpty(cacertsPath)) {
                System.setProperty("javax.net.ssl.trustStore", cacertsPath);
                System.setProperty("javax.net.ssl.trustAnchors", cacertsPath);
            }
        }

    }

    public static class Message {

        private InvalidText invalidText;

        public InvalidText getInvalidText() {
            return this.invalidText;
        }

        public void setInvalidText(InvalidText invalidText) {
            this.invalidText = invalidText;
        }

        public static class InvalidText {

            private String prefix;
            private String suffix;

            public String getPrefix() {
                return this.prefix;
            }

            public void setPrefix(String prefix) {
                this.prefix = prefix;
            }

            public String getSuffix() {
                return this.suffix;
            }

            public void setSuffix(String suffix) {
                this.suffix = suffix;
            }

        }

    }

    public static class Error {

        private String page;

        public String getPage() {
            return this.page;
        }

        public void setPage(String page) {
            this.page = page;
        }

    }

    public static class Log {

        private boolean enable;
        private Action action;
        private Query query;
        private Error error;
        private Login login;
        private Menu menu;

        public boolean isEnable() {
            return this.enable;
        }

        public void setEnable(boolean enable) {
            this.enable = enable;
        }

        public Action getAction() {
            return this.action;
        }

        public void setAction(Action action) {
            this.action = action;
        }

        public Query getQuery() {
            return this.query;
        }

        public void setQuery(Query query) {
            this.query = query;
        }

        public Error getError() {
            return this.error;
        }

        public void setError(Error error) {
            this.error = error;
        }

        public Login getLogin() {
            return this.login;
        }

        public void setLogin(Login login) {
            this.login = login;
        }

        public Menu getMenu() {
            return this.menu;
        }

        public void setMenu(Menu menu) {
            this.menu = menu;
        }

        public static class Action {

            private boolean enable;

            public boolean isEnable() {
                return this.enable;
            }

            public void setEnable(boolean enable) {
                this.enable = enable;
            }

        }

        public static class Query {

            private boolean enable;

            public boolean isEnable() {
                return this.enable;
            }

            public void setEnable(boolean enable) {
                this.enable = enable;
            }

        }

        public static class Error {

            private boolean enable;

            public boolean isEnable() {
                return this.enable;
            }

            public void setEnable(boolean enable) {
                this.enable = enable;
            }

        }

        public static class Login {

            private boolean enable;

            public boolean isEnable() {
                return this.enable;
            }

            public void setEnable(boolean enable) {
                this.enable = enable;
            }

        }

        public static class Menu {

            private boolean enable;

            public boolean isEnable() {
                return this.enable;
            }

            public void setEnable(boolean enable) {
                this.enable = enable;
            }

        }

    }

    public static class File {

        private Repository repository;

        public Repository getRepository() {
            return this.repository;
        }

        public void setRepository(Repository repository) {
            this.repository = repository;
        }

        public static class Repository {

            private boolean createIfNotExists;
            private Map<String, String> repositories;

            public boolean isCreateIfNotExists() {
                return this.createIfNotExists;
            }

            public void setCreateIfNotExists(boolean createIfNotExists) {
                this.createIfNotExists = createIfNotExists;
            }

            public Map<String, String> getRepositories() {
                return this.repositories;
            }

            public void setRepositories(Map<String, String> repositories) {
                this.repositories = repositories;
            }

        }

    }

    public static class Email {

        private String testEmailAddress;
        private int onetimeSendCount;
        private String senderName;

        public String getTestEmailAddress() {
            return this.testEmailAddress;
        }

        public void setTestEmailAddress(String testEmailAddress) {
            this.testEmailAddress = testEmailAddress;
        }

        public int getOnetimeSendCount() {
            return Math.max(this.onetimeSendCount, 1);
        }

        public void setOnetimeSendCount(int onetimeSendCount) {
            this.onetimeSendCount = onetimeSendCount;
        }

        public String getSenderName() {
            return this.senderName;
        }

        public void setSenderName(String senderName) {
            this.senderName = senderName;
        }

    }

    public static class Sms {

        private String senderName;

        public String getSenderName() {
            return this.senderName;
        }

        public void setSenderName(String senderName) {
            this.senderName = senderName;
        }

    }

    public static class Login {

        private String[] userPersonSe;
        private String[] defaultAuthorCodes;
        private Map<String, String> autoLoginKeys;
        private Admin admin;
        private Sso sso;
        private Fail fail;

        public String[] getUserPersonSe() {
            return this.userPersonSe;
        }

        public void setUserPersonSe(String[] userPersonSe) {
            this.userPersonSe = userPersonSe;
        }

        public String[] getDefaultAuthorCodes() {
            return this.defaultAuthorCodes;
        }

        public void setDefaultAuthorCodes(String[] defaultAuthorCodes) {
            this.defaultAuthorCodes = defaultAuthorCodes;
        }

        public Map<String, String> getAutoLoginKeys() {
            return this.autoLoginKeys;
        }

        public void setAutoLoginKeys(Map<String, String> autoLoginKeys) {
            this.autoLoginKeys = autoLoginKeys;
        }

        public Admin getAdmin() {
            return this.admin;
        }

        public void setAdmin(Admin admin) {
            this.admin = admin;
        }

        public Sso getSso() {
            return this.sso;
        }

        public void setSso(Sso sso) {
            this.sso = sso;
        }

        public Fail getFail() {
            return this.fail;
        }

        public void setFail(Fail fail) {
            this.fail = fail;
        }

        public static class Admin {

            private boolean enable;
            private List<String> ipList;
            private String key;
            private String password;

            public boolean isEnable() {
                return this.enable;
            }

            public void setEnable(boolean enable) {
                this.enable = enable;
            }

            public List<String> getIpList() {
                return this.ipList;
            }

            public void setIpList(List<String> ipList) {
                this.ipList = ipList;
            }

            public String getKey() {
                return this.key;
            }

            public void setKey(String key) {
                this.key = key;
            }

            public String getPassword() {
                return this.password;
            }

            public void setPassword(String password) {
                this.password = password;
            }

        }

        public static class Sso {

            private boolean enable;

            public boolean isEnable() {
                return this.enable;
            }

            public void setEnable(boolean enable) {
                this.enable = enable;
            }

        }

        public static class Fail {

            private int availCount;

            public int getAvailCount() {
                return this.availCount;
            }

            public void setAvailCount(int availCount) {
                this.availCount = availCount;
            }

        }

    }

}
