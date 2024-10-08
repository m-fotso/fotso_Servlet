package fotso_CSCI201_Assignment4;
import java.util.List;

public class Location {
	private String address1;
	private Object address2;
	private Object address3;
	private String city;
	private String zip_code;
	private String country;
	private String state;
	private List<String> display_address;

	public Location() {
		
	}
	public String getAddress1() {
	return address1;
	}

	public void setAddress1(String address1) {
	this.address1 = address1;
	}

	public Object getAddress2() {
	return address2;
	}

	public void setAddress2(Object address2) {
	this.address2 = address2;
	}

	public Object getAddress3() {
	return address3;
	}

	public void setAddress3(Object address3) {
	this.address3 = address3;
	}

	public String getCity() {
	return city;
	}

	public void setCity(String city) {
	this.city = city;
	}

	public String getZipCode() {
	return zip_code;
	}

	public void setZipCode(String zip_code) {
	this.zip_code = zip_code;
	}

	public String getCountry() {
	return country;
	}

	public void setCountry(String country) {
	this.country = country;
	}

	public String getState() {
	return state;
	}

	public void setState(String state) {
	this.state = state;
	}

	public List<String> getDisplayAddress() {
	return display_address;
	}

	public void setDisplayAddress(List<String> display_address) {
	this.display_address = display_address;
	}
	
	public String displayTheAddress() {
		String temp = "";
		for (String element : display_address) {
			temp += element+" ";
		}
		return temp;
	}

	
}
