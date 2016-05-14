package senCity;

import java.io.IOException;
import java.util.ArrayList;


public class ArrayListTraces extends Traces {
	

	public ArrayListTraces(){ 
		list = new ArrayList<Trace>(); 
	}
	
	public ArrayListTraces(ArrayList<Trace> traces){ 
		this.list=traces;
		
	}
	
	public void initialiser() {
		this.list = new ArrayList<Trace>();
	}
	
	@Override
	public ArrayListTraces extract(String SSID) {
		ArrayListTraces extracted = new ArrayListTraces();
		for (Trace elt : this.list) {
			if ((elt.getSSID()).equals(SSID)) {
				extracted.ajouter(elt);
			}
		}
		return extracted;
	}
	
	public static void main(String[] args) throws IOException{
/*		ArrayListTraces traces = new ArrayListTraces();  //ligne dependant de la Structure choisie
		/*long memory;
		long diff;
		memory = Runtime.getRuntime().freeMemory();
		System.out.println(memory+"");
		traces.load("capture_wifi.csv","capture_gps.csv",100.);
		diff = memory;
		memory = Runtime.getRuntime().freeMemory();
		diff = diff - memory;
		System.out.println(memory+"");
		System.out.println(diff+"");
		/*traces.load("capture_wifi.csv");
		diff = memory;
		memory = Runtime.getRuntime().freeMemory();
		diff = diff - memory;
		System.out.println(memory+"");
		System.out.println(diff+"");
		//traces.save("nouveau.csv");
		 */
		ArrayListTraces traces = new ArrayListTraces();  //ligne dependant de la Structure choisie
		traces.load("capture_wifi.csv","capture_gps.csv",0.85);
		System.out.println(System.currentTimeMillis());
		traces.extract("<hidden>");
		System.out.println(System.currentTimeMillis());
		//temps :1462382600852
		//       1462382600861
		//donc difference de 9
	}
}


	

