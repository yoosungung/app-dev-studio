package kr.ac.jj.shared.infrastructure.framework.mybatis.persistence.datasource.config;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.core.io.Resource;

import kr.ac.jj.shared.infrastructure.framework.core.support.io.util.IOUtil;
import kr.ac.jj.shared.infrastructure.framework.mybatis.persistence.datasource.props.MyBatisConfigProperties;

public class MapperLocationResources {

    private static final Logger log = LoggerFactory.getLogger(MapperLocationResources.class);

    private final MyBatisConfigProperties myBatisConfig;

    public MapperLocationResources(MyBatisConfigProperties myBatisConfig) {
        this.myBatisConfig = myBatisConfig;
    }

    public Resource[] getResources() {
        String[] locationPatterns = myBatisConfig.getMapperLocations();

        if (locationPatterns == null) {
            return null;
        }

        List<Resource> resourceList = new ArrayList<Resource>();
        GenericApplicationContext applicationContext = null;

        try {

            applicationContext = new GenericApplicationContext();

            for (String locationPattern : locationPatterns) {
                try {
                    resourceList.addAll(Arrays.asList(applicationContext.getResources(locationPattern)));
                } catch (IOException e) {
                    log.error(e.getMessage(), e);
                }
            }

        } catch (RuntimeException e) {
            throw e;
        } finally {
            IOUtil.closeQuietly(applicationContext);
        }

        return resourceList.toArray(new Resource[resourceList.size()]);
    }

}
