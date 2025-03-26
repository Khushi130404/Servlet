import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ServerTime")
public class ServerTimeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE, dd MMMM yyyy | HH:mm:ss a");
        String formattedTime = now.format(formatter);

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<html><head><title>Server Time</title>");
        out.println("<style>");
        out.println("body { font-family: Arial, sans-serif; background: linear-gradient(to right, #6a11cb, #2575fc); ");
        out.println("display: flex; align-items: center; justify-content: center; height: 100vh; margin: 0; color: white; }");
        out.println(".container { background: rgba(255, 255, 255, 0.1); padding: 30px; border-radius: 10px; ");
        out.println("box-shadow: 0px 4px 10px rgba(0, 0, 0, 0.2); text-align: center; backdrop-filter: blur(10px); }");
        out.println("h1 { font-size: 28px; margin-bottom: 10px; }");
        out.println("h2 { font-size: 22px; font-weight: 300; }");
        out.println(".time { font-size: 26px; font-weight: bold; color: #ADB2D4;}");
        out.println("</style>");
        out.println("</head><body>");
        out.println("<div class='container'>");
        out.println("<h1>Current Server Time</h1>");
        out.println("<h2 class='time'>" + formattedTime + "</h2>");
        out.println("</div>");
        out.println("</body></html>");
    }
}
