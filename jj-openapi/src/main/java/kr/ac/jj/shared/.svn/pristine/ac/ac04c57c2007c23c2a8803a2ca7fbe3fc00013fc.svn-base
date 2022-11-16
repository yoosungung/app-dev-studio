package kr.ac.jj.shared.infrastructure.framework.core.support.message;

import java.io.File;
import java.util.List;
import java.util.Locale;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

import kr.ac.jj.shared.infrastructure.framework.core.support.io.file.FileUtil;
import kr.ac.jj.shared.infrastructure.framework.web.context.request.RequestContextUtil;

public class ReloadableAllBasenamesMessageSource extends ReloadableResourceBundleMessageSource {

    private static final Logger log = LoggerFactory.getLogger(ReloadableAllBasenamesMessageSource.class);

    private static final String BASENAME_PATTERN = "(.+Message)(.*).xml";

    public void addRootpath(String rootpath) {
        this.addRootpath(rootpath, BASENAME_PATTERN, 1);
    }

    public void addRootpath(String rootpath, String basenamePattern, int patternMatchGroupIndex) {
        this.addDirBasenames(rootpath, FileUtil.getResourceFile(rootpath), Pattern.compile(basenamePattern),
                patternMatchGroupIndex);
    }

    public void addRootpathList(List<String> rootpathList) {
        this.addRootpathList(rootpathList, BASENAME_PATTERN, 1);
    }

    public void addRootpathList(List<String> rootpathList, String basenamePattern, int patternMatchGroupIndex) {
        if (rootpathList == null) {
            return;
        }

        for (String rootpath : rootpathList) {
            this.addDirBasenames(rootpath, FileUtil.getResourceFile(rootpath), Pattern.compile(basenamePattern),
                    patternMatchGroupIndex);
        }
    }

    public void addBasenameList(List<String> basenameList) {
        if (basenameList == null) {
            return;
        }

        for (String basename : basenameList) {
            this.addBasename(basename);
        }
    }

    private void addDirBasenames(String rootpath, File dir, Pattern basenamePattern, int patternMatchGroupIndex) {
        File[] files = dir.listFiles();

        if (files == null) {
            return;
        }

        String rootAbsolutePath = FileUtil.getResourceFile(rootpath).getAbsolutePath();

        for (File file : files) {
            if (file.isDirectory()) {
                addDirBasenames(rootpath, file, basenamePattern, patternMatchGroupIndex);
                continue;
            }

            Matcher matcher = basenamePattern.matcher(file.getName());

            while (matcher.find()) {
                String absolutePath = new File(dir, matcher.group(patternMatchGroupIndex)).getAbsolutePath();
                this.addBasename(absolutePath.replace(rootAbsolutePath, rootpath).replaceAll("\\\\", "/"));
            }
        }
    }

    public void addBasename(String basename) {
        this.addBasenames(basename);

        log.debug("Message basename added ==> " + basename);
    }

    public Properties getMergedProperties() {
        Locale locale = RequestContextUtil.getLocale();

        PropertiesHolder mergedProperties = super.getMergedProperties(locale);

        return mergedProperties.getProperties();
    }

}
