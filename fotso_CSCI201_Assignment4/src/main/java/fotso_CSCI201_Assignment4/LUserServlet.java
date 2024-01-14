package fotso_CSCI201_Assignment4;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/LUserServlet")
public class LUserServlet extends HttpServlet {
private static final long serialVersionUID = 1L;


	public LUserServlet() {
		super();
		System.out.println("in constructor");
	}
	@Override
	public void init(ServletConfig config) throws ServletException {
		System.out.println("in init");
	}

	//add to log sign input
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		System.out.println("in service");

		String username = request.getParameter("usname");
		String password = request.getParameter("pass");
		Connection conn = null;
		Statement st= null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost/fotso_csci201_assignment4?user=root&password=root");
			st = conn.createStatement();
			rs = st.executeQuery("SELECT * FROM usertable WHERE username = '"+username+"' AND password = '"+password+"';");
			if(!rs.next()) {
				out.println("{}");

			} else {
				String sendName = "";
				int sendID = 0;
				String sendPass = "";
				String sendEmail = "";
				while(rs.next()) {
					sendID = rs.getInt("userId");
					sendName = rs.getString("username");
					sendPass = rs.getString("password");
					sendEmail = rs.getString("email");
				}
				
				System.out.println(sendID + " id");
				out.println("{");
				out.println("\"userId\" : " + "\""+sendID+"\",");
				out.println("\"Username\" : " + "\""+sendName+"\",");
				out.println("\"Email\" : " + "\""+sendEmail+"\",");
				out.println("\"Password\" : " + "\""+sendPass+"\"");
				out.println("}");
			}
			

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if(rs!= null) {
					rs.close();
				}
				if(st!= null) {
					st.close();
				}
				if(conn!= null) {
					conn.close();
				}
				}catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		//out.println("https://api.yelp.com/v3/businesses/search?latitude=\"+latitude.toString()+\"&longitude=\"+longitude.toString()+\"&term=\"+resname+\"&sort_by=best_match&limit=20");


	}
}
