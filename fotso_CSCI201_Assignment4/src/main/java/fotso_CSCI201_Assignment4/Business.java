package fotso_CSCI201_Assignment4;

import java.util.List;

public class Business {
	private String id;
	private String alias;
	private String name;
	private String image_url;
	private boolean is_closed;
	private String url;
	private int review_count;
	private List<Category> categories;
	private double rating;
	private Coordinates coordinates;
	private List<String> transactions;
	private String price;
	private Location location;
	private String phone;
	private String display_phone;
	private double distance;
	
	
	public Business() {
		
	}
	public String getId() {
	return id;
	}

	public void setId(String id) {
	this.id = id;
	}

	public String getAlias() {
	return alias;
	}

	public void setAlias(String alias) {
	this.alias = alias;
	}

	public String getImageUrl() {
	return image_url;
	}

	public void setImageUrl(String image_url) {
	this.image_url = image_url;
	}

	public boolean getIsClosed() {
	return is_closed;
	}

	public void setIsClosed(boolean is_closed) {
	this.is_closed = is_closed;
	}

	public String getUrl() {
	return url;
	}

	public void setUrl(String url) {
	this.url = url;
	}

	public int getReviewCount() {
	return review_count;
	}

	public void setReviewCount(int review_count) {
	this.review_count = review_count;
	}

	public List<Category> getCategories() {
	return categories;
	}

	public void setCategories(List<Category> categories) {
	this.categories = categories;
	}

	public double getRating() {
	return rating;
	}

	public void setRating(double rating) {
	this.rating = rating;
	}

	public Coordinates getCoordinates() {
	return coordinates;
	}

	public void setCoordinates(Coordinates coordinates) {
	this.coordinates = coordinates;
	}

	public List<String> getTransactions() {
	return transactions;
	}

	public void setTransactions(List<String> transactions) {
	this.transactions = transactions;
	}

	public String getPrice() {
	return price;
	}

	public void setPrice(String price) {
	this.price = price;
	}

	public Location getLocation() {
	return location;
	}

	public void setLocation(Location location) {
	this.location = location;
	}

	public String getPhone() {
	return phone;
	}

	public void setPhone(String phone) {
	this.phone = phone;
	}

	public String getDisplayPhone() {
	return display_phone;
	}

	public void setDisplayPhone(String display_phone) {
	this.display_phone = display_phone;
	}

	public double getDistance() {
	return distance;
	}

	public void setDistance(double distance) {
	this.distance = distance;
	}



	//https://stackoverflow.com/questions/6086334/is-it-good-practice-to-make-the-constructor-throw-an-exception
	



	public String getName() {
		return name;
	}

	public void setName(String n) {
		name = n;
	}

	public double getLa() {
		return coordinates.getLatitude();
	}

	public void setLa(double la) {
		coordinates.setLatitude(la);
	}

	public double getLo() {
		return coordinates.getLongitude();
	}

	public void setLo(double lo) {
		coordinates.setLongitude(lo);
	}



	//https://www.geeksforgeeks.org/java-toradians-method-example/
	//https://stackoverflow.com/questions/39824914/how-do-i-round-to-the-nearest-ten#:~:text=You%20can%20use%20Math.,num%2F10.0)%20*%2010%20.&text=if%20you%20divide%20by%2010d,be%20any%20(primitive)%20number.
	


}
