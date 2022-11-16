package kr.ac.jj.shared.infrastructure.framework.web.servlet.util;

import kr.ac.jj.shared.infrastructure.framework.web.servlet.attribute.ServletAttribute;
import kr.ac.jj.shared.infrastructure.framework.web.servlet.attribute.ServletDefaultAttribute;

public class ServletUtil {

    private static ServletAttribute servletAttribute;

    public static ServletAttribute getAttribute() {
        if (ServletUtil.servletAttribute == null) {
            ServletUtil.setAttribute(new ServletDefaultAttribute());
        }

        return ServletUtil.servletAttribute;
    }

    public static void setAttribute(ServletAttribute servletAttribute) {
        ServletUtil.servletAttribute = servletAttribute;
    }

}
