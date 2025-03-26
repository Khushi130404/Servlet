import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/DashboardServlet")
public class DashboardServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        Cookie[] cookies = request.getCookies();
        String savedUsername = null;

        if (cookies != null) {
            for (Cookie c : cookies) {
                if (c.getName().equals("username")) {
                    savedUsername = c.getValue();
                    break;
                }
            }
        }

        if (session == null && savedUsername == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        if (savedUsername != null) {
            HttpSession newSession = request.getSession();
            newSession.setAttribute("user", savedUsername);
        }

        response.sendRedirect("dashboard.jsp");
    }
}
