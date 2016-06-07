package senCity;

import java.lang.Math;

public class SommetGraph {
	private GPS gps;
	private Traces traces;
	public static final double R =6378137.0;
	public SommetGraph(GPS gps) {
		this.gps = gps;
		this.traces = new ArrayListTraces();
	}
	
	public void addTrace(Trace trace) {
		this.traces.ajouter(trace);
	}
	
	public GPS getLocalisation() {
		return this.gps;
	}
	
	public Traces getTraces() {
		return this.traces;
	}
	
	public boolean isLowDistance(SommetGraph s1, double distanceMaxi) { //deux sommets sont ils assez proches?
		double abs1 = Double.parseDouble(s1.getLocalisation().getLatitude());
		double abs2 = Double.parseDouble(this.getLocalisation().getLatitude());
		double ord1 = Double.parseDouble(s1.getLocalisation().getLongitude());
		double ord2 = Double.parseDouble(this.getLocalisation().getLongitude());
		double distance = distance(abs1,ord1,abs2,ord2);
		return (distanceMaxi>distance);
	}
	
	public double getDistance(SommetGraph s1) { //on renvoie la distance entre deux sommets proches
		double abs1 = Double.parseDouble(s1.getLocalisation().getLatitude());
		double abs2 = Double.parseDouble(this.getLocalisation().getLatitude());
		double ord1 = Double.parseDouble(s1.getLocalisation().getLongitude());
		double ord2 = Double.parseDouble(this.getLocalisation().getLongitude());
		//double distance = 7.5*1000*Math.sqrt(Math.pow(Math.abs(abs1-abs2), 2)+Math.pow(Math.abs(ord1-ord2), 2));
		return distance(abs1,ord1,abs2,ord2);
	}
	
	public static double distance(double lati1, double long1, double lati2, double long2){
		double lat1 = Math.toRadians(lati1); 
		double lat2 = Math.toRadians(lati2); 
		double lon1 = Math.toRadians(long1); 
		double lon2 = Math.toRadians(long2); 		
		double res = R*(Math.acos(Math.sin(lat1)*Math.sin(lat2)+Math.cos(lat1)*Math.cos(lat2)*Math.cos((lon1-lon2))));
	return res;
	}
}

