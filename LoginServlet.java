package Register_Login;

import java.io.IOException;
import java.io.PrintWriter;

import java.sql.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;




@WebServlet("/login")
public class LoginServlet extends HttpServlet{
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	    PrintWriter pw = resp.getWriter();
	    resp.setContentType("text/html");
	    pw.println("The server is on !!");
	    
	    try {
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        
	        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/myreg","root","12345");
	        
	        String username = req.getParameter("username");
	        String password = req.getParameter("pass");
	        
	        String query = "INSERT INTO loginfo(name, password) VALUES(?, ?)";
	        PreparedStatement ps = con.prepareStatement(query);
	        
	        ps.setString(1, username);
	        ps.setString(2, password);
	        
	        int rs = ps.executeUpdate();
	        
	        if (rs > 0) {
	            pw.println("User Registered Successfully");
	            RequestDispatcher rd = req.getRequestDispatcher("/welcome.jsp");
	            rd.forward(req, resp);
	        } else {
	            pw.print("<font color=red size=18>Some Error occurred !!<br>");
	            RequestDispatcher rd = req.getRequestDispatcher("/login.jsp");
	            rd.forward(req, resp);
	        }
	    } catch (ClassNotFoundException e) {
	        // Handle ClassNotFoundException
	        pw.println("Database driver not found.");
	    } catch (SQLException e) {
	        // Handle SQLException
	        pw.println("Database error: " + e.getMessage());
	    }
	}
}