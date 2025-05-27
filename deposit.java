

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

@WebServlet("/Deposit")
public class Deposit extends HttpServlet 
{
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		//String name=request.getParameter("username");
		//String mail=request.getParameter("mail");
		//String dob=request.getParameter("dob");
		//String psw=request.getParameter("psw");
		String ano=request.getParameter("ano");
		String num=request.getParameter("num");
		int anum = Integer.parseInt(ano);
		double balance = Double.parseDouble(num);
		
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
			
			int rs=stmt.executeUpdate("UPDATE details SET balance = '"+balance+"' WHERE ano = '"+anum+"'");
			if(rs==1) 
			{
				response.sendRedirect("index.html");
			}
			else
			{
				out.println("deposit not completed");
			}
			con.close();
			
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
	}

}
