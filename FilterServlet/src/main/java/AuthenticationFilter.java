import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebFilter("/*") 
public class AuthenticationFilter implements Filter {

    public void init(FilterConfig fConfig) throws ServletException {}

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        String role = req.getParameter("role");
        String path = req.getServletPath();
        if (path.equals("/login.jsp") || path.equals("/LoginServlet")) {
            chain.doFilter(request, response);
            return;
        }
        if ("user".equals(role) && path.startsWith("/admin")) {
            res.sendRedirect("accessDenied.jsp"); 
            return;
        } 
        if ("admin".equals(role) && path.startsWith("/user")) {
            res.sendRedirect("accessDenied.jsp");
            return;
        }

        chain.doFilter(request, response);
    }

    public void destroy() {}
}
