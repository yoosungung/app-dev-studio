package kr.ac.jj.shared.infrastructure.framework.web.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpMethod;
import org.springframework.web.filter.OncePerRequestFilter;

public class HeaderHttpMethodOverrideFilter extends OncePerRequestFilter {

    private static final String HTTP_METHOD_OVERRIDE_HEADER_NAME = "X-HTTP-Method-Override";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        final String headerValue = request.getHeader(HTTP_METHOD_OVERRIDE_HEADER_NAME);

        if (HttpMethod.POST.name().equalsIgnoreCase(request.getMethod()) && StringUtils.isNotEmpty(headerValue)) {
            final HttpServletRequest wrapper = new HttpMethodRequestWrapper(request, headerValue.toUpperCase());
            filterChain.doFilter(wrapper, response);
        } else {
            filterChain.doFilter(request, response);
        }
    }

    private static class HttpMethodRequestWrapper extends HttpServletRequestWrapper {

        private final String method;

        public HttpMethodRequestWrapper(HttpServletRequest request, final String method) {
            super(request);
            this.method = method;
        }

        @Override
        public String getMethod() {
            return this.method;
        }

    }

}
