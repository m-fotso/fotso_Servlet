package fotso_CSCI201_Assignment4;

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

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

public class gResServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	public gResServlet() {
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
		String id = request.getParameter("userID");
		String name= request.getParameter("userN");
		Connection conn = null;
		Statement st= null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		Gson gson = new Gson();
		response.setContentType("text/html");
		out.println("<form name=\"searchform\" onsubmit=\"return search()\">\r\n"
				+ "	<div id=\"fcontainer\">\r\n"
				+ "		<fieldset class=\"gensearch\">\r\n"
				+ "			<input type=\"text\" id=\"name\" class=\"stext\" name=\"name\" placeholder=\"Restaurant Name\">\r\n"
				+ "			<input type=\"submit\" id=\"search\" value=\"&#61442;\">\r\n"
				+ "			<input type=\"number\" id=\"la\" class=\"stext\" name=\"la\" placeholder=\"Latitude\">\r\n"
				+ "			<input type=\"number\" id=\"lo\" class=\"stext\" name=\"lo\" placeholder=\"Longitude\">\r\n"
				+ "		</fieldset>\r\n"
				+ "		\r\n"
				+ "		<fieldset class=\"extrasearch\">\r\n"
				+ "			<div class=\"g1\">\r\n"
				+ "			<input type=\"radio\" id=\"bs\" name=\"extra\" value=\"best_match\" checked>\r\n"
				+ "			<label for=\"bs\">Best Match</label>\r\n"
				+ "			</div>\r\n"
				+ "			<div class=\"g1\">\r\n"
				+ "			<input type=\"radio\" id=\"rc\" name=\"extra\" value=\"review_count\">\r\n"
				+ "			<label for=\"rc\">Review Count</label>\r\n"
				+ "			</div>\r\n"
				+ "			<div class=\"g1\">\r\n"
				+ "			<input type=\"radio\" id=\"rt\" name=\"extra\" value=\"rating\">\r\n"
				+ "			<label for=\"rt\">Rating</label>\r\n"
				+ "			</div>\r\n"
				+ "			<div class=\"g1\">\r\n"
				+ "			<input type=\"radio\" id=\"dt\" name=\"extra\" value=\"distance\">	\r\n"
				+ "			<label for=\"dt\">Distance</label>\r\n"
				+ "			</div>\r\n"
				+ "			<span onclick=\"openNav()\" class=\"gmaps\">&#61505; Google Maps (Drop a pin!)</span>\r\n"
				+ "		</fieldset>\r\n"
				+ " 	<h>"+name+"'s Favorites:</h>"
				+ "		</div>\r\n"
				+ "		\r\n"
				+ "	</form>");
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost/fotso_csci201_assignment4?user=root&password=root");
			st = conn.createStatement();
			if(id != null) {
				st = conn.createStatement();
				rs = st.executeQuery("SELECT * FROM reservetable WHERE userID = '"+id+"';");
				String sendD = "";
				String sendName = "";
				String sendT = "";
				String sendLo = "";
				String sendLa = "";
				while(rs.next()) {
					sendName = rs.getString("resname");
					sendD = rs.getString("date");
					sendT = rs.getString("time");
					sendLo = rs.getString("longitude");
					sendLa = rs.getString("latitude");
					Restaurants res = null;
					HttpRequest serequest = HttpRequest.newBuilder()
						    .uri(URI.create("https://api.yelp.com/v3/businesses/search?latitude="+sendLa+"&longitude="+sendLo+"&term="+sendName.replace(" ", "%20")+"&sort_by=best_match&limit=1"))
						    .header("accept", "application/json")
						    .header("Authorization", "Bearer Mp4d8Az47GO6cwrSAbbu1eZ4LQ2eQRi4hICPJWro6qBZhdmJfhpKOTCqguW4GlX255d3rmoPKeSjMgmh7FXuCkgo7pjEaC9dg9i3vsvsHOmtUFxtbYoQftorbHA3ZXYx")
						    .method("GET", HttpRequest.BodyPublishers.noBody())
						    .build();
						HttpResponse<String> nresponse;
						
							try {
								nresponse = HttpClient.newHttpClient().send(serequest, HttpResponse.BodyHandlers.ofString());
								String resbody = nresponse.body();
								System.out.println(resbody);
								res = gson.fromJson(resbody, Restaurants.class);
								out.println("<div class=\"results\">");
								String fullID = "https://api.yelp.com/v3/businesses/search?latitude="+res.getDataByIndex(0).getCoordinates().getLatitude()+"&longitude="+res.getDataByIndex(0).getCoordinates().getLongitude()+"&term="+res.getDataByIndex(0).getName().replace(" ", "%20").replace("-", "%20")+"&sort_by=best_match&limit=1";
								out.println("<img src='"+res.getDataByIndex(0).getImageUrl()+"' alt='"+res.getDataByIndex(0).getAlias()+"' class='results' id=\""+fullID+"\" onclick=\"return detailIT(this)\">");
								out.println("<h2 class\"results\">"+res.getDataByIndex(0).getName()+"</h2>");
								out.println("<h4 class=\"results\">"+res.getDataByIndex(0).getLocation().displayTheAddress()+"</h4>");
								out.println("<p class=\"results\"> Date: "+sendD+"</p>");
								out.println("<p class=\"results\"> Time: "+sendT+"</p>");
								out.println("</div>");
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
				}
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
	}
}
