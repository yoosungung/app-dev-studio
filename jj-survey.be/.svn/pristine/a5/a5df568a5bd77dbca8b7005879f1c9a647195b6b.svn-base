package kr.ac.jj.survey.infrastructure.framework.core.support.io.util;

import java.io.Closeable;
import java.io.Flushable;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IOUtil {
    private static final Logger log = LoggerFactory.getLogger(IOUtil.class);

    public static void closeQuietly(Closeable... closeables) {
        for (Closeable c : closeables) {
            closeCloseable(c);
        }
    }

    private static void closeCloseable(Closeable c) {
        if (c == null) {
            return;
        }

        try {
            c.close();
        } catch (IOException e) {
            log.warn("Error ocurred while closing " + c, e);
        }
    }

    public static void flushQuietly(Flushable... flushables) {
        for (Flushable f : flushables) {
            flushFlushable(f);
        }
    }

    private static void flushFlushable(Flushable f) {
        if (f == null) {
            return;
        }

        try {
            f.flush();
        } catch (IOException e) {
            log.warn("Error occurred while flushing " + f, e);
        }
    }
}
