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
@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	public UserServlet() {
		super();
		System.out.println("in constructor");
	}
	
	public void init(ServletConfig config) throws ServletException {
		System.out.println("in init");
	}

	//add to log sign input
	
	protected void service(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		System.out.println("in service");
		PrintWriter out = response.getWriter();
		String username = request.getParameter("nusname");
		String password = request.getParameter("npass");
		String email = request.getParameter("nemail");
		String cpass = request.getParameter("cpass");
		Connection conn = null;
		Statement st= null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		response.setContentType("application/json");
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost/fotso_csci201_assignment4?user=root&password=root");
			st = conn.createStatement();
			rs = st.executeQuery("SELECT * FROM usertable WHERE username = '"+username+"' AND password = '"+password+"';");
			if(rs.next()) {
					System.out.println(username+"1");
					out.println("{}");
			} else {
					System.out.println(username);
					ps = conn.prepareStatement("INSERT INTO usertable(username, password, email) VALUES ('"+username+"', '"+password+"', '"+email+"');");
					
					ps.execute();
					
					rs = st.executeQuery("SELECT * FROM usertable WHERE username = '"+username+"' AND password = '"+password+"';");
					String sendName = "";
					String sendPass = "";
					String sendEmail = "";
					int sendID = 0;
					while(rs.next()) {
						sendID = rs.getInt("userID");
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
