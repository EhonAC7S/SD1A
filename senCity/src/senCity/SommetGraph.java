package senCity;

public class SommetGraph {
	private GPS gps;
	private Traces traces;
	
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
		double distance = Math.sqrt(Math.pow(Math.abs(abs1-abs2), 2)+Math.pow(Math.abs(ord1-ord2), 2));
		return (distanceMaxi>distance);
	}
	
	public double getDistance(SommetGraph s1) { //on renvoie la distance entre deux sommets proches
		double abs1 = Double.parseDouble(s1.getLocalisation().getLatitude());
		double abs2 = Double.parseDouble(this.getLocalisation().getLatitude());
		double ord1 = Double.parseDouble(s1.getLocalisation().getLongitude());
		double ord2 = Double.parseDouble(this.getLocalisation().getLongitude());
		double distance = Math.sqrt(Math.pow(Math.abs(abs1-abs2), 2)+Math.pow(Math.abs(ord1-ord2), 2));
		return distance;
	}
}
