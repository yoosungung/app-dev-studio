package kr.ac.jj.shared.infrastructure.framework.core.foundation.profiles;

import java.util.List;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.env.AbstractEnvironment;
import org.springframework.core.env.Environment;

import kr.ac.jj.shared.infrastructure.framework.core.support.context.ApplicationContextUtil;

public class ProfilesUtil extends AbstractEnvironment {

    private static Environment environment;

    public static Environment getEnvironment() {
        if (ProfilesUtil.environment == null) {
            ProfilesUtil.environment = ApplicationContextUtil.getConfigBean(Environment.class);
        }

        return ProfilesUtil.environment;
    }

    public static String[] getActiveProfileNames() {
        return getActiveProfileNames(false);
    }

    public static String[] getActiveProfileNames(boolean withoutDefault) {
        if (getEnvironment() != null) {
            return getEnvironment().getActiveProfiles();
        }

        String activeProfilesProp = System.getProperty(ACTIVE_PROFILES_PROPERTY_NAME);
        String[] activeProfiles;

        if (StringUtils.isBlank(activeProfilesProp)) {
            if (withoutDefault) {
                activeProfiles = new String[] {};
            } else {
                activeProfiles = new String[] { RESERVED_DEFAULT_PROFILE_NAME };
            }
        } else {
            activeProfiles = activeProfilesProp.split(",");
        }

        for (int i = 0; i < activeProfiles.length; i++) {
            activeProfiles[i] = activeProfiles[i].trim();
        }

        return activeProfiles;
    }

    public static boolean isActivated(List<String> nameList) {
        if (nameList != null) {
            return isActivated(nameList.toArray(new String[nameList.size()]));
        }

        return false;
    }

    public static boolean isActivated(String... names) {
        if (names == null) {
            return false;
        }

        String[] activeProfiles = getActiveProfileNames();

        for (String name : names) {
            if (activeProfiles.length == 0 && RESERVED_DEFAULT_PROFILE_NAME.equals(name)) {
                return true;
            }

            if (ArrayUtils.contains(activeProfiles, name)) {
                return true;
            }
        }

        return false;
    }

}
