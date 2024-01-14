package fotso_CSCI201_Assignment4;

import java.io.Serializable;

public class Coordinates implements Serializable {
	private static final long serialVersionUID = 1L;
	private double latitude;
	private double longitude;

	public Coordinates() {
	
	}

	public double getLatitude() {
	return latitude;
	}

	public void setLatitude(double latitude) {
	this.latitude = latitude;
	}

	public double getLongitude() {
	return longitude;
	}

	public void setLongitude(double longitude) {
	this.longitude = longitude;
	}

	@Override
	public String toString() {
		return latitude +" "+longitude;
	}
}
