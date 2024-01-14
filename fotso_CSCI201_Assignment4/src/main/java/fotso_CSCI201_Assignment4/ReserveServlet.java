package fotso_CSCI201_Assignment4;

import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

@WebServlet("/ReserveServlet")
public class ReserveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	public ReserveServlet() {
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
		String date = request.getParameter("date");
		String time = request.getParameter("time");
		String note = request.getParameter("note");
		String name = request.getParameter("name");
		String id = request.getParameter("userID");
		String latitude = request.getParameter("latitude");
		String longitude = request.getParameter("longitdue");
		Connection conn = null;
		Statement st= null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		response.setContentType("text/html");
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost/fotso_csci201_assignment4?user=root&password=root");
			st = conn.createStatement();
			if(id != null) {
				ps = conn.prepareStatement("INSERT INTO reservetable(reservname, resname, date, time, userID, longitude, latitude) VALUES ('"+note+"', '"+name+"', '"+date+"', '"+time+"', '"+id+"', '"+longitude+"', '"+latitude+"');");
				ps.execute();
			}
			out.println("");

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
	}
}

