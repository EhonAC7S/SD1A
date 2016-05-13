package senCity;

public class GPS {
	public String latitude;
	public String longitude;
	
	public GPS(String lat,String longi) {
		latitude=lat;
		longitude=longi;
	}
	
	public String getLatitude() {
		return this.latitude;
	}
	
	public String getLongitude() {
		return this.longitude;
	}
	
	public void setLatitude(String lat) {
		this.latitude = lat;
	}
	
	public void setLongitude(String longi){
		this.longitude = longi;
	}
	
	public void setLocation(String lat, String longi) {
		this.latitude = lat;
		this.longitude = longi;
	}

	public String toString() {
		return ("(latitude : " + this.latitude + ", longitude : " + this.longitude + ")");
	}
	
	public static void main (String[] args) {
		GPS donnees = new GPS("10","20");
		System.out.println(donnees.getLatitude() + "   " + donnees.getLongitude() + "");
		donnees.setLatitude("11.");
		System.out.println(donnees.toString());
		donnees.setLongitude("21.");
		System.out.println(donnees.toString());
		donnees.setLocation("12.", "22.");
		System.out.println(donnees.toString());
		
	}
	
}
