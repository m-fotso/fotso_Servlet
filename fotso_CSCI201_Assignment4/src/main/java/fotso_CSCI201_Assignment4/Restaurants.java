package fotso_CSCI201_Assignment4;
import java.util.List;
public class Restaurants {
	//http://www.jsonschema2pojo.org/
	private Region region;
	private List<Business> businesses;
	private Integer total;


	public Restaurants() {
		
	}
	public Region getRegion() {
		return region;
	}
	
	public Integer getTotal() {
		return total;
		}

		public void setTotal(Integer total) {
		this.total = total;
		}
	
	public void setRegion(Region region) {
	this.region = region;
	}
	
	public List<Business> getBusinesses() {
		return businesses;
	}
	public void setBusinesses(List<Business> d) {
		businesses = d;
	}

	public Business getDataByIndex(int i) {
		return businesses.get(i);
	}

	public int getSize() {
		return businesses.size();
	}

	/*public String displayString() {
		String display = "";
		for(int i=0; i<data.size(); i++) {
			display += data.get(i).displayString();
		}
		return display;
	}*/

	public Business searchbyName(String s) {
		Business temp = null;
		for (Business element : businesses) {
			if(element.getName().toLowerCase().equals(s.toLowerCase())) {
				temp = element;
			}
		}
		return temp;
	}

	/*public String searchbyMenu(String s) {
		String temp = "";
		for(int i=0; i<data.size(); i++) {
			List<String> items = data.get(i).checkMenu(s);
			if( items != null) {
				temp += data.get(i).getName() + " has ";
				for(int j=0; j<items.size(); j++) {
					if(j != items.size()-1) {
						temp += items.get(j) + " and ";
					} else {
						temp += items.get(j) + ".\n";
					}

				}
			}
		}
		return temp;
	}*/

	public void addRestaurant(Business r) {
		businesses.add(r);

	}

	public void removeRestaurant(Business r) {
		businesses.remove(r);

	}

	//src: https://www.studytonight.com/java-programs/java-program-to-sort-an-array-in-alphabetical-order#google_vignette
	public void AlphSort() {
		for(int i=0; i<businesses.size(); i++) {
			for(int j=i+1; j<businesses.size(); j++) {
				if(businesses.get(i).getName().toLowerCase().compareTo(businesses.get(j).getName().toLowerCase()) > 0) {
					Business temp = businesses.get(i);
					businesses.set(i, businesses.get(j));
					businesses.set(j, temp);
				}
			}

		}
	}

	public void revAlphaSort() {
		for(int i=0; i<businesses.size(); i++) {
			for(int j=i+1; j<businesses.size(); j++) {
				if(businesses.get(i).getName().toLowerCase().compareTo(businesses.get(j).getName().toLowerCase()) < 0) {
					Business temp = businesses.get(i);
					businesses.set(i, businesses.get(j));
					businesses.set(j, temp);
				}
			}

		}
	}

	



}
