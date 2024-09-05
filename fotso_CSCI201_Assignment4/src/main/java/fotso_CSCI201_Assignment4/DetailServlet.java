package fotso_CSCI201_Assignment4;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

@WebServlet("/DetailServlet")
public class DetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public DetailServlet() {
		super();
		System.out.println("in constructor");
	}
	
	public void init(ServletConfig config) throws ServletException {
		System.out.println("in init");
	}

	
	
	protected void service(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		System.out.println("in service");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String term = request.getParameter("link");
		String longitude = request.getParameter("longitude");
		String latitude = request.getParameter("latitude");
		String name = request.getParameter("term");
		String special = request.getParameter("sort_by");
		String limit = request.getParameter("limit");
		Gson gson = new Gson();
		Restaurants res = null;
		HttpRequest serequest = HttpRequest.newBuilder()
			    .uri(URI.create(term+"&longitude="+longitude+"&term="+name.replace(" ", "%20")+"&sort_by="+special+"&limit="+limit))
			    .header("accept", "application/json")
			    .header("Authorization", "Bearer insert_key")
			    .method("GET", HttpRequest.BodyPublishers.noBody())
			    .build();
			HttpResponse<String> nresponse;
			try {
				nresponse = HttpClient.newHttpClient().send(serequest, HttpResponse.BodyHandlers.ofString());
				String resbody = nresponse.body();
				
				res = gson.fromJson(resbody, Restaurants.class);
				System.out.println(res.getSize());
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
					out.println("<div class=\"details\">");
					out.println("<img src='"+res.getDataByIndex(i).getImageUrl()+"' alt='"+res.getDataByIndex(i).getAlias()+"' class='results' id='rimage' data-value='"+res.getDataByIndex(i).getName()+"'>");
					out.println("<h2 class='details' id='info' name-value='"+res.getDataByIndex(i).getName()+"' la-value='"+res.getDataByIndex(i).getCoordinates().getLatitude()+"' lo-value='"+res.getDataByIndex(i).getCoordinates().getLongitude()+"'>"+res.getDataByIndex(i).getName()+"</h2>");
					out.println("<h4 class='details'>Address: "+res.getDataByIndex(i).getLocation().displayTheAddress()+"</h4>");
					out.println("<p class='details'>Phone No. "+res.getDataByIndex(i).getDisplayPhone() +"</p>");
					out.println("<p class='details'>Cuisine: "+res.getDataByIndex(i).getCategories().get(0).getTitle() +"</p>");
					out.println("<p class='details'>Price: "+res.getDataByIndex(i).getPrice() +"</p>");
					out.println("<p class='details'>Rating: "+res.getDataByIndex(i).getRating() +"</p>");
					out.println("<input type='button' value='&#61445Add to Favorites' id='fav' onclick='return fav()'>");
					out.println("<input type='button' value='Add Reservation' id='res' onclick='return resDisplay()'>");
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
