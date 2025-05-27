

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Withdraw
 */
@WebServlet("/Withdraw")
public class Withdraw extends HttpServlet 
{
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		//String name=request.getParameter("username");
		//String mail=request.getParameter("mail");
		//String dob=request.getParameter("dob");
		String psw=request.getParameter("psw");
		String ano=request.getParameter("ano");
//		String balance=request.getParameter("balance");
		String amo=request.getParameter("amo");
		double amo1=Double.parseDouble(amo);
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
			
			ResultSet n1=stmt.executeQuery("select * from details where ano='"+ano+"'");
			if (n1.next())
			{
				ResultSet n2=stmt.executeQuery("select * from details where ano='"+ano+"' and psw='"+psw+"'");
				if (n2.next())
				{
		     int n=stmt.executeUpdate("UPDATE details SET balance=balance-'"+amo1+"' WHERE ano='"+ano+"' AND psw='"+psw+"' and balance>='"+amo1+"'");
			 if(n==1) {
				    SimpleDateFormat df=new SimpleDateFormat("yyyy-mm-dd  hh:mm:ss");
					Timestamp ts=new Timestamp(System.currentTimeMillis());
					String type="withdraw";
					int n3=stmt.executeUpdate("insert into statement values('"+ano+"','"+type+"','"+amo1+"','"+df.format(ts)+"')");
					if(n3>0) {
						response.sendRedirect("index.html");
					}
					else
					{
						out.println("statement not inserted");
					}

			}
			else
			{
				out.println("insufficient funds");
			}
			}
				else {
					out.println("invalid password");
				}
			}
			else
			{
				out.println("invalid account number");
			}

			
			con.close();
			
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
	}

}
