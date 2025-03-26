import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/BookDetails")
public class BookDetailsServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
       
    public BookDetailsServlet() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try
		{
			int bookId = Integer.parseInt(request.getParameter("bookid"));
			request.setAttribute("bookid", bookId);
			Class.forName("com.mysql.cj.jdbc.Driver");
	        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/books?characterEncoding=latin1", "root", "khushi");
	        PreparedStatement pst = con.prepareStatement("SELECT * FROM book WHERE bookId = ?");
	        pst.setInt(1, bookId);
	        ResultSet rs = pst.executeQuery();
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();

            List<Map<String, Object>> bookList = new ArrayList<>();
            while (rs.next()) 
            {
                Map<String, Object> book = new HashMap<>();
                for (int i = 1; i <= columnCount; i++) 
                {
                    book.put(metaData.getColumnName(i), rs.getObject(i));
                }
                bookList.add(book);
            }
            request.setAttribute("bookList", bookList);
            rs.close();
            pst.close();
            con.close();
            RequestDispatcher dispatcher = request.getRequestDispatcher("BookDetails.jsp");
            dispatcher.forward(request, response);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
