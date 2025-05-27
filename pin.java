

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Pin")
public class Pin extends HttpServlet 
{
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		//String name=request.getParameter("username");
		String mail=request.getParameter("mail");
		//String dob=request.getParameter("dob");
		String psw=request.getParameter("psw");
		String psw1=request.getParameter("psw1");
//		String balance=request.getParameter("balance");
//		String acc1=request.getParameter("ano");
//		String acc2=request.getParameter("ano1");
//		String amo=request.getParameter("amo");
//		Double amo1=Double.parseDouble(amo);
//		double bal = Double.parseDouble(balance);
		
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
			
			int n=stmt.executeUpdate("UPDATE details SET psw='"+psw1+"' WHERE mail='"+mail+"' AND psw='"+psw+"'");
			if(n==1) {
				response.sendRedirect("index.html");
				return;
			}

			
			con.close();
			
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
	}

}
