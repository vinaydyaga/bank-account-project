

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Login")
public class Login extends HttpServlet 
{
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String name=request.getParameter("username");
		String psw=request.getParameter("psw");
		
		
		PrintWriter out=response.getWriter();
		//out.println(name);
		//out.println(mail);
		//out.println(dob);
		//out.println(psw);
		
		//load the driver
		try {
			//load the driver
			Class.forName("com.mysql.jdbc.Driver");
			
			//get connection
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/dev","root","vinay@32");
			
			//create statement
			Statement stmt=con.createStatement();
			
			ResultSet rs=stmt.executeQuery("SELECT * FROM details WHERE name='"+name+"' AND psw='"+psw+"'");
			if(rs.next())
			{
				response.sendRedirect("index.html");
			}
			else
			{
				out.println("user dosen't exist");
			}
			con.close();
			
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
	}

}
