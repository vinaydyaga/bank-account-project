

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Janvi")
public class Janvi extends HttpServlet 
{	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String name=request.getParameter("username");
		String mail=request.getParameter("mail");
		String dob=request.getParameter("dob");
		String psw=request.getParameter("psw");
		String ano=request.getParameter("ano");
		
		
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
			
			ResultSet n=stmt.executeQuery("SELECT * FROM details WHERE mail='"+mail+"'");
			if(n.next()) {
				response.sendRedirect("abc.html?error=mail_exists");
				return;
			}
			ResultSet n1=stmt.executeQuery("SELECT * FROM details WHERE ano='"+ano+"'");
			if(n1.next())
			{
				response.sendRedirect("abc.html?error=account_exists");
				return;
			}

			int balance=0;
			int res=stmt.executeUpdate("insert into details values('"+name+"','"+mail+"','"+dob+"','"+psw+"','"+ano+"','"+balance+"')");
			if(res==1) 
			{
				response.sendRedirect("log.html");
			}
			con.close();
			
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
		
	}

}
