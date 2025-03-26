import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet 
{
    private static final long serialVersionUID = 1L;

    public LoginServlet() 
    {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
    	HttpSession session = request.getSession(false);
    	if (session == null || session.getAttribute("username") == null) 
    	{
            response.sendRedirect("login.jsp");
            return;
    	}
        String role = (String) session.getAttribute("role");
        if ("admin".equalsIgnoreCase(role)) 
        {
            List<Map<String, Object>> bookList = new ArrayList<>();
            try 
            {
                Class.forName("com.mysql.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/books?characterEncoding=latin1", "root", "khushi");
                PreparedStatement pst = con.prepareStatement("SELECT bookId, bookName, authorNames, publication, dateOfPublication, priceOfBook, totalQuantityToOrder, totalCost FROM book");
                ResultSet rs = pst.executeQuery();
                ResultSetMetaData metaData = rs.getMetaData();
                int columnCount = metaData.getColumnCount();
                while (rs.next()) 
                {
                    Map<String, Object> row = new LinkedHashMap();
                    for (int i = 1; i <= columnCount; i++) 
                    {
                        row.put(metaData.getColumnName(i), rs.getObject(i));
                    }
                    bookList.add(row);
                }
                session.setAttribute("bookList", bookList);
                rs.close();
                pst.close();
                con.close();
            } 
            catch (Exception e) 
            {
                e.printStackTrace();
            }
            response.sendRedirect("adminBookRequest.jsp");
        } 
        else 
        {
            response.sendRedirect("userBookRequest.jsp");
        }
    }
}
