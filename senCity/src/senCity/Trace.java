package senCity;
import senCity.GPS;

public class Trace {
	
	private String ts;
	private String SSID;
	private int signal;
	private GPS localisation;
	
	public Trace(String ts, String SSID, int signal){
		this.ts = ts;
		this.SSID = SSID;
		this.signal = signal;
	}
	
	public String getSSID() {
		return this.SSID;
	}
	
	public Trace(String ts, String SSID, int signal, GPS loca){
		this.ts = ts;
		this.SSID = SSID;
		this.signal = signal;
		this.localisation = loca;
	}
	
	public String toString(){
		return ("(" + this.ts + ", " + this.SSID + ", " + this.signal + this.localisation.toString() + ")");
	}
}