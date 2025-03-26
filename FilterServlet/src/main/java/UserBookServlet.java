import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/UserBookServlet")
public class UserBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public UserBookServlet() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try
		{
			int bookId = Integer.parseInt(request.getParameter("bookid"));
			Class.forName("com.mysql.jdbc.Driver");
	        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/books?characterEncoding=latin1", "root", "khushi");
	        PreparedStatement pst = con.prepareStatement("SELECT bookId, bookName, authorNames, publication, dateOfPublication, priceOfBook, totalQuantityToOrder, totalCost FROM book WHERE bookId = ?");
	        pst.setInt(1, bookId);
	        ResultSet rs = pst.executeQuery();
            
            if(!rs.next())
            {
            	System.out.println("No book found for bookId: " + bookId);
            	request.setAttribute("isFound", false);
            }
            else
            {
            	request.setAttribute("isFound", true);
                Map<String, Object> book = new LinkedHashMap<>();
                book.put("bookId", rs.getInt("bookId"));
                book.put("bookName", rs.getString("bookName"));
                book.put("publication", rs.getString("publication"));
                book.put("priceOfBook", rs.getFloat("priceOfBook"));
                request.setAttribute("book_info", book);
            }
            rs.close();
            pst.close();
            con.close();
            RequestDispatcher dispatcher = request.getRequestDispatcher("userBookDetails.jsp");
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
