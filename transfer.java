

import java.io.IOException;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Transfer")
public class Transfer extends HttpServlet 
{
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		//String name=request.getParameter("username");
				//String mail=request.getParameter("mail");
				//String dob=request.getParameter("dob");
				String psw=request.getParameter("psw");
//				String ano=request.getParameter("ano");
//				String balance=request.getParameter("balance");
				String acc1=request.getParameter("ano");
				String acc2=request.getParameter("ano1");
				String amo=request.getParameter("amo");
				Double amo1=Double.parseDouble(amo);
//				double bal = Double.parseDouble(balance);
				
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
					ResultSet n1=stmt.executeQuery("select * from details where ano='"+acc1+"'");
					if (n1.next())
					{
						ResultSet n=stmt.executeQuery("select * from details where ano='"+acc2+"'");
						if (n.next())
						{
						ResultSet n2=stmt.executeQuery("select * from details where ano='"+acc1+"' and psw='"+psw+"'");
						if (n2.next())
						{
					
					stmt.executeUpdate("UPDATE details SET balance=balance-'"+amo1+"' WHERE ano='"+acc1+"' AND balance>='"+amo1+"'");
					int n6=stmt.executeUpdate("UPDATE details SET balance=balance+'"+amo1+"' WHERE ano='"+acc2+"'");
					if(n6==1) {
						SimpleDateFormat df=new SimpleDateFormat("yyyy-mm-dd  hh:mm:ss");
						Timestamp ts=new Timestamp(System.currentTimeMillis());
						String type="withdraw";
						int n3=stmt.executeUpdate("insert into statement values('"+acc1+"','"+type+"','"+amo1+"','"+df.format(ts)+"')");
						String type1="debit";
							int n4=stmt.executeUpdate("insert into statement values('"+acc2+"','"+type1+"','"+amo1+"','"+df.format(ts)+"')");
							if(n4>0) {
								response.sendRedirect("index.html");
						}
						else
						{
							out.println("statement not inserted");
						}
					}
					else
					{
						out.println("details are invalid");
					}
						}
						else {
							out.println("password is invalid");
						}
					}
					else {
						out.println("Target account number is invalid");
					    }
					}
					else {
						out.println("Account number doesn't exist");
					}
					
					con.close();
					
				} catch (Exception e) 
				{
					e.printStackTrace();
				}
	}

}
