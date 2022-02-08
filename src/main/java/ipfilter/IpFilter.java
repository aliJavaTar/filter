package ipfilter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebFilter("/hi_ip")
public class IpFilter implements Filter {
    private FilterConfig filterConfig;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        String username = filterConfig.getInitParameter("username");
        System.out.println(username);
        this.filterConfig=filterConfig;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String forbiddenIp = "127.0.0.1";
        String remoteIp = request.getRemoteAddr();
        if (remoteIp.equals(forbiddenIp)) {
            ((HttpServletResponse)response).sendError(403);
        }else
            chain.doFilter(request,response);
    }
}
