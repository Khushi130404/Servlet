import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter("/LoginServlet") 
public class AuthenticationFilter implements Filter 
{    
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException 
    {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        if (username == null || password == null || username.trim().isEmpty() || password.trim().isEmpty()) 
        {
            req.setAttribute("errorMessage", "Username and Password are required!");
            req.getRequestDispatcher("login.jsp").forward(req, res);
            return;
        }

        try 
        {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/books?characterEncoding=latin1", "root", "khushi");
            PreparedStatement pst = con.prepareStatement("SELECT role FROM users WHERE username = ? AND password = ?");
            pst.setString(1, username);
            pst.setString(2, password);
            ResultSet rs = pst.executeQuery();

            if (!rs.next()) 
            {
                req.setAttribute("errorMessage", "Incorrect Credentials");
                req.getRequestDispatcher("login.jsp").forward(req, res);
                return;
            } 

            HttpSession session = req.getSession();
            session.setAttribute("username", username);
            session.setAttribute("role", rs.getString("role"));

            chain.doFilter(request, response);

        } 
        catch (Exception e) 
        {
            e.printStackTrace();
            res.sendRedirect("login.jsp");
        }
    }

    public void init(FilterConfig fConfig) throws ServletException {}

    public void destroy() {}
}
