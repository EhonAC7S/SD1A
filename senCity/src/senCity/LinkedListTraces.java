package senCity;

import java.io.IOException;
import java.util.LinkedList; 

public class LinkedListTraces extends Traces {
	public LinkedListTraces(){ 
		list = new LinkedList<Trace>(); 
	}
	
	public LinkedListTraces(LinkedList<Trace> traces){
		this.list=traces;
	}
	public void initialiser() {
		this.list = new LinkedList<Trace>();
	}
	
	@Override
	public LinkedListTraces extract(String SSID) {
		LinkedListTraces extracted = new LinkedListTraces();
		for (Trace elt:this.list) {
			if ((elt.getSSID()).equals(SSID)) {
				extracted.ajouter(elt);
			}
		}
		return extracted;
	}

	public static void main(String[] args) throws IOException{
		LinkedListTraces traces = new LinkedListTraces();
		traces.load("capture_wifi.csv","capture_gps.csv",0.85);
		System.out.println(System.currentTimeMillis());
		traces.extract("<hidden>");
		System.out.println(System.currentTimeMillis());
		//temps : 1462382488912
		//        1462382488919 
		//donc 7 de difference
	}
}
