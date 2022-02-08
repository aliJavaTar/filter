package filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
@WebFilter("/time")
public class FilterTime implements Filter {

    protected FilterConfig config;

    public void init(FilterConfig config) throws ServletException {
        this.config = config;
    }

    public void destroy() {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        long startTime = System.currentTimeMillis();
        System.out.println("startTime : "+startTime);
        chain.doFilter(request, response);
        long elapsed = System.currentTimeMillis() - startTime;
        System.out.println("elapsed : "+elapsed);
        String name = "servlet";
        if (request instanceof HttpServletRequest) {
            name = ((HttpServletRequest) request).getRequestURI();
            System.out.println(name);
        }

        config.getServletContext().log(name + " took " + elapsed + " ms");
    }
}
