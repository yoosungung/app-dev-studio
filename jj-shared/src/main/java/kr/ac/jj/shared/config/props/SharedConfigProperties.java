package kr.ac.jj.shared.config.props;

import java.util.List;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "config.shared")
public class SharedConfigProperties {

    private java.io.File logsDirectory;
    private java.io.File tempDirectory;
    private String defaultLocale;
    private Message message;
    private Log log;
    private File file;
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

    public Message getMessage() {
        return this.message;
    }

    public void setMessage(Message message) {
        this.message = message;
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

    public Login getLogin() {
        return this.login;
    }

    public void setLogin(Login login) {
        this.login = login;
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

    public static class Log {

        private boolean enable;
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
            private String serverCode;

            public boolean isEnable() {
                return this.enable;
            }

            public void setEnable(boolean enable) {
                this.enable = enable;
            }

            public String getServerCode() {
                return this.serverCode;
            }

            public void setServerCode(String serverCode) {
                this.serverCode = serverCode;
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

    public static class Login {

        private String defaultAuthorCode;
        private Admin admin;
        private Sso sso;
        private Fail fail;

        public String getDefaultAuthorCode() {
            return this.defaultAuthorCode;
        }

        public void setDefaultAuthorCode(String defaultAuthorCode) {
            this.defaultAuthorCode = defaultAuthorCode;
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
