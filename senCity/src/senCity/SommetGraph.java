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
}
