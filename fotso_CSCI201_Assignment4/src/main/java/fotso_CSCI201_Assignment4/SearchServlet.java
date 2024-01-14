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

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;


@WebServlet("/SearchServlet")
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public SearchServlet() {
		super();
		System.out.println("in constructor");
	}
	@Override
	public void init(ServletConfig config) throws ServletException {
		System.out.println("in init");
	}

	
	
	protected void service(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		System.out.println("in service");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String term = request.getParameter("name");
		String longitude = request.getParameter("lo");
		String latitude = request.getParameter("la");
		String special = request.getParameter("extra");
		Gson gson = new Gson();
		Restaurants res = null;
		HttpRequest serequest = HttpRequest.newBuilder()
			    .uri(URI.create("https://api.yelp.com/v3/businesses/search?latitude="+latitude+"&longitude="+longitude+"&term="+term+"&sort_by="+special+"&limit=20"))
			    .header("accept", "application/json")
			    .header("Authorization", "Bearer Mp4d8Az47GO6cwrSAbbu1eZ4LQ2eQRi4hICPJWro6qBZhdmJfhpKOTCqguW4GlX255d3rmoPKeSjMgmh7FXuCkgo7pjEaC9dg9i3vsvsHOmtUFxtbYoQftorbHA3ZXYx")
			    .method("GET", HttpRequest.BodyPublishers.noBody())
			    .build();
			HttpResponse<String> nresponse;
			try {
				nresponse = HttpClient.newHttpClient().send(serequest, HttpResponse.BodyHandlers.ofString());
				String resbody = nresponse.body();
				System.out.print(resbody);
				res = gson.fromJson(resbody, Restaurants.class);
				
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
						+ " 	<h>Results for\""+term+"\""
						+ "		</div>\r\n"
						+ "		\r\n"
						+ "	</form>");
				for(int i=0; i<res.getSize(); i++) {
					out.println("<div class=\"results\">");
					String fullID = "https://api.yelp.com/v3/businesses/search?latitude="+res.getDataByIndex(i).getCoordinates().getLatitude()+"&longitude="+res.getDataByIndex(i).getCoordinates().getLongitude()+"&term="+res.getDataByIndex(i).getName().replace(" ", "%20").replace("-", "%20")+"&sort_by=best_match&limit=1";
					
					out.println("<img src='"+res.getDataByIndex(i).getImageUrl()+"' alt='"+res.getDataByIndex(i).getAlias()+"' class='results' id=\""+fullID+"\" onclick=\"return detailIT(this)\">");
					out.println("<h2 id=\"ti\">"+res.getDataByIndex(i).getName()+"</h2>");
					out.println("<h4 id=\"ad\">"+res.getDataByIndex(i).getLocation().displayTheAddress()+"</h4>");
					out.println("<p id=\"url\">"+res.getDataByIndex(i).getUrl()+"</p>");
					out.println("</div>");
				}
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JsonSyntaxException e) {
	            // Handle exception if JSON syntax is invalid
	            System.err.println("Error parsing JSON: " + e.getMessage());
	        }
			
			
	}
}
