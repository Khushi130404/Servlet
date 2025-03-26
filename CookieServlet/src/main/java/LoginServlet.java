import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet 
{
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
        HttpSession session = request.getSession(false);
        Cookie[] cookies = request.getCookies();
        String savedUsername = null;

        if (cookies != null) 
        {
            for (Cookie c : cookies) 
            {
                if (c.getName().equals("username")) 
                {
                    savedUsername = c.getValue();
                    break;
                }
            }
        }

        if (session != null && session.getAttribute("user") != null) 
        {
            response.sendRedirect("dashboard.jsp");
            return;
        }

        if (savedUsername != null) 
        {
            HttpSession newSession = request.getSession();
            newSession.setAttribute("user", savedUsername);
            response.sendRedirect("dashboard.jsp");
            return;
        }

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        try 
        {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/books?characterEncoding=latin1", "root", "khushi");
            
            pst = con.prepareStatement("SELECT role FROM users WHERE username = ? AND password = ?");
            pst.setString(1, username);
            pst.setString(2, password);
            rs = pst.executeQuery();

            if (!rs.next()) 
            {
                response.sendRedirect("login.jsp?error=true");
            } 
            else 
            {
                HttpSession newSession = request.getSession();
                newSession.setAttribute("user", username);
                newSession.setMaxInactiveInterval(5 * 60);

                Cookie userCookie = new Cookie("username", username);
                Cookie userRole = new Cookie("role", rs.getString("role"));
                userCookie.setMaxAge(5 * 60);
                userRole.setMaxAge(5 * 60);

                userCookie.setHttpOnly(true); 
                userRole.setHttpOnly(true);

                response.addCookie(userCookie);
                response.addCookie(userRole);
                response.sendRedirect("dashboard.jsp");
            }
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        } 
        finally 
        {
            try 
            {
                if (rs != null) rs.close();
                if (pst != null) pst.close();
                if (con != null) con.close();
            } 
            catch (Exception ex) 
            {
                ex.printStackTrace();
            }
        }
    }
}
